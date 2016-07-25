//
//  SortSelectionBasedShellTests.swift
//  SedgewikAlgorithms
//
//  Created by zubrin on 7/16/16.
//  Copyright © 2016 zubrin. All rights reserved.
//

import XCTest

class SortSelectionBasedShellTests: XCTestCase {

    var initialValue: Array<String> = []
    var expectation: Array<String> = ["a", "e", "e", "l", "m", "o", "p", "r", "s", "t", "x"]
    
    override func setUp() {
        super.setUp()
        initialValue = ["s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"]
    }
    
    func testSorting() {
        SortSelectionBasedShell.sort(&initialValue)
        XCTAssertEqual(initialValue, expectation, "Shell sort based on selection failed")
    }
    
    func testPerformance() {
        var values: [Int] = []
        for _ in 0..<1000 {
            values.append(random())
        }
        
        self.measureBlock {
            SortSelectionBasedShell.sort(&values)
        }
    }
    
    // TODO: test performance on predefined array to compare sorting methods
}
