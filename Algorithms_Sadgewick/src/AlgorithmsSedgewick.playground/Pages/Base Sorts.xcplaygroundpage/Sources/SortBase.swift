import Foundation

/// The abstract class that we will inherit from for implementing and testing of simple sorting algorithms.
public class SortBase {
    
    /**
     Actual implementation of the sorting logic will be placed here
     
     - parameter array: array to be sorted inplace
     */
    public class func sort<Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        // TODO: implementation needed by inheritors
    }
    
    /**
     Check if first element is less than the second one
     
     - parameter left:  first element
     - parameter right: second element
     
     - returns: `true` if first element is less than the second, `false` otherwise
     */
    static func less<Element: Comparable>(left: Element, right: Element) -> Bool {
        return left < right
    }
    
    /**
     Check if first element is less than the second one and increase the count
     
     - parameter left:  first element
     - parameter right: second element
     - parameter count: the count that should be increased by 1
     
     - returns: `true` if first element is less than the second, `false` otherwise
     */
    static func less<Element: Comparable>(left: Element, right: Element, inout count: Int) -> Bool {
        count += 1
        return left < right
    }

    
    /**
     Exchanges elements with a given indexes in the given array
     
     - parameter array:  array to be changed
     - parameter first:  first index
     - parameter second: second index
     */
    static func exch<Element: Comparable>(inout array: Array<Element>, first: Int, second: Int) {
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
    
    // MARK: testing API
    
    /**
     Test whether the array entries are in order.
     
     - parameter array: given array to be tested
     
     - returns: `true` if given array is ordered, `false` otherwise
     */
    public static func isSorted<Element: Comparable>(array: Array<Element>) -> Bool {
        for i in 1..<array.count {
            if array[i] < array[i-1] {
                return false
            }
        }
        return true
    }
    
    /**
     The debug output of the given array
     
     - parameter array: array to be printed
     - parameter first: the index of first element that should be capitalized, may be nil
     - parameter second: the index of second element that should be capitalized, may be nil
     */
    public static func show<Element: Comparable>(array: Array<Element>, first: Int? = nil, second: Int? = nil) {
        
        if first == nil && second == nil {
            print(array)
            return
        }
        
        guard let first = first, second = second else {
            print(array)
            return
        }
        
        guard first < array.count && second < array.count else {
            print(array)
            return
        }
        
        var result = "["
        for (index, item) in array.enumerate() {
            if index == first || index == second {
                if index == array.count - 1 {
                    // the last element in the array
                    result += "\"\(item)\"".uppercaseString
                } else {
                    result += "\"\(item)\", ".uppercaseString
                }
            } else {
                if index == array.count - 1 {
                    // the last element in the array
                    result += "\"\(item)\"".lowercaseString
                } else {
                    result += "\"\(item)\", ".lowercaseString
                }
            }
        }
        result += "] : (\(first), \(second))"
        
        print(result)
    }
}

// MARK: - Tests

extension SortBase {

    /**
     Actual test of sorting algorithm
     */
    public static func check(andLog shouldLog: Bool = false) {
        var a: Array<String> = ["m", "e", "r", "g", "e", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"]
        sort(&a, shouldLog: shouldLog)
        assert(isSorted(a))
        if shouldLog { show(a) }
    }

    /**
     Actual test of sorting algorithm using descending initial sequence
     */
    public static func checkWithDescendingSequence(andLog shouldLog: Bool = false) {
        var a: Array<Int> = [19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        sort(&a, shouldLog: shouldLog)
        assert(isSorted(a))
        if shouldLog { show(a) }
    }
    
    /**
     Actual test of sorting algorithm using ascending initial sequence
     */
    public static func checkWithAscendingSequence(andLog shouldLog: Bool = false) {
        var a: Array<Int> = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
        sort(&a, shouldLog: shouldLog)
        assert(isSorted(a))
        if shouldLog { show(a) }
    }

    /**
     Actual test of sorting algorithm using ascending initial sequence
     */
    public static func checkWithPartiallySortedSequence(andLog shouldLog: Bool = false) {
        var a: Array<Int> = [1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, 13, 12, 15, 14, 17, 16, 19, 18]
        sort(&a, shouldLog: shouldLog)
        assert(isSorted(a))
        if shouldLog { show(a) }
    }

}
