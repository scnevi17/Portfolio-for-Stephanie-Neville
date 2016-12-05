#!/usr/bin/env amm
/*
*/


import scala.io.Source

def lookupAnalysis(s: String, index: Vector[Array[String]]) = {
  val reslt = index.filter(_(0) == s)
  if (reslt.size == 0){
    s
  } else {
    reslt(0)(1)
  }
}


@main
def alignedtext(surfaceText: String, analysisFile: String) {

  val surface = Source.fromFile(surfaceText).getLines.toVector.filterNot(_.isEmpty)

  val pairings = surface.zipWithIndex
  val reff = pairings.map(_._2)


  val analysisIndex = Source.fromFile(analysisFile).getLines.toVector.map(_.split("\t").filterNot(_.isEmpty))


  val surfaceWords = surface.map(_.split("\\W").filterNot(_.isEmpty))
  val analyzedWords = surfaceWords.map { ar => ar.map( lookupAnalysis(_,analysisIndex)) }


   val analyzedText = analyzedWords.map(_.mkString(" "))

  val analyzedWithReff = reff.zip(analyzedText)
  for (wd <- analyzedWithReff) {
    println(wd._1 + "\t" + wd._2)
  }

}
