libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.6")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
resolvers += "Sonatype Maven Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
addSbtPlugin("com.github.mwegrz" % "sbt-logback" % "0.1.0-SNAPSHOT")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0-M1")
