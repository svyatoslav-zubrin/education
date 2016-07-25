//
//  SortShell.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/15/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import Foundation

class SortShell<Element where Element: Comparable>: Sort {
    
    static func sort(inout array: Array<Element>) {
        let incrementSequence = incrementSequenceFor(array)
        
        for increment in incrementSequence.reverse() {
            for i in increment..<array.count {
                var j = i
                while j >= increment && array[j] < array[j - increment] {
                    exch(&array, first: j, second: j-increment)
                    j -= increment
                }
            }
        }
    }
}

private extension SortShell {
    
    static func incrementSequenceFor(array: Array<Element>) -> Array<Int> {
        var result: Array<Int> = [1]
        let maxElement = array.count / 3
        while result.last! < maxElement {
            result.append(result.last! * 3 + 1)
        }
        return result
    }
}