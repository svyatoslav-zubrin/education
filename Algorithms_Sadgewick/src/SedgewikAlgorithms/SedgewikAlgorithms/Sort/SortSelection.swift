//
//  File.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/15/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import Foundation

class SortSelection<Element where Element: Comparable>: Sort {
    
    static func sort(inout array: Array<Element>) {
        let elementsCount = array.count
        for currentElementIndex in 0..<elementsCount {
            var minElementIndex = currentElementIndex
            for j in currentElementIndex+1..<elementsCount {
                if array[j] < array[minElementIndex] {
                    minElementIndex = j
                }
            }
            exch(&array, first: currentElementIndex, second: minElementIndex)
        }
    }
}
