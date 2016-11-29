#.!/usr/bin/env amm
import scala.io.Source
@main
def alignedtext(f: String) {

println("This is the file we will analyze: " + f)
val lines = Source.fromFile(f).getLines.toVector

val pairings = lines.map(_.split("\t"")).filter(._size == 2)
val reff = pairings.map(_(0))
val chunks = pairings.map(_(1))

val analyzedAsCells = chunks.map(_.toCells)
val = reff.zip(analyzedAsCells)
val = citableSourceText = reff.zip(chunks)
}
