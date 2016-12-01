#!/usr/bin/env amm
import scala.io.Source

import scala.xml._

def distinctwordsscript (s : String) = {
  val txt = scala.io.Source.fromFile(s).getLines.toList
  val textlines = txt.mkString(" ")
  val words = textlines.split("\\W+").filterNot(_.isEmpty)
  words.distinct.toVector
}

@main
def getDistinctWords(fName: String) = {
   val wordList = distinctwordsscript(fName)
   for (w <- wordList) {
     println(w)
   }
}
