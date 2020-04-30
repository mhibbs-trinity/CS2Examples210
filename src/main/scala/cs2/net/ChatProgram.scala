package cs2.net

import java.lang.Thread
import java.net._
import java.io._

object ChatProgram {

    def createInputThread(sock:Socket):Thread = {
        new Thread {
            override def run() {
                val is = new BufferedInputStream(sock.getInputStream)
                var msg = ""
                while(!sock.isClosed && msg != "EXIT\n") {
                    msg = ""
                    while(!sock.isClosed && is.available == 0) Thread.sleep(10)
                    var lastChar = ' '
                    while(!sock.isClosed && is.available > 0 && lastChar != '\n') {
                        lastChar = is.read.toChar
                        msg += lastChar
                    }
                    println("<< " + msg)
                }
                is.close
            }
        }
    }

    def createOutputThread(sock:Socket):Thread = {
        new Thread {
            override def run() {
                val os = new BufferedOutputStream(sock.getOutputStream)
                val is = new BufferedInputStream(System.in)
                var msg = ""
                while(!sock.isClosed && msg != "EXIT\n") {
                    msg = ""
                    while(!sock.isClosed && is.available == 0) Thread.sleep(10)
                    var lastChar = 'Z'
                    while(!sock.isClosed && is.available > 0 && lastChar != '\n') {
                        lastChar = is.read.toChar
                        msg += lastChar
                    }
                    println(">> " + msg)
                    if(!sock.isClosed) {
                        for(c <- msg) os.write(c)
                        os.flush
                    }
                }
                os.close
                is.close
            }
        }
    }


    def main(args:Array[String]):Unit = {
        if(args(0) == "server") {
            val ss = new ServerSocket(args(1).toInt)
            val sock = ss.accept
            val inThread = createInputThread(sock)
            val outThread= createOutputThread(sock)
            inThread.start
            outThread.start
            inThread.join
            outThread.join
            sock.close
            ss.close
        } else if(args(0) == "client") {
            val sock = new Socket(args(2), args(1).toInt)
            val inThread = createInputThread(sock)
            val outThread= createOutputThread(sock)
            inThread.start
            outThread.start
            inThread.join
            outThread.join
            sock.close
        }

    }
}