import sbt._

ThisBuild / scalaVersion := "2.12.7"
////
resolvers += "redshift" at "http://redshift-maven-repository.s3-website-us-east-1.amazonaws.com/release"
////
////val agentDependencies: Seq[ModuleID] = Seq(
////  "com.typesafe.play" %% "anorm" % "2.4.0"
////  "com.microsoft.sqlserver" % "mssql-jdbc" % "6.2.2.jre8",
////  "com.amazon.redshift" % "redshift-jdbc42" % "1.2.10.1009",
////  "com.amazonaws" % "aws-java-sdk" % "1.11.232"
////)


libraryDependencies ++= Seq(
  "org.playframework.anorm" %% "anorm" % "2.6.2",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "7.0.0.jre8",
  "com.amazon.redshift" % "redshift-jdbc42" % "1.2.10.1009",
  "org.postgresql" % "postgresql" % "42.2.5",
  "net.snowflake" % "snowflake-jdbc" % "3.6.26",
  "com.typesafe" % "config" % "1.3.3",
  "com.zaxxer" % "HikariCP" % "3.3.1"
)