import Foundation

public class SortMerge: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        if shouldLog { show(array) }
        sort(&array, low: 0, high: array.count - 1, shouldLog: shouldLog)
    }
}

private extension SortMerge {
    
    static func sort<Element where Element: Comparable>(inout array: Array<Element>, low: Int, high: Int, shouldLog: Bool) {
        guard low < high else {
            return
        }
        
        var auxArray: Array<Element> = Array(count: array.count, repeatedValue: array[low])
        
        let middle = low + (high - low) / 2
        sort(&array, low: low, high: middle, shouldLog: shouldLog)
        sort(&array, low: middle+1, high: high, shouldLog: shouldLog)
        merge(&array, auxArray: &auxArray, low: low, middle: middle, high: high)
    }
    
    static func merge<Element where Element: Comparable>(inout array: Array<Element>, inout auxArray: Array<Element>, low: Int, middle: Int, high: Int) {
        assert(low > high, "Incorrect indexes for merging (low > high)")
        assert(low > middle, "Incorrect indexes for merging (low > middle)")
        assert(middle > high, "Incorrect indexes for merging (middle > high)")
        
        for i in low...high {
            auxArray[i] = array[i]
        }
        
        var i = low, j = middle + 1
        for k in low...high {
            if i > middle { // no elements in the first part -> copy the rest elements from the second part
                array[k] = auxArray[j]
                j += 1
            } else if j > high { // no elements in the second part -> copy the rest elements from the first part
                array[k] = auxArray[i]
                i += 1
            } else if auxArray[i] < auxArray[j] {
                array[k] = auxArray[i]
                i += 1
            } else {
                array[k] = auxArray[j]
                j += 1
            }
        }

        print("merge array (\(low), \(middle), \(high)):\t\(array)")
    }
}