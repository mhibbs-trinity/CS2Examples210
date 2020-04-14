package cs2.parallel

import io.StdIn._
import java.lang.Thread

object SimpleCounting {

    def countDownGetName():String = {
        val thread = new Thread {
            override def run():Unit = {
                for(i <- 0 until 5) {
                    println(i)
                    Thread.sleep(1000)
                }
                println("Time's up")
                sys.exit
            }
        }
        thread.start

        println("Enter your name:")
        var name = readLine
        thread.stop
        name
    }

    def betterCountDownGetName():String = {
        var haveAnswer = false
        var timesUp = false

        val thread = new Thread {
            override def run():Unit = {
                //Running in one thread
                var ctr = 5
                while(!haveAnswer && ctr > 0) {
                    println(ctr)
                    ctr -= 1
                    Thread.sleep(1000)
                }
                if(!haveAnswer) {
                    println("Time's up - using default name")
                    timesUp = true
                }
            }
        }
        thread.start

        //Running in the "main" thread
        println("Enter your name:")
        var name = "Default"
        while(!timesUp && !Console.in.ready) {
            Thread.sleep(10)
        }
        if(!timesUp) {
            name = readLine
            haveAnswer = true
        }
        name
    }

    class Lock

    def simpleThreadCount():Unit = {
        val lock = new Lock

        var ctr = 0
        val threads = Array.fill(10)(new Thread {
            override def run():Unit = {
                for(i <- 1 to 1000000000/10) {
                    
                   lock.synchronized {
                       ctr += 1
                   } 
                }
            }
        })
        threads.foreach(_.start)
        threads.foreach(_.join)
        
        println(ctr)

    }


    def main(args:Array[String]):Unit = {
        //println("Hello, " + betterCountDownGetName)
        simpleThreadCount
    }
}