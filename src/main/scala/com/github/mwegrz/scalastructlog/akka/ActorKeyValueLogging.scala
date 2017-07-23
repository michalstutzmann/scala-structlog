package com.github.mwegrz.scalastructlog.akka

import akka.actor.Actor
import com.github.mwegrz.scalastructlog.KeyValueLogger

/**
  * Mix in ActorKeyValueLogging into your Actor to easily obtain a reference to a key-value logger,
  * which is available under the name "log".
  *
  * {{{
  * class MyActor extends Actor with ActorKeyValueLogging {
  *   def receive = {
  *     case "pigdog" => log.info("We've got yet another pigdog on our hands")
  *   }
  * }
  * }}}
  */
class ActorKeyValueLogging { this: Actor =>
  private var _log: KeyValueLogger = _

  protected def log: KeyValueLogger = {
    // only used in Actor, i.e. thread safe
    // if (_log eq null) {
    _log = KeyValueLogger(new AkkaKeyValueAdapter(akka.event.Logging.withMarker(this)))
    _log
  }
}
