#!/usr/bin/env amm
import io.Source
@main
def zipf(f: String) {
println("This is the file we will analyze: " + f)
val lines = Source.fromFile(f).getLines.toList
val textlines = lines.filter(!_.contains("BOOK"))
val caesar = textlines.mkString
val allwords = caesar.split("\\W")
val groupedwords = allwords.groupBy(w => w)
val freqs = groupedwords.map { case (k,v) => (k, v.size) }
val orderedFreqs = freqs.toSeq
val sorted = orderedFreqs.sortBy(_._2)
for (kvpair <- sorted) {
println(kvpair)
}
}
