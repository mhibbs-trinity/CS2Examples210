package cs2.algorithms

import scala.util.Random

object ParametricSorting {

    def bubbleSort[A <% Ordered[A]] (arr:Array[A]):Unit = {
        for(j <- 0 until arr.length) {
            for(i <- 0 until arr.length-1) {
                if(arr(i) > arr(i+1)) {
                    val tmp = arr(i)
                    arr(i) = arr(i+1)
                    arr(i+1) = tmp
                }
            }
        }
    }

    def bubbleSort[A] (arr:Array[A], gt:(A,A)=>Boolean):Unit = {
        for(j <- 0 until arr.length) {
            for(i <- 0 until arr.length-1) {
                if(gt(arr(i), arr(i+1))) {
                    val tmp = arr(i)
                    arr(i) = arr(i+1)
                    arr(i+1) = tmp
                }
            }
        }
    }

    class Student(var first:String, val id:Int, var gpa:Double) extends Ordered[Student] {
        override def compare(other:Student):Int = {
            first.compare(other.first)
        }
        override def toString():String = first
    }

    def main(args:Array[String]):Unit = {
        val a:Array[Double] = Array.fill(10)(math.random)
        val b:Array[Char] = Array.fill(10)(Random.nextPrintableChar)
        
        val c:Array[Student] = Array(new Student("Sally", 123, 4.0),
                                     new Student("Bob", 432, 3.2),
                                     new Student("Cindy", 512, 2.6))
        
        println(c.mkString(", "))
        bubbleSort(c, (x:Student,y:Student) => x.gpa > y.gpa)
        println(c.mkString(", "))
        
        /*
        println(a.mkString)
        bubbleSort(a, (x:Double,y:Double) => x > y)
        println(a.mkString)

        println(b.mkString)
        bubbleSort(b)
        println(b.mkString)
        */
    }
}