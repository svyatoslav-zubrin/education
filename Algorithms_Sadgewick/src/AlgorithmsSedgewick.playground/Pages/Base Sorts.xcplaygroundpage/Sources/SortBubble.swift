import Foundation

public class SortBubble: SortBase {
    
    override public static func sort<Element where Element: Comparable>(inout array: Array<Element>, shouldLog: Bool = false) {
        
        guard array.count > 1 else {
            return
        }
        
        while true {
            var swapCount = 0
            
            for i in 0..<array.count - 1 {
                if array[i] > array[i + 1] {
                    exch(&array, first: i, second: i + 1)
                    if shouldLog { show(array, first: i, second: i + 1) }
                    swapCount += 1
                }
            }
            
            if swapCount == 0 { break }
        }
        
        if shouldLog { show(array) }
    }
}