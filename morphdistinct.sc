#!/usr/bin/env amm
import scala.io.Source

import scala.xml._

@main
def disctinctwordsscript (s : String) = {
  val txt = scala.io.Source.fromFile(s).getLines.toList
  val textlines = txt.mkString(" ")
  val words = textlines.split("\\W+").filterNot(_.isEmpty)
  words.distinct
  //val grouped = words.groupBy(w =>w)
  //val freqs = grouped.map { case (k, v) => (k,v.size) }
  //val sorted = freqs.toSeq.sortBy(_._2)
  //for (wd <- sorted) {
  //println(wd)
  //}
}
