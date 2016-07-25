import Foundation

public class SortSelectionBasedShell: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        
        var exchCount = 0
        var comparisonsCount = 0
        
        if shouldLog { show(array) }
        
        let incrementSequence = incrementSequenceFor(array)
        
        for increment in incrementSequence.reverse() {
            var minElementIndexInSubsequence = increment == 1 ? 0 : increment
            var initialElementInSubsequence = minElementIndexInSubsequence
            while minElementIndexInSubsequence < array.count - increment {
                defer {
                    minElementIndexInSubsequence = initialElementInSubsequence + increment
                    initialElementInSubsequence = minElementIndexInSubsequence
                }
                
                var nextElementIndexInSubsequence = minElementIndexInSubsequence + increment
                while nextElementIndexInSubsequence < array.count {
                    
                    defer {
                        nextElementIndexInSubsequence += increment
                    }
                    
                    if less(array[minElementIndexInSubsequence], right: array[nextElementIndexInSubsequence], count: &comparisonsCount) {
                        minElementIndexInSubsequence = nextElementIndexInSubsequence
                    }
                }
                
                if initialElementInSubsequence != minElementIndexInSubsequence {
                    exch(&array, first: initialElementInSubsequence, second: minElementIndexInSubsequence)
                    exchCount += 1
                    if shouldLog { show(array, first: initialElementInSubsequence, second: minElementIndexInSubsequence) }
                }
            }
        }

        if shouldLog {
            print("Exchanges count: \(exchCount)")
            print("Comparisons count: \(comparisonsCount)")
        }
    }
}

private extension SortSelectionBasedShell {
    
    static func incrementSequenceFor<Element>(array: Array<Element>) -> Array<Int> {
        var result: Array<Int> = [1]
        let maxElement = array.count / 3
        while result.last! < maxElement {
            result.append(result.last! * 3 + 1)
        }
        return result
    }
}