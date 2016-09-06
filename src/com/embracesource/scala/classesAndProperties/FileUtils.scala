package com.embracesource.scala.classesAndProperties
//4.4Defining a private primary constructor

//Because only an object is defined, code like this won¡¯t compile:
//val utils = new FileUtils // won't compile
//So in this case, there¡¯s no need for a private class constructor; just don¡¯t define a class.

object FileUtils {
  def readFile(filename: String) = {
    // code here ...
  }
  def writeToFile(filename: String, contents: String) {
    // code here ...
  }
}