import Foundation

public class SortShell: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        
        var exchCount = 0
        var comparisonsCount = 0
        
        if shouldLog { show(array) }
        
        let incrementSequence = incrementSequenceFor(array)
        
        for increment in incrementSequence.reverse() {
            for i in increment..<array.count {
                var j = i
                while j >= increment && less(array[j], right: array[j - increment], count: &comparisonsCount) {
                    exch(&array, first: j, second: j - increment)
                    exchCount += 1
                    if shouldLog { show(array, first: j, second: j - increment) }
                    j -= increment
                }
            }
        }
        
        if shouldLog {
            print("Exchanges count: \(exchCount)")
            print("Comparisons count: \(comparisonsCount)")
        }
    }
}

private extension SortShell {

    static func incrementSequenceFor<Element>(array: Array<Element>) -> Array<Int> {
        var result: Array<Int> = [1]
        let maxElement = array.count / 3
        while result.last! < maxElement {
            result.append(result.last! * 3 + 1)
        }
        return result
    }
}