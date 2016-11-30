#!/usr/bin/env amm
import scala.io.Source

import scala.xml._


@main
def morphology(fName: String) = {
  val words = Source.fromFile(fName).getLines.toVector
  val parsedWords = words.map(w => (w, parse(w).mkString(","))).filterNot(_._2.isEmpty )
  for (wd <- parsedWords) {
   println(wd._1 + "\t" + wd._2)
  }
}


def parse(wd : String): Vector[String] = {
  val baseUrl =   "https://services.perseids.org/bsp/morphologyservice/analysis/word?lang=lat&engine=morpheuslat&word="
  val query = baseUrl + wd
  val morphReply = scala.io.Source.fromURL(query).mkString
  val xmlReply = XML.loadString(morphReply)
  val entries = xmlReply \\ "entry"

  val lexent = entries.map(e => e match {
    case el: Elem => formatEntry(el)
    case _ => ""
    }
  )
  lexent.toVector
}



def lemmaForEntry (nseq: NodeSeq) = {
  if (nseq.size > 0) {
  nseq(0).text
  } else ""
}

def idForEntry (nseq: NodeSeq) = {
  if (nseq.size > 0) {
  nseq(0).text.replaceFirst("http://data.perseus.org/collections/urn:cite:perseus:latlexent.","")
  } else ""
}

def formatEntry(e: Elem) : String = {
  val uriGroup = e \ "@uri"
  val uri = idForEntry(uriGroup)
  val headwordList = e \\ "hdwd"
  val headword = lemmaForEntry(headwordList)
  uri + "_" + headword
}
