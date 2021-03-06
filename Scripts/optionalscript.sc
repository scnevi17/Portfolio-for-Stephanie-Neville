#!/usr/bin/env amm
import scala.io.Source
@main
def optionalscript(f: String) {
println("This is the file we will analyze: " + f)
val lines = Source.fromFile(f).getLines.toVector
val txt = lines.mkString
val words = txt.split("\\W+").filterNot(_.isEmpty)
val grouped = words.groupBy(w =>w)
val freqs = grouped.map { case (k, v) => (k,v.size) }
val sorted = freqs.toSeq.sortBy(_._2)
for (wd <- sorted) {
println(wd)
}
println("Read " + lines.size + " sections.")
println("Read " + txt.size + " characters.")
println("Read " + words.size + " words.")
println("Read " + grouped.size + " unique words.")
}
