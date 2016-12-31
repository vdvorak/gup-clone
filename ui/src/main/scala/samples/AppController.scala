package samples

import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._
import org.slf4j.LoggerFactory
import java.util.Date
import javax.validation.Valid

@RequestMapping(Array("/scala"))
@Controller
class AppController {

  private val logger = LoggerFactory.getLogger(classOf[AppController])

  @RequestMapping(Array("/ping"))
  @ResponseBody
  def ping(): String = {
    "pong";
  }

  protected def weekDay(): String = {
    new SimpleDateFormat("E").format(new Date())
  }

  @RequestMapping(value = Array("/indata"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.OK) def receiveData(@RequestBody @Valid inData: AppIndata): Unit = {
    logger.debug("Got valid POSTed data: {}", inData)
  }

}
