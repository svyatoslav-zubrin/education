//
//  Sort.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/15/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import Foundation

protocol Sort {
    associatedtype Element: Comparable
    static func sort(inout array: Array<Element>)
}

extension Sort {
    static func exch<Element>(inout array: Array<Element>, first: Int, second: Int) {
        guard first != second else {
            return
        }
        
        guard first < array.count && second < array.count else {
            return
        }
        
        let tmp = array[first]
        array[first] = array[second]
        array[second] = tmp
    }

}