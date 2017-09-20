package ua.com.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.bank_api.BankSession;
import ua.com.gup.domain.Offer;
import ua.com.gup.dto.OfferInfo;
import ua.com.gup.model.offer.paidservices.Marked;
import ua.com.gup.model.offer.paidservices.PaidServices;
import ua.com.gup.service.offers.OffersService;
import ua.com.gup.util.SecurityOperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@RestController
@PropertySource("classpath:properties/offer-paid.properties")
@RequestMapping("/api/rest/offersPaidService")
public class OfferPaidRestController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private BankSession bankSession;

    @Autowired
    private Environment env;

    static String formatter = "d.MM.yyyy";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    @CrossOrigin
    @RequestMapping(value = "/offer-info/{offerId}", method = RequestMethod.GET,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferInfo> getOfferInfoById(@PathVariable String offerId) {
        Offer offer = offersService.findOfferAndIncViews(offerId);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(offersService.getPrivateOfferInfoByOffer(offer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
    @RequestMapping(value = "/offer/{offerId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOfferById(@PathVariable String offerId) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PaidServices paid = new PaidServices();
        paid.setIsMarked(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        paid.setIsUrgent( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
        paid.setIsCheaper( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
        paid.setLastPaidUpdateDate( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
        //todo vdvorak
        //Offer newOffer = oldOffer.setPaidServices(paid);
        Offer newOffer = oldOffer;
        offersService.edit(newOffer);

        return new ResponseEntity<>(oldOffer.toString(), HttpStatus.OK);
    }

    //------------------------------------------ Paid -----------------------------------------------------------------

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/marked/offer/57444d787688dcc2ed336db7
     * (long) isMarked .......... просто красиво выделеное цветом объявление (указать дату когда истекает срок на 1,3,6,16 месяцев)
     * period (0 | 1 | 3 | 6 | 12)
     * (userId=571a2fdd681db5eee71086c0)
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/marked/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setMarkedByOfferId(@PathVariable String offerId, @RequestBody String period) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        String buyByBonusAccount = null;

        if (oldOffer.getAuthorId().equals(userId)) {
            int marked_cost = Integer.valueOf(env.getProperty("offer-paid.marked.cost"));
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (marked_cost < amount && 0 < Marked.getMarked(Integer.valueOf(period)).period()){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, marked_cost, offerId);
                PaidServices paid = new PaidServices();
                try {
                    //todo vdvorak
                    //if(oldOffer.getPaidServices() != null){
                    if(oldOffer != null){
                        //paid = oldOffer.getPaidServices();
                        paid.setIsMarked( addDate(paid.getIsMarked(), Marked.getMarked(Integer.valueOf(period)).period()) );
                    } else {
                        paid = new PaidServices();
                        paid.setIsMarked( addDate(Marked.getMarked(Integer.valueOf(period)).period()) );
                        paid.setIsUrgent(0l);
                        paid.setIsCheaper(0l);
                        paid.setLastPaidUpdateDate(0l);
                    }
                } catch (ParseException e) {
                    return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"Incorrect Data Format\"}", HttpStatus.BAD_REQUEST);
                }
                //todo vdvorak
                //Offer newOffer = oldOffer.setPaidServices(paid);
                Offer newOffer = oldOffer;
                offersService.edit(newOffer);
                return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); // TODO result transacrion
            }
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"insufficient funds\"}", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"not the owner\"}", HttpStatus.FORBIDDEN);
    }

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/urgent/offer/57444d787688dcc2ed336db7
     * (long) isUrgent .......... пометить как срочное объявление (указать дату когда истекает срок на 1,3,6,16 месяцев)
     * period (0 | 1 | 3 | 6 | 12)
     * (userId=571a2fdd681db5eee71086c0)
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/urgent/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setUrgentByOfferId(@PathVariable String offerId, @RequestBody String period) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        String buyByBonusAccount = null;

        if (oldOffer.getAuthorId().equals(userId)) {
            int urgent_cost = Integer.valueOf(env.getProperty("offer-paid.urgent.cost"));
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (urgent_cost < amount && 0 < Marked.getMarked(Integer.valueOf(period)).period()){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, urgent_cost, offerId);
                //todo vdvorak
                //PaidServices paid = null;
                PaidServices paid = new PaidServices();
                try {
                    //todo vdvorak
                    //if(oldOffer.getPaidServices() != null){
                    if(oldOffer != null){
                        //paid = oldOffer.getPaidServices();
                        paid.setIsUrgent(addDate(paid.getIsUrgent(), Marked.getMarked(Integer.valueOf(period)).period()));
                    } else {
                        paid = new PaidServices();
                        paid.setIsMarked(0l);
                        paid.setIsUrgent(addDate(Marked.getMarked(Integer.valueOf(period)).period()));
                        paid.setIsCheaper(0l);
                        paid.setLastPaidUpdateDate(0l);
                    }
                } catch (ParseException e) {
                    return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"Incorrect Data Format\"}", HttpStatus.BAD_REQUEST);
                }
                //todo vdvorak
                //Offer newOffer = oldOffer.setPaidServices(paid);
                Offer newOffer = oldOffer;
                offersService.edit(newOffer);
                return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); // TODO result transacrion
            }
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"insufficient funds\"}", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"not the owner\"}", HttpStatus.FORBIDDEN);
    }

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/cheaper/offer/57444d787688dcc2ed336db7
     * (long) isCheaper ......... пометить как дешевле объявление (указать дату когда истекает срок на 1,3,6,16 месяцев)
     * period (0 | 1 | 3 | 6 | 12)
     * 30.06.2014
     * (userId=571a2fdd681db5eee71086c0)
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/cheaper/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setCheaperByOfferId(@PathVariable String offerId, @RequestBody String period) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        String buyByBonusAccount = null;

        if (oldOffer.getAuthorId().equals(userId)) {
            int cheaper_cost = Integer.valueOf(env.getProperty("offer-paid.cheaper.cost"));
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (cheaper_cost < amount && 0 < Marked.getMarked(Integer.valueOf(period)).period()){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, cheaper_cost, offerId);
                //todo vdvorak
                //PaidServices paid = null;
                PaidServices paid = new PaidServices();
                try {
                    //todo vdvorak
                    //if(oldOffer.getPaidServices() != null){
                    if(oldOffer != null){
                        //paid = oldOffer.getPaidServices();
                        paid.setIsCheaper(addDate(paid.getIsCheaper(), Marked.getMarked(Integer.valueOf(period)).period()));
                    } else {
                        paid = new PaidServices();
                        paid.setIsMarked(0l);
                        paid.setIsUrgent(0l);
                        paid.setIsCheaper(addDate(Marked.getMarked(Integer.valueOf(period)).period()));
                        paid.setLastPaidUpdateDate(0l);
                    }
                } catch (ParseException e) {
                    return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"Incorrect Data Format\"}", HttpStatus.BAD_REQUEST);
                }
                //todo vdvorak
                //Offer newOffer = oldOffer.setPaidServices(paid);
                Offer newOffer = oldOffer;
                offersService.edit(newOffer);
                return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); // TODO result transacrion
            }
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"insufficient funds\"}", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"not the owner\"}", HttpStatus.FORBIDDEN);
    }

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/paid-update/offer/57444d787688dcc2ed336db7
     * <дата платного обновления - обновить срок> ... поднять-отсортировать вверх списка по дате (одноразово)
     * (userId=571a2fdd681db5eee71086c0)
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/paid-update/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setPaidUpdateByOfferId(@PathVariable String offerId) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        String buyByBonusAccount = null;

        if (oldOffer.getAuthorId().equals(userId)) {
            int paid_update_cost = Integer.valueOf(env.getProperty("offer-paid.paid-update.cost"));
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (paid_update_cost < amount){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, paid_update_cost, offerId);
                //todo vdvorak
                //PaidServices paid = null;
                PaidServices paid = new PaidServices();
                if(oldOffer != null){
                //if(oldOffer.getPaidServices() != null){
                    //paid = oldOffer.getPaidServices();
                    paid.setLastPaidUpdateDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
                } else {
                    paid = new PaidServices();
                    paid.setIsMarked(0l);
                    paid.setIsUrgent(0l);
                    paid.setIsCheaper(0l);
                    paid.setLastPaidUpdateDate( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
                }
                //todo vdovorak
                //Offer newOffer = oldOffer.setPaidServices(paid);
                Offer newOffer = oldOffer;
                offersService.edit(newOffer);
                return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); // TODO result transacrion
            }
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"insufficient funds\"}", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("{\"status\":\"ERROR\", \"notification\":\"not the owner\"}", HttpStatus.FORBIDDEN);
    }


    long addDate(int iMonth) throws ParseException {
        Date     currDate = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(currDate)));
        calendar.add(Calendar.MONTH, iMonth);
        LocalDate localDate = LocalDate.parse(simpleDateFormat.format(calendar.getTime()), dateTimeFormatter);
        Date        addDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return addDate.getTime();
    }

    long addDate(long lLastDate, int iMonth) throws ParseException {
        if( isExpired(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(), lLastDate) ){
            Date lastDate = new Date(lLastDate);
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(lastDate)));
            calendar.add(Calendar.MONTH, iMonth);
            LocalDate localDate = LocalDate.parse(simpleDateFormat.format(calendar.getTime()), dateTimeFormatter);
            Date addDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            return addDate.getTime();
        } else {
            return addDate(iMonth);
        }
    }

    private boolean isExpired(long currDate, long lastDate) {
        return (currDate < lastDate) ? true : false;
    }
}
