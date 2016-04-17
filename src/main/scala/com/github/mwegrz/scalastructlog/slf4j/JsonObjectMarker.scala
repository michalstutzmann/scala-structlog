package com.github.mwegrz.scalastructlog.slf4j

import java.util
import com.github.mwegrz.scalastructlog.JsonLogger.JsonObject
import org.slf4j.Marker

case class JsonObjectMarker(value: JsonObject) extends Marker {
  override def hasChildren: Boolean = false

  override def getName: String = "JSON_OBJECT"

  override def remove(reference: Marker): Boolean = throw new UnsupportedOperationException

  override def contains(other: Marker): Boolean = false

  override def contains(name: String): Boolean = false

  override def iterator(): util.Iterator[Marker] = throw new UnsupportedOperationException

  override def add(reference: Marker): Unit = throw new UnsupportedOperationException

  override def hasReferences: Boolean = false
}
