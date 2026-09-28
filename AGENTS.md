# AGENTS.md

## Project

`scala-structlog` is a Scala 2.13 structured-logging library. It has one sbt project (sbt 2) and publishes to Maven
Central as `com.github.mwegrz:scala-structlog`.

## Layout

- `src/main/scala/com/github/mwegrz/scalastructlog/`: the core API.
  - `Logger`, `KeyValueLogger`, `JsonLogger`: the loggers. Their log methods are blackbox **macros**, so level
    checks happen at the call site. Keep new overloads consistent across `debug`/`info`/`warning`/`error`.
  - `Logging`, `KeyValueLogging`, `JsonLogging`: mixins that provide `log`.
  - `Adapter`, `KeyValueAdapter`, `JsonAdapter`: backend abstraction.
- `slf4j/`: SLF4J adapters. Context is passed through markers (`KeyValueMapMarker`, `JsonObjectMarker`).
- `logback/`: `KeyValueLayout` and `JsonLayout`.
- `akka/`: the Akka adapter and `ActorKeyValueLogging`.
- `src/test/`: ScalaTest specs (`UnitSpec` base). Logback is configured via `application.conf` (logback-hocon).

## Conventions

- Scala 2 macros depend on `scala-reflect`, so don't migrate to Scala 3 syntax.
- Backend libraries (slf4j, logback, akka, json4s) are `Optional` dependencies. Don't make them required.
- Formatting follows `.scalafmt.conf` (max 120 columns).
- The package stays `com.github.mwegrz.scalastructlog` for compatibility with existing users.

## Commands

```sh
sbt compile
sbt test
sbt scalafmtAll          # format
sbt scalafmtCheckAll     # CI check
```

Run `sbt scalafmtCheckAll test` before you finish a change.

## Releases

Don't edit the version in `build.sbt`. CI (`./publish`) derives it from Git tags with `git-semver-release`, and
pushing a `vX.Y.Z` tag publishes to Maven Central. Don't create tags or push unless asked.
