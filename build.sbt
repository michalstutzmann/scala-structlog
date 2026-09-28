val ScalaVersion = "2.13.18"
val Slf4jVersion = "1.7.36"
val LogbackVersion = "1.2.13"
val AkkaVersion = "2.6.21"
val Json4sVersion = "3.6.7"
val ConfigVersion = "1.4.9"
val LogbackHoconVersion = "0.2.0"
val ScalaTestVersion = "3.0.8"

lazy val root = (project in file("."))
  .enablePlugins(ScalafmtPlugin)
  .settings(
    name := "scala-structlog",
    organization := "com.github.mwegrz",
    scalaVersion := ScalaVersion,
    scalacOptions ++= Seq("-deprecation"),
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
    // Publish settings
    publishTo := {
      if (isSnapshot.value) Some("central-snapshots" at "https://central.sonatype.com/repository/maven-snapshots/")
      else localStaging.value
    },
    publishMavenStyle := true,
    Test / publishArtifact := false,
    pomIncludeRepository := { _ =>
      false
    },
    licenses := Seq("Apache License, Version 2.0" -> uri("https://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(uri("https://github.com/michalstutzmann/scala-structlog")),
    scmInfo := Some(
      ScmInfo(
        uri("https://github.com/michalstutzmann/scala-structlog"),
        "scm:git@github.com:michalstutzmann/scala-structlog.git"
      )
    ),
    developers := List(
      Developer(
        id = "michalstutzmann",
        name = "Michal Stutzmann",
        email = null,
        url = uri("https://github.com/michalstutzmann")
      )
    )
  )
