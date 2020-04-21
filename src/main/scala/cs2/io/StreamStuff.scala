package cs2.io

import java.io._

object StreamStuff {

    def copyStream(in:InputStream, out:OutputStream):Unit = {
        while(in.available() > 0) {
            out.write(in.read)
        }
    }

    def main(args:Array[String]):Unit = {

        //To read from a file in the resources folder, we should use the
        //getResourceAsStream method to immediately get an InputStream
        //rather than making a new File
        val stream = getClass().getResourceAsStream("/tempest.txt")
        val fin = new BufferedInputStream(stream)
        while(fin.available() > 0) {
            print(fin.read.toChar)
        }

        /*
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