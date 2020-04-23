package cs2.io

import java.io._
import scala.collection.mutable

class Student(var name:String, var id:Int, var gpa:Double) extends Serializable {
    def writeStudent(out:DataOutputStream):Unit = {
        out.write(name.length)
        for(c <- name) {
            out.writeChar(c)
        }
        out.writeInt(id)
        out.writeDouble(gpa)
    }
    override def toString():String = name + ":" + gpa
}

object Student {
    def readStudent(in:DataInputStream):Student = {
        val len:Int = in.read
        var name = ""
        for(i <- 0 until len) {
            name += in.readChar
        }
        val sid = in.readInt
        val sgpa = in.readDouble
        new Student(name, sid, sgpa)
    }
}

object SerialTester {
    var students = mutable.Buffer[Student]()

    def createStudents():Unit = {
        students += new Student("Jane Doe", 12345, 3.78)
        students += new Student("Bob Smith", 98765, 3.2)
        students += new Student("Mary Poppins", 77777, 4.0)
    }

    def saveStudentsToFile(fname:String):Unit = {
        val out = new DataOutputStream(new BufferedOutputStream(
                        new FileOutputStream(new File(fname))))
        out.writeInt(students.length)
        for(s <- students) {
            s.writeStudent(out)
        }
        out.close
    }

    def saveStudentObjectsToFile(fname:String):Unit = {
        val out = new ObjectOutputStream(new BufferedOutputStream(
                        new FileOutputStream(new File(fname))))
        out.writeInt(students.length)
        for(s <- students) {
            out.writeObject(s)
        }
        out.close
    }

    def readStudentFromFile(fname:String):Unit = {
        val in = new DataInputStream(new BufferedInputStream(
                        new FileInputStream(new File(fname))))
        val numStudents = in.readInt
        for(i <- 0 until numStudents) {
            students += Student.readStudent(in)
        }
        in.close
    }

    def readStudentObjectsFromFile(fname:String):Unit = {
        val in = new ObjectInputStream(new BufferedInputStream(
                        new FileInputStream(new File(fname))))
        val numStudents = in.readInt
        for(i <- 0 until numStudents) {
            students += in.readObject.asInstanceOf[Student]
        }
        in.close
    }

    def main(args:Array[String]):Unit = {
        createStudents()
        println(students)
        saveStudentObjectsToFile("students_obj.txt")
        readStudentObjectsFromFile("students_obj.txt")
        println("-------")
        println(students)
        println(students.length)
    }

}
