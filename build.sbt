import ReleaseTransformations._

val ScalaVersion = "2.13.0"
val Slf4jVersion = "1.7.25"
val LogbackVersion = "1.2.3"
val AkkaVersion = "2.5.23"
val Json4sVersion = "3.6.6"
val ConfigVersion = "1.3.4"
val LogbackHoconVersion = "0.1.7"
val ScalaTestVersion = "3.0.8"

lazy val root = (project in file("."))
  .enablePlugins(ReleasePlugin, ScalafmtPlugin)
  .settings(
    name := "scala-structlog",
    organization := "com.github.mwegrz",
    crossScalaVersions := Seq(ScalaVersion, "2.12.8"),
    scalaVersion := crossScalaVersions.value.head,
    scalacOptions ++=
      (CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, n)) if n >= 13 => Seq("-Xsource:2.14")
        case _ => Seq("-Yno-adapted-args", "-deprecation")
      }),
    resolvers += "Sonatype Maven Snapshots" at "https://oss.sonatype.org/content/repositories/releases",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value, // Using `scalaVersion` directly so it cross-compiles correctly
      "org.slf4j" % "slf4j-api" % Slf4jVersion % Optional,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Optional,
      "com.typesafe.akka" %% "akka-actor" % AkkaVersion % Optional,
      "org.json4s" %% "json4s-native" % Json4sVersion % Optional,
      "com.typesafe" % "config" % ConfigVersion % Test,
      "com.github.mwegrz" % "logback-hocon" % LogbackHoconVersion % Test,
      "org.scalatest" %% "scalatest" % ScalaTestVersion % Test
    ),
    scalafmtOnCompile := true,
    // Release settings
    releaseCrossBuild := false,
    releaseTagName := { (version in ThisBuild).value },
    releaseTagComment := s"Release version ${(version in ThisBuild).value}",
    releaseCommitMessage := s"Set version to ${(version in ThisBuild).value}",
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      releaseStepCommandAndRemaining("+test"),
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      releaseStepCommandAndRemaining("+publishSigned"),
      setNextVersion,
      commitNextVersion,
      releaseStepCommandAndRemaining("+sonatypeReleaseAll"),
      pushChanges
    ),
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    // Publish settings
    autoScalaLibrary := true,
    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ =>
      false
    },
    licenses := Seq("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("http://github.com/mwegrz/scala-structlog")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/mwegrz/scala-structlog.git"),
        "scm:git@github.com:mwegrz/scala-structlog.git"
      )
    ),
    developers := List(
      Developer(
        id = "mwegrz",
        name = "Michał Węgrzyn",
        email = null,
        url = url("http://github.com/mwegrz")
      )
    )
  )
