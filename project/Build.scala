import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName         = "play2-memcached"
  val appVersion      = "0.1-SNAPSHOT"
  val baseName        = "sogelog"

  lazy val root = Project("root", base = file("."))
    .aggregate(specs2_9, specs2_11)

  lazy val specs2_9 = Project("specs2 1.9", base = file ("1.9")).settings(
    organization := "com.github.mumoshu.specs2.specs2_9",
    version := "0.2.8",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.9" % "test"
    )
  )

  lazy val specs2_11 = Project("specs2 1.11", base = file ("1.11")).settings(
    organization := "com.github.mumoshu.specs2.specs2_11",
    version := "0.2.8",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.11" % "test"
    )
  )
}
