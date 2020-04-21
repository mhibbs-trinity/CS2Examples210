package cs2.io

import java.io._

object StreamStuff {

    def copyStream(in:InputStream, out:OutputStream):Unit = {
        while(in.available() > 0) {
            out.write(in.read)
        }
    }

    def main(args:Array[String]):Unit = {
        /*
        val path = getClass().getResource("/tempest.txt")
        println(path)

        val fis = new BufferedInputStream(
                     new FileInputStream(new File("tempest.txt")))
        val fos = new BufferedOutputStream(
                     new FileOutputStream(new File("temp.txt")))

        copyStream(fis, System.out)

        copyStream(fis, fos)

        fis.close()
        fos.close() */

        val dos = new DataOutputStream(
                      new BufferedOutputStream(
                      new FileOutputStream(new File("data.txt"))))
        dos.writeDouble(math.Pi)

        dos.close()


    }
}