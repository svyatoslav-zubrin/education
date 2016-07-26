import Foundation

public class SortQuick: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        
        if shouldLog { show(array) }
        
        // TODO: we must shuffle the original array first to make sure that it is not sorted, cause in that case quicksort sucks.(
        
        sort(&array, low: 0, high: array.count - 1, shouldLog: true)
    }
}

extension SortQuick {
    
    public static func sort<Element where Element: Comparable>(inout array: Array<Element>, low: Int, high: Int, shouldLog: Bool = false) {
        
        guard low < high else { return }
        
        // partition original aray
        let mid = partition(&array, low: low, high: high, shouldLog: shouldLog)
        
        // recursively sort two subarrays (left & right)
        sort(&array, low: low    , high: mid - 1 , shouldLog: shouldLog)
        sort(&array, low: mid + 1, high: high    , shouldLog: shouldLog)
    }
    
    private static func partition<Element where Element: Comparable>(inout array: Array<Element>, low: Int, high: Int, shouldLog: Bool = false) -> Int {
        
        var inner_low = low
        var inner_high = high
        
        while true {
            
            // move from left border to the right until we find element that is greater than the initial one
            while array[low] >= array[inner_low] {
                inner_low += 1
                if inner_low == high { break }
            }
            
            // move from right border to the left until we find element that is lower than the initial one
            while array[low] < array[inner_high] {
                inner_high -= 1
                if inner_high == low { break }
            }

            // check for permutation finish and break if needed
            if inner_low >= inner_high {
                break
            }
            
            // exchange found inner elements that break the invariant
            exch(&array, first: inner_low, second: inner_high)
            if shouldLog { show(array, first: inner_low, second: inner_high) }
            
        }

        // exchange initial element with the top element in the left subarray (subarray with lower elements)
        exch(&array, first: low, second: inner_high)
        if shouldLog { show(array, first: low, second: inner_high) }
        
        return inner_high
    }
}
