# scala-structlog

Structured logging for Scala 2.13. Attach key-value or JSON context to log entries and render them with
Logback layouts. The library is built on SLF4J and also has an Akka adapter.

## Installation

```scala
libraryDependencies += "com.github.mwegrz" %% "scala-structlog" % "<version>"
```

Backend dependencies are marked `Optional`, so you add the ones you use:

```scala
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.36",
  "ch.qos.logback" % "logback-classic" % "1.2.13", // for KeyValueLayout / JsonLayout
  "org.json4s" %% "json4s-native" % "3.6.7",       // for JsonLogger / JsonLayout
  "com.typesafe.akka" %% "akka-actor" % "2.6.21"   // for the Akka adapter
)
```

## Usage

### Plain logger

```scala
import com.github.mwegrz.scalastructlog.{ Logger, Logging }

class Service extends Logging {
  log.info("Started")
}

val logger = Logger[Service]()
logger.warning("Something looks off")
```

### Key-value logger

Pass context as a tuple of up to 22 `key -> value` pairs:

```scala
import com.github.mwegrz.scalastructlog.{ KeyValueLogger, KeyValueLogging }

class Service extends KeyValueLogging {
  log.info("Request handled", ("path" -> "/users", "status" -> 200))
}

val logger = KeyValueLogger[Service]()
logger.error("Request failed", new Exception("Boom"), ("path" -> "/users", "status" -> 500))
```

The methods that take a `cause` return that throwable, so you can write `throw log.error("...", e, ctx)`.

### JSON logger

`JsonLogger` / `JsonLogging` take a json4s `JObject` as context.

### Levels and tags

Every logger has `debug`, `info`, `warning` and `error`. Each one comes in overloads with and without a `cause`
and with and without leading `tags`.

### Akka

To get a key-value logger backed by Akka's logging, mix `akka.ActorKeyValueLogging` into an actor.

## Logback layouts

- `com.github.mwegrz.scalastructlog.logback.KeyValueLayout` writes `time=... level=... logger=... message=... key=value`
  lines. The line includes MDC entries and key-value context.
- `com.github.mwegrz.scalastructlog.logback.JsonLayout` writes one JSON object per line.

Example (HOCON config via [logback-hocon](https://github.com/mwegrz/logback-hocon)):

```hocon
logback.appenders.console {
  class = "ch.qos.logback.core.ConsoleAppender"
  encoder {
    class = "ch.qos.logback.core.encoder.LayoutWrappingEncoder"
    layout.class = "com.github.mwegrz.scalastructlog.logback.KeyValueLayout"
  }
}
```

## Development

Requires JDK and sbt 2.

```sh
sbt scalafmtCheckAll test
```

## Releasing

GitHub Actions (`.github/workflows/publish.yml`) runs `./publish` on every push. Versions are derived from Git tags
via [git-semver-release](https://github.com/michalstutzmann/setup-git-semver-release). A release tag (`vX.Y.Z`)
publishes signed artifacts to Maven Central and creates a GitHub release. Any other push runs the format check and
the tests.

## License

Apache License, Version 2.0. See [LICENSE](LICENSE).
