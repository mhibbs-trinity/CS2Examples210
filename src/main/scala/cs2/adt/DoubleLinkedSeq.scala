package cs2.adt

class DoubleLinkedSeq[A:Manifest] extends Seq[A] {
    private class Node(var data:A, var prev:Node, var next:Node) {
        def getNodeByIndex(idx:Int):Node = {
            if(idx >= 0 && idx < len) {
                var rover = this
                for(i <- 1 to idx) rover = rover.next
                rover
            } else end
        }
    }
    private var end:Node = new Node(new Array[A](1)(0) ,end,end)
    private var len:Int = 0

    def get(idx:Int):A = end.next.getNodeByIndex(idx).data
    def set(idx:Int, elem:A):Unit = {
        end.next.getNodeByIndex(idx).data = elem
    }
    def insert(idx:Int, elem:A):Unit = {
        len += 1
        var rover = end.next.getNodeByIndex(idx-1)
        rover.next = new Node(elem, rover, rover.next)
        rover.next.next.prev = rover.next
    }
    def remove(idx:Int):A = {
        len -= 1
        var rover = end.next.getNodeByIndex(idx)
        rover.prev.next = rover.next
        rover.next.prev = rover.prev
        rover.data
    }

    def length():Int = ???

}