package com.github.mwegrz.scalastructlog.slf4j

import java.util
import com.github.mwegrz.scalastructlog.KeyValueLogger.{Key, Value}
import org.slf4j.Marker

case class KeyValueMapMarker(value: Map[Key, Value]) extends Marker {
  override def hasChildren: Boolean = false

  override def getName: String = "KEY_VALUE_PAIR"

  override def remove(reference: Marker): Boolean = throw new UnsupportedOperationException

  override def contains(other: Marker): Boolean = false

  override def contains(name: String): Boolean = false

  override def iterator(): util.Iterator[Marker] = throw new UnsupportedOperationException

  override def add(reference: Marker): Unit = throw new UnsupportedOperationException

  override def hasReferences: Boolean = false
}
