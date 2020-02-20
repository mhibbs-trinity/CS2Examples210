package cs2.adt

class ArrayStack[A : Manifest] extends Stack[A] {
    var arr = new Array[A] (20)
    var len = 0

    def push(elem:A):Unit = {
        if(len == arr.length) {
            val tmp = new Array[A] (len * 2)
            for(i <- 0 until len) tmp(i) = arr(i)
            arr = tmp
        }
        arr(len) = elem
        len += 1
    }
    def pop():A = {
        len -= 1
        arr(len)
    }
    def peek():A = arr(len-1)
    def isEmpty():Boolean = len == 0

}