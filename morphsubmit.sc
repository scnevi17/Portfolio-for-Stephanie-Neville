#!/usr/bin/env amm
import scala.io.Source

import scala.xml._

@main
def morphsubmit(fName: String) = {
  val words = disctinctwordsscript(fName)
  val parsedWords = words.map (w => (w, parse(w)))
  for (wd <- parsedWords) {
    println(wd._1 + "\t" + wd._2.toVector.mkString(","))
  }
}

def parse(wd : String) = {
  val baseUrl =   "https://services.perseids.org/bsp/morphologyservice/analysis/word?lang=lat&engine=morpheuslat&word="
  val query = baseUrl + wd
  val morphReply = scala.io.Source.fromURL(query).mkString
  val xmlReply = XML.loadString(morphReply)
  val entries = xmlReply \\ "entry"
def lemmaForEntry (nseq: NodeSeq) = {
  if (nseq.size > 0) {
  nseq(0).text
  } else ""
}


def idForEntry (nseq: NodeSeq) = {
  if (nseq.size > 0) {
  nseq(0).text.replaceFirst("http://data.perseus.org/collections/urn:cite:perseus:latlexent","")
  } else ""
}
