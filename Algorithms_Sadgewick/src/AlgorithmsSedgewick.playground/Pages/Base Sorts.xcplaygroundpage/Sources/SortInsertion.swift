import Foundation

public class SortInsertion: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        
        var exchCount = 0
        var comparisonsCount = 0
        
        if shouldLog { show(array) }
        
        for currentElementIndex in 1..<array.count {
            var actualElementIndex = currentElementIndex
            while actualElementIndex > 0 && less(array[actualElementIndex], right: array[actualElementIndex - 1], count: &comparisonsCount) {
                exch(&array, first: actualElementIndex, second: actualElementIndex - 1)
                exchCount += 1
                if shouldLog { show(array, first: actualElementIndex, second: actualElementIndex - 1) }
                actualElementIndex -= 1
            }
        }
        
        if shouldLog {
            print("Exchanges count: \(exchCount)")
            print("Comparisons count: \(comparisonsCount)")
        }
    }
}