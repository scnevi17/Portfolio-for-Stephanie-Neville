#!/usr/bin/env amm
import scala.io.Source
import scala.xml._
def whatIsIt(n: scala.xml.Node) = {
  n match {
  case el : scala.xml.Elem => "It's an element."
  case txt: scala.xml.Text => "It's a text node"
  }
}

/*
val tinyXml = "<para>One short paragraph</para>"
val root = XML.loadString(tinyXml)
val expectedAnswer = "It's an element."
val actualAnswer = whatIsIt(root)
println(expectedAnswer + " == " + actualAnswer)
*/

def collectText(n: xml.Node, s: String): String = {
  var txt = s
  n match {
    case t: xml.Text => {
      txt = txt + t.text
    }
    case e: xml.Elem => {
      for (ch <- e.child) {
        txt += collectText(ch, s)
      }
    }
  }
  txt
}


@main
def getText (s : String) {
  val xmlText = Source.fromFile(s).getLines.mkString

  val root = XML.loadString(xmlText)
  val actualText = collectText(root,"")
  println(actualText)

//require(expectedText == actualText)
s.split("//W")
}
