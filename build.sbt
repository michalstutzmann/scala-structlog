import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences.{DoubleIndentClassDeclaration, PlaceScaladocAsterisksBeneathSecondAsterisk}

val ScalaVersion = "2.11.8"
val Slf4jVersion = "1.7.18"
val LogbackVersion = "1.1.6"
val AkkaVersion = "2.4.3"
val Json4sVersion = "3.3.0"
val ConfigVersion = "1.3.0"
val LogbackHoconVersion = "0.1.0-SNAPSHOT"

lazy val root = (project in file(".")).
  enablePlugins(GitBranchPrompt, ReleasePlugin, SbtScalariform).
  settings(
    name := "scala-structlog",
    organization := "com.github.mwegrz",
    scalaVersion := ScalaVersion,
    resolvers += "Sonatype Maven Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % ScalaVersion,
      "org.slf4j" % "slf4j-api" % Slf4jVersion % Optional,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Optional,
      "com.typesafe.akka" %% "akka-actor" % AkkaVersion % Optional,
      "org.json4s" %% "json4s-native" % Json4sVersion % Optional,
      "com.typesafe" % "config" % ConfigVersion % Test,
      "com.github.mwegrz" % "logback-hocon" % LogbackHoconVersion % Test
    ),
    // Publishing
    publishMavenStyle := true,
    crossPaths := true,
    autoScalaLibrary := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    pomExtra := (
      <url>http://github.com/mwegrz/scala-structlog</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>https://opensource.org/licenses/MIT</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:mwegrz/scala-structlog.git</url>
          <connection>scm:git:git@github.com:mwegrz/scala-structlog.git</connection>
        </scm>
        <developers>
          <developer>
            <id>mwegrz</id>
            <name>Michał Węgrzyn</name>
            <url>http://github.com/mwegrz</url>
          </developer>
        </developers>),
    releaseTagComment := s"Released ${(version in ThisBuild).value}",
    releaseCommitMessage := s"Set version to ${(version in ThisBuild).value}",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
  )
