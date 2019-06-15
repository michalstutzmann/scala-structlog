package com.github.mwegrz.scalastructlog

import scala.reflect.ClassTag
import scala.reflect.macros.blackbox
import Logger.Tags
import com.github.mwegrz.scalastructlog.slf4j.Slf4jJsonAdapter

object JsonLogger {
  import language.implicitConversions

  type JsonObject = org.json4s.JsonAST.JObject

  def apply[T](c: Class[T]): JsonLogger = new JsonLogger(new Slf4jJsonAdapter(c))

  def apply[T]()(implicit ct: ClassTag[T]): JsonLogger = new JsonLogger(new Slf4jJsonAdapter(ct.runtimeClass))

  private object Macros {
    type C = blackbox.Context { type PrefixType = JsonLogger }

    // Info
    def infoMessageContext(c: C)(message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isInfoEnabled) $l.info($message, $context)"
    }

    def infoTagMessageContext(c: C)(tags: c.Expr[Tags], message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isInfoEnabled) $l.info($tags.toList, $message, $context)"
    }

    def infoMessageCauseContext(
        c: C
    )(message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isInfoEnabled) $l.info($message, $cause, $context)
          $cause
       """
    }

    def infoTagMessageCauseContext(
        c: C
    )(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isInfoEnabled) $l.info($tags.toList, $message, $cause, $context)
         $cause
       """
    }

    // Debug
    def debugMessageContext(c: C)(message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isDebugEnabled) $l.debug($message, $context)"
    }

    def debugTagMessageContext(c: C)(tags: c.Expr[Tags], message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isDebugEnabled) $l.debug($tags.toList, $message, $context)"
    }

    def debugMessageCauseContext(
        c: C
    )(message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isDebugEnabled) $l.debug($message, $cause, $context)
          $cause
       """
    }

    def debugTagMessageCauseContext(
        c: C
    )(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isDebugEnabled) $l.debug($tags.toList, $message, $cause, $context)
         $cause
       """
    }

    // Warning
    def warningMessageContext(c: C)(message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isWarningEnabled) $l.warning($message, $context)"
    }

    def warningTagMessageContext(c: C)(tags: c.Expr[Tags], message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isWarningEnabled) $l.warning($tags.toList, $message, $context)"
    }

    def warningMessageCauseContext(
        c: C
    )(message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isWarningEnabled) $l.warning($message, $cause, $context)
          $cause
       """
    }

    def warningTagMessageCauseContext(
        c: C
    )(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isWarningEnabled) $l.warning($tags.toList, $message, $cause, $context)
         $cause
       """
    }

    // Error
    def errorMessageContext(c: C)(message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isErrorEnabled) $l.error($message, $context)"
    }

    def errorTagMessageContext(c: C)(tags: c.Expr[Tags], message: c.Expr[String], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isErrorEnabled) $l.error($tags.toList, $message, $context)"
    }

    def errorMessageCauseContext(
        c: C
    )(message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isErrorEnabled) $l.error($message, $cause, $context)
          $cause
       """
    }

    def errorTagMessageCauseContext(
        c: C
    )(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable], context: c.Expr[JsonObject]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isErrorEnabled) $l.error($tags.toList, $message, $cause, $context)
         $cause
       """
    }
  }
}

class JsonLogger private (override val underlying: JsonAdapter) extends Logger(underlying) {
  import JsonLogger.JsonObject
  import JsonLogger.Macros
  import language.experimental.macros

  // Info
  def info(message: String, context: JsonObject): Unit = macro Macros.infoMessageContext

  def info(tags: Tags, message: String, context: JsonObject): Unit = macro Macros.infoTagMessageContext

  def info[A <: Throwable](message: String, cause: A, context: JsonObject): A = macro Macros.infoMessageCauseContext

  def info[A <: Throwable](tags: Tags, message: String, cause: A, context: JsonObject): A =
    macro Macros.infoTagMessageCauseContext

  // Debug

  def debug(message: String, context: JsonObject): Unit = macro Macros.debugMessageContext

  def debug(tags: Tags, message: String, context: JsonObject): Unit = macro Macros.debugTagMessageContext

  def debug[A <: Throwable](message: String, cause: A, context: JsonObject): A = macro Macros.debugMessageCauseContext

  def debug[A <: Throwable](tags: Tags, message: String, cause: A, context: JsonObject): A =
    macro Macros.debugTagMessageCauseContext

  // Warning
  def warning(message: String, context: JsonObject): Unit = macro Macros.warningMessageContext

  def warning(tags: Tags, message: String, context: JsonObject): Unit = macro Macros.warningTagMessageContext

  def warning[A <: Throwable](message: String, cause: A, context: JsonObject): A =
    macro Macros.warningMessageCauseContext

  def warning[A <: Throwable](tags: Tags, message: String, cause: A, context: JsonObject): A =
    macro Macros.warningTagMessageCauseContext

  // Error
  def error(message: String, context: JsonObject): Unit = macro Macros.errorMessageContext

  def error(tags: Tags, message: String, context: JsonObject): Unit = macro Macros.errorTagMessageContext

  def error[A <: Throwable](message: String, cause: A, context: JsonObject): A = macro Macros.errorMessageCauseContext

  def error[A <: Throwable](tags: Tags, message: String, cause: A, context: JsonObject): A =
    macro Macros.errorTagMessageCauseContext
}
