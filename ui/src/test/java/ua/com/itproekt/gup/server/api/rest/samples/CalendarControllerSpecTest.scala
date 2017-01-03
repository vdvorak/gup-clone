package ua.com.itproekt.gup.server.api.rest.samples

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.specs2.mutable.Specification

@RunWith(classOf[JUnitRunner])
class CalendarControllerSpecTest extends Specification {

  "Calling CalendarController.ping" should {
    "return pong" in {
      new CalendarController().ping() must be equalTo "pong"
    }
  }

}
