package cs2.algorithms

import scala.io.Source
import scala.collection.mutable.Set
import scala.collection.mutable.Map

object TextAnalysis {

    def countWords(lines:Iterator[String]):Int = {
        var wordSet = Set[String]()
        for(line <- lines) {
            var words = line.split("\\s+")
            words = words.map(_.filter(_.isLetter).toLowerCase)
            wordSet ++= words
        }
        for(word <- wordSet) {
            //println(word)
        }
        wordSet.size
    }

    def freqWords(lines:Iterator[String]):Int = {
        var wordMap = Map[String,Int]()
        for(line <- lines) {
            var words = line.split("\\s+")
            words = words.map(_.filter(_.isLetter).toLowerCase)
            for(word <- words) {
                if(wordMap.contains(word)) {
                    wordMap(word) += 1
                } else {
                    wordMap(word) = 1
                }
            }    
        }
        val mapSort = wordMap.toArray
        ParametricSorting.bubbleSort(mapSort, (tup1:(String,Int), tup2:(String,Int)) => tup1._2 < tup2._2)

        for((word,count) <- mapSort) {
            println(word + "\t" + count)
        }
        wordMap.size
    }

    def main(args:Array[String]):Unit = {
        val url = getClass().getResource("/tempest.txt")
        val lines = Source.fromURL(url).getLines()
        println(freqWords(lines))
    }
}