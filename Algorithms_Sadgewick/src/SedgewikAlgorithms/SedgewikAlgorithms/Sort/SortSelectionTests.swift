//
//  SortSelectionTests.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/15/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import XCTest

class SortSelectionTests: XCTestCase {
    
    var initialValue: Array<String> = []
    var expectation: Array<String> = ["a", "e", "e", "l", "m", "o", "p", "r", "s", "t", "x"]
    
    override func setUp() {
        super.setUp()
        initialValue = ["s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"]
    }
    
    func testSorting() {
        SortSelection.sort(&initialValue)
        XCTAssertEqual(initialValue, expectation, "Selection sort failed")
    }

    func testPerformance() {
        var values: [Int] = []
        for _ in 0..<1000 {
            values.append(random())
        }
        
        self.measureBlock {
            SortSelection.sort(&values)
        }
    }
    
    // TODO: test performance on predefined array to compare sorting methods
}
