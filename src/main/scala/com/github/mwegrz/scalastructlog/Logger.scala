package com.github.mwegrz.scalastructlog

import com.github.mwegrz.scalastructlog.slf4j.Slf4jAdapter

import scala.reflect.ClassTag
import scala.reflect.macros.blackbox

class Logger(val underlying: Adapter) {
  import Logger.Tags
  import Logger.Macros._
  import language.experimental.macros

  // Info
  def info(message: String): Unit = macro infoMessage

  def info[A <: Throwable](message: String, cause: A): A = macro infoMessageCause

  def info(tags: Tags, message: String): Unit = macro infoTagsMessage

  def info[A <: Throwable](tags: Tags, message: String, cause: A): A = macro infoTagsMessageCause

  // Debug
  def debug(message: String): Unit = macro debugMessage

  def debug[A <: Throwable](message: String, cause: A): A = macro debugMessageCause

  def debug(tags: Tags, message: String): Unit = macro debugTagsMessage

  def debug[A <: Throwable](tags: Tags, message: String, cause: A): A = macro debugTagsMessageCause

  // Warning
  def warning(message: String): Unit = macro warningMessage

  def warning[A <: Throwable](message: String, cause: A): A = macro warningMessageCause

  def warning(tags: Tags, message: String): Unit = macro warningTagsMessage

  def warning[A <: Throwable](tags: Tags, message: String, cause: A): A = macro warningTagsMessageCause

  // Error
  def error(message: String): Unit = macro errorMessage

  def error[A <: Throwable](message: String, cause: A): A = macro errorMessageCause

  def error(tags: Tags, message: String): Unit = macro errorTagsMessage

  def error[A <: Throwable](tags: Tags, message: String, cause: A): A = macro errorTagsMessageCause
}

object Logger {
  def apply[T](c: Class[T]): Logger = new Logger(new Slf4jAdapter(c))

  def apply[T]()(implicit ct: ClassTag[T]): Logger = new Logger(new Slf4jAdapter(ct.runtimeClass))

  case class Tag(name: String)

  sealed trait Tags {
    val elems: Product

    def toList: List[Tag] = List(elems.productIterator.map(_.asInstanceOf[Tag]).toList: _*)
  }

  implicit def stringToTag(string: String): Tag = Tag(string)

  implicit def tagToTags(tag: Tag): Tags = Tuple1(tag)

  implicit class Tags1(override val elems: Tuple1[Tag]) extends Tags

  implicit class Tags2(override val elems: (Tag, Tag)) extends Tags

  implicit class Tags3(override val elems: (Tag, Tag, Tag)) extends Tags

  private object Macros {
    type C = blackbox.Context { type PrefixType = Logger }

    // Info
    def infoMessage(c: C)(message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isInfoEnabled) $l.info($message)"
    }

    def infoMessageCause(c: C)(message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isInfoEnabled) $l.info($message, $cause)
          $cause
       """
    }

    def infoTagsMessage(c: C)(tags: c.Expr[Tags], message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isInfoEnabled) $l.info($tags.toList, $message)"
    }

    def infoTagsMessageCause(c: C)(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isInfoEnabled) $l.info($tags.toList, $message, $cause)
          $cause
       """
    }

    // Debug
    def debugMessage(c: C)(message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isDebugEnabled) $l.debug($message)"
    }

    def debugMessageCause(c: C)(message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isDebugEnabled) $l.debug($message, $cause)
          $cause
       """
    }

    def debugTagsMessage(c: C)(tags: c.Expr[Tags], message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isDebugEnabled) $l.debug($tags.toList, $message)"
    }

    def debugTagsMessageCause(c: C)(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isDebugEnabled) $l.debug($tags.toList, $message, $cause)
          $cause
       """
    }

    // Warning
    def warningMessage(c: C)(message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isWarningEnabled) $l.warning($message)"
    }

    def warningMessageCause(c: C)(message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isWarningEnabled) $l.warning($message, $cause)
          $cause
       """
    }

    def warningTagsMessage(c: C)(tags: c.Expr[Tags], message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isWarningEnabled) $l.warning($tags.toList, $message)"
    }

    def warningTagsMessageCause(c: C)(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isWarningEnabled) $l.warning($tags.toList, $message, $cause)
          $cause
       """
    }

    // Error
    def errorMessage(c: C)(message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isErrorEnabled) $l.error($message)"
    }

    def errorMessageCause(c: C)(message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isErrorEnabled) $l.error($message, $cause)
          $cause
       """
    }

    def errorTagsMessage(c: C)(tags: c.Expr[Tags], message: c.Expr[String]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"if ($l.isErrorEnabled) $l.error($tags.toList, $message)"
    }

    def errorTagsMessageCause(c: C)(tags: c.Expr[Tags], message: c.Expr[String], cause: c.Expr[Throwable]) = {
      import c.universe._
      val l = q"${c.prefix}.underlying"
      q"""if ($l.isErrorEnabled) $l.error($tags.toList, $message, $cause)
          $cause
       """
    }
  }
}
