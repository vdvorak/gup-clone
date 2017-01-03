package ua.com.itproekt.gup.server.api.rest.samples

import java.text.SimpleDateFormat
import java.util.Date
import javax.validation.Valid

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@RequestMapping(Array("/scala"))
@Controller
class CalendarController {

  private val logger = LoggerFactory.getLogger(classOf[CalendarController])

  @RequestMapping(Array("/ping"))
  @ResponseBody
  def ping(): String = {
    "pong";
  }

  protected def weekDay(): String = {
    new SimpleDateFormat("E").format(new Date())
  }

  @RequestMapping(value = Array("/indata"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.OK) def receiveData(@RequestBody @Valid inData: CalendarData): Unit = {
    logger.debug("Got valid POSTed data: {}", inData)
  }

}
