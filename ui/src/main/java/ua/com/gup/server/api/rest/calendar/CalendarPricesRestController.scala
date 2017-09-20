package ua.com.gup.server.api.rest.calendar

import java.io.{FileNotFoundException, FileReader}
import java.text.SimpleDateFormat
import java.util.Date
import javax.validation.Valid

import com.google.gson.reflect.TypeToken
import com.google.gson.{Gson, JsonElement, JsonObject, JsonParser}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._
import ua.com.gup.domain.Offer
import ua.com.gup.service.offers.price.PriceOfRent
import ua.com.gup.service.offers.{OfferPricesServiceImpl, OffersServiceImpl, PriceOfRents}
import ua.com.gup.util.ConvertUtil

import scala.collection.mutable.HashMap

@RestController
@RequestMapping(Array("/api/rest/calendarService"))
class CalendarPricesRestController {
  private val logger = LoggerFactory.getLogger(classOf[CalendarPricesRestController])
  private val formatter = "d.MM.yyyy"
  private val simpleDateFormat = new SimpleDateFormat(formatter)
  private[this] val offerOctober,offerRents = "offerOctoberOfPrices.json";"offerRents.json"
  private[this] var objJsonMonth: JsonElement = null
  private[this] var objJsonRents: JsonObject = null

  private[this] var monthOfPrices: HashMap[String,PriceOfRent] = _
//  def setMonthOfPrices(key:String, value:PriceOfRent) = this.monthOfPrices = monthOfPrices

//  private[this] var rents: Map[String,RentTest] = _
//  def setRents(key:String, value:RentTest) = this.rents = rents

  private[this] var offersServiceImpl: OffersServiceImpl = _
  @Autowired def setOffersServiceImpl(offersServiceImpl: OffersServiceImpl) = this.offersServiceImpl = offersServiceImpl

  private[this] var offerPricesServiceImpl: OfferPricesServiceImpl = _
  @Autowired def setOfferPricesServiceImpl(offerPricesServiceImpl: OfferPricesServiceImpl) = this.offerPricesServiceImpl = offerPricesServiceImpl


  /**
   *    URL: http://localhost:8184/api/rest/calendarService/offer/587659524c8ef1b9713b5ca3/price
   * method: GET
    *
    * @return Status: 200 OK | Text: {...}
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(value = Array("/offer/{offerId}/price"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def viewOfferPrices(@PathVariable offerId: String): ResponseEntity[String] = {
    if( !(offersServiceImpl offerExists(offerId)) ) new ResponseEntity(HttpStatus NOT_FOUND)
    else new ResponseEntity( new OfferPricesServiceImpl(offersServiceImpl findById(offerId) ) toJson, HttpStatus OK )
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/offer/587659524c8ef1b9713b5ca3/price
   * method: POST
   *   body: { "specialPrice":{"name": "scheme4","price": 11111,"days": []} }
   * @return Status: 201 Created | Text: {...}
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(value = Array("/offer/{offerId}/price"), method = Array(RequestMethod.POST), consumes = Array(MediaType.APPLICATION_JSON_VALUE))
  def createOfferPrices(@PathVariable offerId: String, @RequestBody monthOfPrices: PriceOfRents): ResponseEntity[String] = {
    if( !(offersServiceImpl offerExists(offerId)) ) new ResponseEntity(HttpStatus NOT_FOUND)
    else if (monthOfPrices.getWeekendPrice() == null && monthOfPrices.getSpecialPrice() == null && monthOfPrices.getSpecialPrice() == null) new ResponseEntity(HttpStatus BAD_REQUEST)
    offerPricesServiceImpl = new OfferPricesServiceImpl(monthOfPrices getWeekdayPrice, monthOfPrices getWeekendPrice)                     // Устанавливаем дефолтную цену (на будни и выходные дни)
    offerPricesServiceImpl addPrices( monthOfPrices.getSpecialPrice getPrice, ConvertUtil toDate(monthOfPrices.getSpecialPrice getDays) ) // Устанавливаем специальную цену на отдельные дни
    offersServiceImpl edit(offersServiceImpl findById(offerId) setMonthOfPrices(offerPricesServiceImpl toRestore))
    new ResponseEntity( offerPricesServiceImpl toJson, HttpStatus CREATED )
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/offer/587659524c8ef1b9713b5ca3/price
   * method: PUT
   *   body: { "specialPrice":{"name":"scheme4","price":11111,"days":["31.10.2016"]} }
   * @return Status: 200 OK | Text: {...}
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(value = Array("/offer/{offerId}/price"), method = Array(RequestMethod.PUT), consumes = Array(MediaType.APPLICATION_JSON_VALUE))
  def editOfferPrices(@PathVariable offerId: String, @RequestBody monthOfPrice: PriceOfRents): ResponseEntity[String] = {
    if( !(offersServiceImpl offerExists(offerId)) ) new ResponseEntity(HttpStatus NOT_FOUND)
    else if (monthOfPrice.getWeekendPrice() == null && monthOfPrice.getSpecialPrice() == null && monthOfPrice.getSpecialPrice() == null) new ResponseEntity(HttpStatus BAD_REQUEST)

    var parser = new JsonParser()
    var classLoader: ClassLoader = getClass getClassLoader
    var gson = new Gson()
    try { objJsonMonth = parser parse(new FileReader(classLoader getResource(offerOctober) getFile)) }
    catch { case e: FileNotFoundException => e printStackTrace }
    monthOfPrices = gson fromJson(objJsonMonth, new TypeToken[HashMap[String, PriceOfRent]]{} getType)

    val editOffer: Offer = offersServiceImpl findById(offerId)
    offerPricesServiceImpl = new OfferPricesServiceImpl(editOffer getMonthOfPrices)
    offerPricesServiceImpl addPrices( monthOfPrice.getSpecialPrice getPrice, ConvertUtil toDate(monthOfPrice.getSpecialPrice getDays))
    editOffer setMonthOfPrices(offerPricesServiceImpl toRestore)
    offersServiceImpl edit(editOffer)
    new ResponseEntity(offerPricesServiceImpl toJson, HttpStatus OK)
  }

  /**
   *    URL: http://localhost:8184/api/rest/calendarService/offer/587659524c8ef1b9713b5ca3/price
   * method: DELETE
   * @return Status: 200 OK | Text: 5
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(value = Array("/offer/{offerId}/price"), method = Array(RequestMethod.DELETE), consumes = Array(MediaType.APPLICATION_JSON_VALUE))
  def deleteOfferPrices(@PathVariable offerId: String): ResponseEntity[Int] = {
    if( !(offersServiceImpl offerExists(offerId)) ) new ResponseEntity(HttpStatus NOT_FOUND)
    else new ResponseEntity(new OfferPricesServiceImpl(offersServiceImpl findById(offerId) getMonthOfPrices) delPrices(ConvertUtil toDate(Array("12.11.2016","14.11.2016"))), HttpStatus OK)
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
