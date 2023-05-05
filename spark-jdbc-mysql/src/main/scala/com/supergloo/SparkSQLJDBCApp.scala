package com.supergloo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Spark JDBC Examples
  */
object SparkSQLJDBCApp {
 
  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("JDBC Data Source Example")
      .setIfMissing("spark.master", "local[*]")

    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    // Set JDBC connection properties
    val jdbcUrl = "jdbc:mysql://localhost/supergloo"
    val jdbcUser = "root"
    val jdbcPassword = "example"
    val jdbcDriverClass = "com.mysql.cj.jdbc.Driver"
    val jdbcReadTable = "baby_names"
    val jdbcWriteTable = "popular_baby_names"


    // hardcoded for simplicity for Spark read jdbc
    val jdbcOptions = Map(
      "url" -> jdbcUrl,
      "user" -> jdbcUser,
      "password" -> jdbcPassword,
      "driver" -> jdbcDriverClass,
      "dbtable" -> jdbcReadTable
    )

    // hardcoded for now for Spark write jdbc
    val jdbcWriteOptions = Map(
      "url" -> jdbcUrl,
      "user" -> jdbcUser,
      "password" -> jdbcPassword,
      "driver" -> jdbcDriverClass,
      "dbtable" -> jdbcWriteTable
    )

    // Read data from the JDBC source
    val df = spark.read.format("jdbc").options(jdbcOptions).load()
    df.createOrReplaceTempView("baby_names")

    // Show the data -- wouldn't do this in prop
    df.show()

    val pop = spark.sql("select * from baby_names order by Count desc limit 3")

    // Write data to a JDBC source from Spark
    pop.write.format("jdbc").options(jdbcWriteOptions).saveAsTable("popular_baby_names")

    spark.stop()
  }
 
}