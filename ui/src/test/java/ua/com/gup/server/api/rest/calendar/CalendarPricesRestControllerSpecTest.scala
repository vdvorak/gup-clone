package ua.com.itproekt.gup.server.api.rest.calendar

import java.text.SimpleDateFormat
import java.util.Date

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import org.specs2.mutable.Specification


/**
* The SpecTest for CalendarPricesRest-Controller
*/

@RunWith(classOf[JUnitRunner])
class CalendarPricesRestControllerSpecTest extends Specification {

  val logger = LoggerFactory.getLogger(getClass)

  /**
   * Calling CalendarPricesRestController.ping should
   * return pong
   */
  "Calling CalendarPricesRestController.ping" should {
    "return pong" in {
      new CalendarPricesRestController().ping() must be equalTo "pong"
    }
  }

  /**
   * Calling CalendarController.echo should
   * return echo name and week day
   * DEBUG: ua.com.itproekt.gup.server.api.rest.calendar.CalendarPricesRestController - CalendarEcho was: CalendarEcho(Daniel,ср)
   */
  "Calling CalendarPricesRestController.echo" should {
    "echo name and week day" in {
      val weekDay = new SimpleDateFormat("E").format(new Date());
      val echo: CalendarEcho = new CalendarPricesRestController().echo("Daniel")
      echo.name must be equalTo "Daniel"
      echo.weekDay must be equalTo weekDay

      logger.debug("CalendarEcho was: {}", echo)
      success
    }
  }

}
