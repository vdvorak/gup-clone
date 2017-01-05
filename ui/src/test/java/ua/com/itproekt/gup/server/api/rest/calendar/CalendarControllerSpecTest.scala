package ua.com.itproekt.gup.server.api.rest.calendar

import java.text.SimpleDateFormat
import java.util.Date

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import org.specs2.mutable.Specification


/**
* ScalaController SpecTest
* ************************
* InvocationTargetException for 'Start' in MyNotifierRunner; cause: org/specs2/specification/TagsAssociation
*/

@RunWith(classOf[JUnitRunner])
class CalendarControllerSpecTest extends Specification {

  val logger = LoggerFactory.getLogger(getClass)

  /**
   * Calling CalendarController.ping should
   * return pong
   */
  "Calling CalendarController.ping" should {
    "return pong" in {
      new CalendarController().ping() must be equalTo "pong"
    }
  }

  /**
   * Calling CalendarController.echo should
   * return echo name and week day
   * DEBUG: ua.com.itproekt.gup.server.api.rest.calendar.CalendarControllerSpecTest - CalendarEcho was: CalendarEcho(Daniel,ср)
   */
  "Calling CalendarController.echo" should {
    "echo name and week day" in {
      val weekDay = new SimpleDateFormat("E").format(new Date());
      val echo: CalendarEcho = new CalendarController().echo("Daniel")
      echo.name must be equalTo "Daniel"
      echo.weekDay must be equalTo weekDay

      logger.debug("CalendarEcho was: {}", echo)
      success
    }
  }

}
