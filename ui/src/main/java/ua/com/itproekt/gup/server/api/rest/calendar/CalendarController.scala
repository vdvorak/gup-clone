package ua.com.itproekt.gup.server.api.rest.calendar

import java.text.SimpleDateFormat
import java.util.Date
import javax.validation.Valid

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@RequestMapping(Array("/calendar"))
@Controller
class CalendarController {

  private val logger = LoggerFactory.getLogger(classOf[CalendarController])

  /**
   *    URL: http://localhost:8184/calendar/ping
   * method: GET
   * @return Status: 200 OK | Text: pong
   */
  @RequestMapping(Array("/ping"))
  @ResponseBody
  def ping(): String = {
    "pong";
  }

  /**
   *    URL: http://localhost:8184/calendar/echo?name=Alex
   * method: GET
   * @return Status: 200 OK | Text: { "name": "Alex", "weekDay": "ср" }
   */
  @RequestMapping(Array("/echo"))
  @ResponseBody
  def echo(name: String): CalendarEcho = {
    CalendarEcho(name, weekDay())
  }

  protected def weekDay(): String = {
    new SimpleDateFormat("E").format(new Date())
  }

  /**
   *    URL: http://localhost:8184/calendar/data
   * method: POST
   *   body: { "name": "Alex", "age": 23 }
   * @return Status: 200 OK || DEBUG: ua.com.itproekt.gup.server.api.rest.samples.CalendarController - Got valid POST: CalendarData(Alex,23)
   */
  @RequestMapping(value = Array("/data"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.OK) def receiveData(@RequestBody inData: CalendarData): Unit = {
    logger.debug("Got valid POST: {}", inData)
  }

  /**
   *    URL: http://localhost:8184/calendar/dataannotated
   * method: POST
   *   body: { "name": "Alex", "age": 23 }
   * @return Status: 200 OK || DEBUG: ua.com.itproekt.gup.server.api.rest.samples.CalendarController - Got valid POST: CalendarDataAnnotated(Alex,23)
   */
  @RequestMapping(value = Array("/dataannotated"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.OK) def receiveDataAnnotated(@RequestBody @Valid inData: CalendarDataAnnotated): Unit = {
    logger.debug("Got valid POST: {}", inData)
  }

}