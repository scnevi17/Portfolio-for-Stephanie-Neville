#!/usr/bin/env amm
import scala.io.Source

import scala.xml._

@maindef formatEntry(e: Elem) : String = {
  val uriGroup = e \ "@uri"
  val uri = idForEntry(uriGroup)
  val headwordList = e \\ "hdwd"
  val headword = lemmaForEntry(headwordList)
  uri + "_" + headword
}
