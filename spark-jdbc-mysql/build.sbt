name := "spark-sql-examples"
 
version := "1.0"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "3.4.0" % "provided",
  "mysql" % "mysql-connector-java" % "8.0.33"
)
