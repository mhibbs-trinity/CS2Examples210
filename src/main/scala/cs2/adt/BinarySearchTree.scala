package cs2.adt

class BinarySearchTree[A <% Ordered[A]] {
  private class Node(var data: A, var left: Node, var right: Node) {
    def contains(elem: A): Boolean = {
      if (data == elem) true
      else {
        if (elem < data) {
          if (left == null) false
          else left.contains(elem)
        } else {
          if (right == null) false
          else right.contains(elem)
        }
      }
    }

    def insert(elem:A):Unit = {
        if(elem < data) {
            if(left == null) left = new Node(elem, null, null)
            else left.insert(elem)
        } else {
            if(right == null) right = new Node(elem, null, null)
            else right.insert(elem)
        }
    }

    def passUpMax():(A,Node) = {
        if(right == null) (data, left)
        else {
            val (d,n) = right.passUpMax
            right = n
            (d, this)
        }
    }

    def remove(elem:A):Node = {
        if(data == elem) {
            if(left == null) right
            else if(right == null) left
            else {
                val (d,n) = left.passUpMax
                data = d
                left = n
                this
            }
        } else {
            if(elem < data) left = left.remove(elem)
            else right = right.remove(elem)
            this
        }
    }

    def getMax():A = {
      if(right == null) data
      else right.getMax
    }

  }

  private var root: Node = null

  def getMax():A = {
    root.getMax
  }
  def removeMax():A = {
    val mx = getMax
    remove(mx)
    mx
  }

  def contains(elem: A): Boolean = {
      if(root == null) false
      else root.contains(elem)
  }

  def insert(elem:A):Unit = {
      if(root == null) root = new Node(elem, null, null)
      else root.insert(elem)
  }

  def remove(elem:A):Unit = {
      if(root != null) root = root.remove(elem)
  }

  def isEmpty():Boolean = root == null

}