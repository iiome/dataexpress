//assembly to package with dependencies ------------------------------

import AssemblyKeys._

assemblySettings

//standard options ------------------------------

name := "DataExpress"

version := "0.9.0.6"

organization := "edu.chop.cbmi"

scalaVersion := "2.10.0"

//assembly options

jarName in assembly <<=version("DataExpress_" + _ + "_standalone.jar")

test in assembly := {}

assembleArtifact in packageScala := false

//Need this for now until we unwind some of the tests
parallelExecution in Test := false

//compile dependencies------------------------------

libraryDependencies ++= Seq(
  "org.xerial" % "sqlite-jdbc" % "3.7.2",
  "postgresql" % "postgresql" % "9.0-801.jdbc4",
  "mysql" % "mysql-connector-java" % "5.1.21"
)


//test dependencies------------------------------

libraryDependencies ++= {
  val deps = Seq(
        "org.scalatest" %% "scalatest" % "2.0.M5b",
        "junit" % "junit" % "4.8.1"
      )
  deps map {v => v % "test"}
}

//scala options------------------------------

scalacOptions +="-language:dynamics"

//console imports------------------------------

initialCommands in console := """import edu.chop.cbmi.dataExpress.dsl.ETL
import edu.chop.cbmi.dataExpress.dsl.ETL._
import edu.chop.cbmi.dataExpress.dsl.stores.SqlDb
import edu.chop.cbmi.dataExpress.dataModels.RichOption._"""
