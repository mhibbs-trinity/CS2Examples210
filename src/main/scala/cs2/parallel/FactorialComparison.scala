package cs2.parallel

import cs2.util.TimeLogger
import java.util.concurrent._

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

        for(i <- 1 to 30) {
            logger.restart
            factExecutor(n, i)
            logger.log("factExect " + i)
        }
    }
}