package ua.com.itproekt.gup.server.api.rest.calendar

import java.text.SimpleDateFormat
import java.util.Date
import javax.validation.Valid

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, MediaType, HttpStatus}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import ua.com.itproekt.gup.model.offer.Offer
import ua.com.itproekt.gup.server.api.rest.offers.RentTest
import ua.com.itproekt.gup.service.offers.{PriceOfRents, OfferPricesServiceImpl, OffersServiceImpl, OffersService}
import ua.com.itproekt.gup.util.ConvertUtil

@RequestMapping(Array("/api/rest/calendarService"))
@Controller
class CalendarPricesRestController {
  private val logger = LoggerFactory.getLogger(classOf[CalendarPricesRestController])
  private val formatter: String = "d.MM.yyyy"
  private val simpleDateFormat: SimpleDateFormat = new SimpleDateFormat(formatter)

  var offersServiceImpl: OffersServiceImpl = _
  @Autowired def setOffersServiceImpl(offersServiceImpl: OffersServiceImpl) = this.offersServiceImpl = offersServiceImpl

  var offerPricesServiceImpl: OfferPricesServiceImpl = _
  @Autowired def setOfferPricesServiceImpl(offerPricesServiceImpl: OfferPricesServiceImpl) = this.offerPricesServiceImpl = offerPricesServiceImpl

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/offer/57f37a5e6032233325b9f8c9/price
   * method: GET
   * @return Status: 200 OK | Text: {...}
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(value = Array("/offer/{offerId}/price"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def viewOfferPrices(@PathVariable offerId: String): ResponseEntity[String] = {
    if( !(offersServiceImpl offerExists(offerId)) ) new ResponseEntity(HttpStatus.NOT_FOUND)
    else new ResponseEntity( new OfferPricesServiceImpl(offersServiceImpl findById(offerId) getMonthOfPrices) toJson, HttpStatus.OK )
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/offer/57f37a5e6032233325b9f8c9/price
   * method: POST
   *   body: { "specialPrice":{"name": "scheme4","price": 11111,"days": []} }
   * @return Status: 201 Created | Text: {...}
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(value = Array("/offer/{offerId}/price"), method = Array(RequestMethod.POST), consumes = Array(MediaType.APPLICATION_JSON_VALUE))
  def createOfferPrices(@PathVariable offerId: String, @RequestBody monthOfPrices: PriceOfRents): ResponseEntity[String] = {
    if( !(offersServiceImpl offerExists(offerId)) ) new ResponseEntity(HttpStatus.NOT_FOUND)
    else (monthOfPrices getWeekendPrice, monthOfPrices getSpecialPrice, monthOfPrices getSpecialPrice) match {case null => new ResponseEntity(HttpStatus.BAD_REQUEST)}
    offerPricesServiceImpl = new OfferPricesServiceImpl(monthOfPrices getWeekdayPrice, monthOfPrices getWeekendPrice)                   // Устанавливаем дефолтную цену (на будни и выходные дни)
    offerPricesServiceImpl addPrices(monthOfPrices.getSpecialPrice getPrice, ConvertUtil toDate(monthOfPrices.getSpecialPrice getDays)) // Устанавливаем специальную цену на отдельные дни
    offersServiceImpl edit(offersServiceImpl findById(offerId) setMonthOfPrices(offerPricesServiceImpl toRestore))
    new ResponseEntity(offerPricesServiceImpl toJson, HttpStatus.CREATED)
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/ping
   * method: GET
   * @return Status: 200 OK | Text: pong || DEBUG: ua.com.itproekt.gup.server.api.rest.samples.CalendarPricesRestController - Got valid GET: ...
   */
  @RequestMapping(Array("/ping"))
  @ResponseBody
  def ping(): String = {
    logger.debug("Got valid GET: {}", "pong")
    "pong";
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/echo?name=Alex
   * method: GET
   * @return Status: 200 OK | Text: { "name": "Alex", "weekDay": "ср" } || DEBUG: ua.com.itproekt.gup.server.api.rest.samples.CalendarPricesRestController - Got valid GET: ...
   */
  @RequestMapping(Array("/echo"))
  @ResponseBody
  def echo(name: String): CalendarEcho = {
    logger.debug("Got valid GET: {}", name)
    CalendarEcho(name, weekDay())
  }

  protected def weekDay(): String = {
    new SimpleDateFormat("E").format(new Date())
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/data
   * method: POST
   *   body: { "name": "Alex", "age": 23 }
   * @return Status: 200 OK || DEBUG: ua.com.itproekt.gup.server.api.rest.samples.CalendarPricesRestController - Got valid POST: CalendarData(Alex,23)
   */
  @RequestMapping(value = Array("/data"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.OK) def receiveData(@RequestBody inData: CalendarData): Unit = {
    logger.debug("Got valid POST: {}", inData)
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/dataannotated
   * method: POST
   *   body: { "name": "Alex", "age": 23 }
   * @return Status: 200 OK || DEBUG: ua.com.itproekt.gup.server.api.rest.samples.CalendarPricesRestController - Got valid POST: CalendarDataAnnotated(Alex,23)
   */
  @RequestMapping(value = Array("/dataannotated"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.OK) def receiveDataAnnotated(@RequestBody @Valid inData: CalendarDataAnnotated): Unit = {
    logger.debug("Got valid POST: {}", inData)
  }

}
