package com.github.mwegrz.scalastructlog.logback

import java.time.Instant
import ch.qos.logback.classic.pattern.RootCauseFirstThrowableProxyConverter
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.LayoutBase
import com.github.mwegrz.scalastructlog.slf4j.JsonObjectMarker
import org.json4s.JObject
import org.json4s.JsonAST.JField
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import scala.collection.JavaConverters._

class JsonLayout extends LayoutBase[ILoggingEvent] {
  import JsonLayout._

  override def doLayout(event: ILoggingEvent): String = {
    val time = Instant.ofEpochMilli(event.getTimeStamp).toString
    val message = event.getMessage
    val level = event.getLevel.levelStr.toLowerCase
    val logger = event.getLoggerName
    val stacktrace = throwableConverter.convert(event)
    val baseJson = ("time" -> time) ~ ("level" -> level) ~ ("logger" -> logger) ~ ("message" -> message)
    val mdcJson = JObject(event.getMDCPropertyMap.asScala.map(a => JField(a._1, a._2)).toList)
    val markerJson = event.getMarker match {
      case m: JsonObjectMarker => m.value
      case _ => JObject()
    }
    val stackTraceJson: JObject = if (stacktrace.nonEmpty) "stack-trace'" -> stacktrace else JObject()
    compact(render(baseJson.obj ++ mdcJson.obj ++ markerJson.obj ++ stackTraceJson.obj)) + "\n"
  }
}

object JsonLayout {
  private val throwableConverter = new RootCauseFirstThrowableProxyConverter
  throwableConverter.start()
}
