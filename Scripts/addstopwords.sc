#!/usr/bin/env amm
import scala.io.Source

// File f is a two-column file with a text to strip stopwords from.



@main
def addstopwords(f: String) {

  println("This is the file we will analyze: " + f)
  val lines = Source.fromFile(f).getLines.toVector

  val pairings = lines.map(_.split("\t")).filter(_.size == 2)
  val reff = pairings.map(_(0))
  val chunks = pairings.map(_(1))

  val stopwords = Source.fromFile("stoplist2.txt").getLines.toVector.flatMap(_.split(" "))
  val chunksByWord = chunks.
       map(_.split("\\W").filterNot(stopwords.contains(_)))
  val trimmedText = chunksByWord.map(_.mkString(" "))

  val indexedTrimmedText = reff.zip(trimmedText)
  for (t <- indexedTrimmedText) {
    println(t._1 + "\t" + t._2)
  }
}
