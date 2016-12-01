#!/usr/bin/env amm
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

  val surface = Source.fromFile(surfaceText).getLines.toVector
  val pairings = surface.zipWithIndex
  val reff = pairings.map(_._2)


  val analysisIndex = Source.fromFile(analysisFile).getLines.toVector.map(_.split("\t"))

  val analyzed = surface.map(s => lookupAnalysis(s, analysisIndex))

  val analyzedWithReff = reff.zip(analyzed)
  for (wd <- analyzedWithReff) {
    println(wd._1 + "\t" + wd._2)
  }

}
