package cs2.parallel

import cs2.util.TimeLogger
import java.util.concurrent._
import scala.collection.parallel.mutable.ParArray

object FactorialComparison {

    def factRecur(n:BigInt):BigInt = {
        if(n <= 1) 1
        else n * factRecur(n-1)
    }

    def factFor(n:BigInt):BigInt = {
        var product:BigInt = 1
        val one:BigInt = 1
        for(x <- one to n) {
            product *= x
        }
        product
    }

    def factCollect(n:BigInt):BigInt = {
        val one:BigInt = 1
        (one to n).reduce(_*_)
    }

    def factParCollect(n:BigInt):BigInt = {
        val one:BigInt = 1
        (one to n).par.reduce(_*_)
    }

    def factExecutor(n:BigInt, k:Int):BigInt = {
        val service = java.util.concurrent.Executors.newCachedThreadPool
        val futures = Array.tabulate(k)((i:Int) => {
            service.submit(new Callable[BigInt] {
                def call():BigInt = {
                    (BigInt(i+1) to n by k).product
                }
            })
        })
        val res = futures.map(_.get).product
        service.shutdown
        res
    }

    def main(args:Array[String]):Unit = {
        val logger = new TimeLogger

        val a = (Array.fill(100)(1)).par
        var x = 0
        for(i <- a) {
            x += i
        }
        println(x)
        println(a.sum)

        val n:BigInt = 100000

        logger.restart
        //factRecur(n)
        logger.log("factRecur")

        logger.restart
        factFor(n)
        logger.log("factFor  ")

        logger.restart
        factCollect(n)
        logger.log("factCollt")

        logger.restart
        factParCollect(n)
        logger.log("factPar  ")

        for(i <- 10 to 10) {
            logger.restart
            factExecutor(n, i)
            logger.log("factExect " + i)
        }
    }
}