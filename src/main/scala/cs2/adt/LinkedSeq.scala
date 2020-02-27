package cs2.adt

class LinkedSeq[A] extends Seq[A] with Iterable[A] {
    private class Node(var data:A, var next:Node) {
        def getNodeByIndex(idx:Int):Node = {
            var rover = this
            for(i <- 1 to idx) rover = rover.next
            rover
        }
    }
    private var myHead:Node = null
    private var len:Int = 0

    def length():Int = len

    def iterator():scala.collection.Iterator[A] = {
        new scala.collection.Iterator[A] {
            var rover = myHead
            def next():A = {
                val ret = rover.data
                rover = rover.next
                ret
            }
            def hasNext():Boolean = {
                rover != null
            }
        }
    }

    def get(idx:Int):A = {
        var rover = myHead.getNodeByIndex(idx)
        rover.data
    }
    def set(idx:Int, elem:A):Unit = {
        var rover = myHead.getNodeByIndex(idx)
        rover.data = elem
    }
    def insert(idx:Int, elem:A):Unit = {
        if(idx == 0) {
            myHead = new Node(elem, myHead)
        } else {
            var rover = myHead.getNodeByIndex(idx-1)
            rover.next = new Node(elem, rover.next)
        }
        len += 1
    }
    def remove(idx:Int):A = {
        len -= 1
        if(idx == 0) {
            val ret = myHead.data
            myHead = myHead.next
            ret
        } else {
            var rover = myHead.getNodeByIndex(idx-1)
            val ret = rover.next.data
            rover.next = rover.next.next
            ret
        }
    }

}

object SeqStuff {
    def main(args:Array[String]) {
        var seq = new LinkedSeq[Int]()
        for(i <- 1 to 20) seq.insert(0,i)

        for(i <- 0 until seq.length) {
            print(seq(i) + ",")
        }

        var it = seq.iterator()
        while(it.hasNext) {
            print(it.next + ",")
        }
        it = seq.iterator()
        it.foreach(print)

        seq.foreach(print)

    }
}
