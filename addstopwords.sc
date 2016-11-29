#.!/usr/bin/env amm
import scala.io.Source
@main
def addstopwords(f: String) {

println("This is the file we will analyze: " + f)
val lines = Source.fromFile(f).getLines.toVector

val pairings = lines.map(_.split("\t"")).filter(._size == 2)
val reff = pairings.map(_(0))
val chunks = pairings.map(_(1))

val stopwords = ("stoplist2.txt").split(" ")
val chunksByWord = chunks.
 map(_.split("\\W")
 filterNot(stopwords.contains(_)))

val filteredChunks = chunksByWord.map(_.mkString(" "))
filteredChunks
}
