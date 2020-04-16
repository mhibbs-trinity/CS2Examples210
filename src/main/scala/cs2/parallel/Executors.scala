package cs2.parallel

import java.util.concurrent._

object Executors {

    def countingWithExecutors():Unit = {
        val service = java.util.concurrent.Executors.newCachedThreadPool
        val futures = Array.fill(10)(service.submit(new Callable[Int] {
            def call():Int = {
                var local = 0
                for(i <- 1 to 100000000) {
                    local += 1
                }
                local
            }
        }))
        val res:Array[Int] = futures.map(_.get)
        println(res.sum)
        service.shutdown
    }

    def main(args:Array[String]):Unit = {
        countingWithExecutors
    }
}