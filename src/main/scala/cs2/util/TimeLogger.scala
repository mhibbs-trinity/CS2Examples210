package cs2.util

class TimeLogger {
    var start = System.nanoTime
    def restart():Unit = { start = System.nanoTime }
    def log(msg:String):Unit = {
        println(msg + ": " + (System.nanoTime - start)/1e9 + "s")
    }
}