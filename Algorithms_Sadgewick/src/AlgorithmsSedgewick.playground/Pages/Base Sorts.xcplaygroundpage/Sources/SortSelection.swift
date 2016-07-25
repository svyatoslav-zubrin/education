import Foundation

public class SortSelection: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        
        var exchCount = 0
        var comparisonsCount = 0
        
        if shouldLog { show(array) }
        
        let elementsCount = array.count
        
        for currentElementIndex in 0..<elementsCount {
            var minElementIndex = currentElementIndex
            for j in currentElementIndex+1..<elementsCount {
                if less(array[j], right: array[minElementIndex], count: &comparisonsCount) {
                    minElementIndex = j
                }
            }
            exch(&array, first: currentElementIndex, second: minElementIndex)
            exchCount += 1
            if shouldLog { show(array, first: currentElementIndex, second: minElementIndex) }
        }
        
        
        if shouldLog {
            print("Exchanges count: \(exchCount)")
            print("Comparisons count: \(comparisonsCount)")
        }
    }
}