//
//  SortSelectionBasedShell.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/16/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import Foundation

class SortSelectionBasedShell<Element where Element: Comparable>: Sort {

    static func sort(inout array: Array<Element>) {
        let incrementSequence = incrementSequenceFor(array)
        
        for increment in incrementSequence.reverse() {
            var minElementIndexInSubsequence = increment == 1 ? 0 : increment
            var initialElementInSybsequence = minElementIndexInSubsequence
            while minElementIndexInSubsequence < array.count - increment {
                defer {
                    minElementIndexInSubsequence = initialElementInSybsequence + increment
                    initialElementInSybsequence = minElementIndexInSubsequence
                }
                
                var nextElementIndexInSubsequence = minElementIndexInSubsequence + increment
                while nextElementIndexInSubsequence < array.count {
                
                    defer {
                        nextElementIndexInSubsequence += increment
                    }
                    
                    if array[minElementIndexInSubsequence] > array[nextElementIndexInSubsequence] {
                        minElementIndexInSubsequence = nextElementIndexInSubsequence
                    }
                }

                if initialElementInSybsequence != minElementIndexInSubsequence {
                    exch(&array, first: initialElementInSybsequence, second: minElementIndexInSubsequence)
                }
            }
        }
    }
}

private extension SortSelectionBasedShell {
    
    static func incrementSequenceFor(array: Array<Element>) -> Array<Int> {
        var result: Array<Int> = [1]
        let maxElement = array.count / 3
        while result.last! < maxElement {
            result.append(result.last! * 3 + 1)
        }
        return result
    }
}