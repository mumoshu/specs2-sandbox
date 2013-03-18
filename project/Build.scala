import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName         = "play2-memcached"
  val appVersion      = "0.1-SNAPSHOT"
  val baseName        = "sogelog"

  lazy val root = Project("root", base = file("."))
    .aggregate(specs2_9, specs2_11)

  lazy val common = Project("common", base = file("common")).settings(
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.10" % "test",
      "org.mockito" % "mockito-all" % "1.9.0" % "test",
      "org.hamcrest" % "hamcrest-all" % "1.3" % "test"
    )
  )

  lazy val specs2_9 = Project("specs2_9", base = file ("1.9")).settings(
    organization := "com.github.mumoshu.specs2.specs2_9",
    version := "0.2.8",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.9" % "test"
    )
  )

  lazy val specs2_1_10 = Project("specs2_1_10", base = file(".")).settings(
    crossScalaVersions := Seq("2.9.2", "2.9.1"),
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.10" % "test",
      "org.mockito" % "mockito-all" % "1.9.0" % "test",
      "org.hamcrest" % "hamcrest-all" % "1.3" % "test"
    ),
    sourceDirectory in Test <<= baseDirectory / "1.9/src/test",
    scalaSource in Test <<= baseDirectory / "1.9/src/test/scala",
    sourceDirectory in Compile <<= baseDirectory / "1.9/src/main",
    scalaSource in Compile <<= baseDirectory / "1.9/src/main/scala"
  ).dependsOn(common % "test->test;compile->compile")

  lazy val specs2_11 = Project("specs2_11", base = file ("1.11")).settings(
    organization := "com.github.mumoshu.specs2.specs2_11",
    version := "0.2.8",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.11" % "test"
    )
  )

  lazy val specs2_12 = Project("specs2_12", base = file ("1.12")).settings(
    organization := "com.github.mumoshu.specs2.specs2_12",
    version := "0.2.8",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "1.12.3" % "test",
      "org.mockito" % "mockito-all" % "1.9.0" % "test",
      "org.hamcrest" % "hamcrest-all" % "1.1" % "test"
    )
  )
}
