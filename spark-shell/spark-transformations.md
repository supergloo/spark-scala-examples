Test

```scala
scala> grouped.collect().foreach(println)
(orange,CompactBuffer(5))
(apple,CompactBuffer(1, 3))
(banana,CompactBuffer(2, 4))

scala>     val reduced = data.reduceByKey((x, y) => x + y)
reduced: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[9] at reduceByKey at <console>:23

scala> reduced.collect().foreach(println)
(orange,5)
(apple,4)
(banana,6)

scala> val agg = data.reduceByKey((x, y) => x + y)
agg: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[10] at reduceByKey at <console>:23

scala> agg.collect().foreach(println)
(orange,5)
(apple,4)
(banana,6)
```
