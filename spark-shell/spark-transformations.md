
## Spark Transformation Examples

The following examples of Spark transformations are taken from the tutorial [Spark Transformations in Scala Tutorial](https://supergloo.com/spark-scala/spark-transformations)

```scala
scala> val data = spark.sparkContext.parallelize(Seq(("apple", 1), ("banana", 2), ("apple", 3), ("banana", 4), ("orange", 5)))
data: org.apache.spark.rdd.RDD[(String, Int)] = ParallelCollectionRDD[7] at parallelize at <console>:22

scala> val grouped = data.groupByKey()
grouped: org.apache.spark.rdd.RDD[(String, Iterable[Int])] = ShuffledRDD[8] at groupByKey at <console>:23

scala> grouped.collect().foreach(println)
(orange,CompactBuffer(5))
(apple,CompactBuffer(1, 3))
(banana,CompactBuffer(2, 4))

scala>val reduced = data.reduceByKey((x, y) => x + y)
reduced: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[9] at reduceByKey at <console>:23

scala> reduced.collect().foreach(println)
(orange,5)
(apple,4)
(banana,6)

scala> val agg = data.aggregateByKey(0)((k,v) => v.toInt+k, (v,k) => k+v)
agg: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[43] at aggregateByKey at <console>:23

scala> agg.foreach(println)
(banana,6)
(orange,5)
(apple,4)

scala> agg.sortByKey().foreach(println)
(apple,4)
(banana,6)
(orange,5)

scala> agg.sortByKey().collect().foreach(println)
(apple,4)
(banana,6)
(orange,5)

scala> agg.sortByKey(false).collect().foreach(println)
(orange,5)
(banana,6)
(apple,4)

```
