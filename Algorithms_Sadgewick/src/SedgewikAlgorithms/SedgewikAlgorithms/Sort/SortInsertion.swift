//
//  SortInsertion.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/15/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import Foundation

class SortInsertion<Element where Element: Comparable>: Sort {
    
    static func sort(inout array: Array<Element>) {
        for currentElementIndex in 1..<array.count {
            var actualElementIndex = currentElementIndex
            while actualElementIndex > 0 && array[actualElementIndex] < array[actualElementIndex - 1] {
                exch(&array, first: actualElementIndex, second: actualElementIndex - 1)
                actualElementIndex -= 1
            }
        }
    }
}