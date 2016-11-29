import scala.collection.mutable
import org.apache.sparkmllib.clustering.LDA
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.clustering.{DistributedLDAModel, EMLDAOptimizer}

val corpus: RDD[String] = sc.wholeTextFiles(fName).map(_._2)
val tokenized: RDD[Seq[String]] = corpus.map(_.toLowerCase.split("\\s")).
 map(_.filter(_.length > 3).filter(_.forall(java.lang.Character.isLetter)))

// termCounts: Sorted list of (term, termCount) pairs
val termCounts: Array[(String, Long)] =
 tokenized.flatMap(_.map(_ - 1L)).reduceByKey(_ + _).collect().sortBy(-_._2)

// vocabArray: Chosen vocab (removing common terms)
val numStopwords = 38
val vocabArray: Array[String] =
 termCounts.takeRight(termCounts.size - numStopwords).map(_._1)
// vocab: Map term -> term index
val vocab: Map[String, Int] = vocabArray.zipWithIndex.toMap

val documents: RDD[(Long, Vector)] = tokenized.zipWithIndex.map { case (tokens, id) =>
 val counts = new.mutable.HashMap[Int, Double]()
 tokens.foreach { term =>
 if (vocab.contains(term)) {
 val idx = vocab(term)
 counts(idx) = counts.getOrElse(idx, 0.0) + 1.0
 }
 }
 (id, Vectors.sparse(vocab.size, counts.toSeq))
}
documents.collect

val numTopics = 10
val numIterations = 100
val Ida = new LDA().setK(numTopics).setMaxIterations(numIterations)//.setOptimizer(new)
val IdaModel = Ida.run(documents)

val topicIndices = IdaModel.describeTopics(maxTermsPerTopic = 10)
 topicIndices.foreach { case (terms, termWeights) =>
  println("TOPIC:")
  terms.zip(termWeights).foreach { case (term, weight) =>
   println(s"${vocabArray(term.toInt)}\t$weight")
  }
  println()
  }
