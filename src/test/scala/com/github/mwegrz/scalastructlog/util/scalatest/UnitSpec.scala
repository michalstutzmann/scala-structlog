package netemera.util.scalatest

import org.scalatest.prop.PropertyChecks
import org.scalatest.{ FunSpec, GivenWhenThen }

class UnitSpec extends FunSpec with GivenWhenThen with PropertyChecks
