package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.paidservices.Marked;
import ua.com.itproekt.gup.model.offer.paidservices.PaidServices;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@RestController
@PropertySource("classpath:properties/offer-paid.properties")
@RequestMapping("/api/rest/offersPaidService")
public class OfferPaidRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    SeoSequenceService seoSequenceService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private StorageService storageService;

    @Autowired
    BankSession bankSession;

    @Autowired
    Environment env;

//    private final int marked_cost = 15;
//    private final int urgent_cost = 15;
    private final int cheaper_cost = 15;

    @CrossOrigin
    @RequestMapping(value = "/offer/check/{id}", method = RequestMethod.POST,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferInfo> checkOfferById(@PathVariable String id) {
        Offer offer = offersService.findOfferAndIncViews(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = "571a2fdd681db5eee71086c0"; //String userId = SecurityOperations.getLoggedUserId();

        //if user is author - he will receive additional fields
        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(offersService.getPrivateOfferInfoByOffer(offer), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //------------------------------------------ Paid -----------------------------------------------------------------

    @CrossOrigin
    @RequestMapping(value = "/offer/get/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOfferById(@PathVariable String id) {
        Offer oldOffer = offersService.findOfferAndIncViews(id);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PaidServices paid = new PaidServices();
        paid.setIsMarked(Marked.THREE);
        paid.setIsUrgent( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
        paid.setIsCheaper( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
        paid.setLastPaidUpdateDate( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
        Offer newOffer = oldOffer.setPaidServices(paid);
        offersService.edit(newOffer);

        return new ResponseEntity<>(oldOffer.toString(), HttpStatus.OK);
    }

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/marked/offer/57444d787688dcc2ed336db7
     * isMarked .......... просто красиво выделеное цветом объявление (на 3,6,16 месяцев)
     * 0 | 3 | 6 | 12
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/marked/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setMarkedByOfferId(@PathVariable String offerId, @RequestBody String marked) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //String userId = SecurityOperations.getLoggedUserId();
        String userId = "571a2fdd681db5eee71086c0";
        String buyByBonusAccount = null;

        //if user is author - he will receive additional fields
        if (oldOffer.getAuthorId().equals(userId)) {
            int marked_cost = Integer.valueOf(env.getProperty("offer-paid.marked.cost"));
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (marked_cost < amount){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, marked_cost, offerId);
                PaidServices paid = new PaidServices();
                paid.setIsMarked( Marked.getMarked(marked) );
                paid.setLastPaidUpdateDate( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
                Offer newOffer = oldOffer.setPaidServices(paid);
                offersService.edit(newOffer);
            }
            return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); //return new ResponseEntity<>(oldOffer.toString(), HttpStatus.OK);
//            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
//            return new ResponseEntity<>(oldOffer.toString() + " ============== amount=" + amount, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/urgent/offer/57444d787688dcc2ed336db7
     * isUrgent .......... пометить как срочное объявление (указать дату когда истекает срок)
     * 30.06.2014
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/urgent/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setUrgentByOfferId(@PathVariable String offerId, @RequestBody String urgent) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //String userId = SecurityOperations.getLoggedUserId();
        String userId = "571a2fdd681db5eee71086c0";
        String buyByBonusAccount = null;

        //if user is author - he will receive additional fields
        if (oldOffer.getAuthorId().equals(userId)) {
            int urgent_cost = Integer.valueOf(env.getProperty("offer-paid.marked.cost"));
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (urgent_cost < amount){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, urgent_cost, offerId);
                PaidServices paid = new PaidServices();
                paid.setIsUrgent(convertDate(urgent));
                paid.setLastPaidUpdateDate( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
                Offer newOffer = oldOffer.setPaidServices(paid);
                offersService.edit(newOffer);
            }
            return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); //return new ResponseEntity<>(oldOffer.toString(), HttpStatus.OK);
//            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
//            return new ResponseEntity<>(oldOffer.toString() + " ============== amount=" + amount, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * http://localhost:8084/api/rest/offersPaidService/paid-bonus/cheaper/offer/57444d787688dcc2ed336db7
     * isCheaper ......... пометить как дешевле объявление (указать дату когда истекает срок)
     * 30.06.2014
     *
     * @param offerId
     * @return
     */
    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid-bonus/cheaper/offer/{offerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setCheaperByOfferId(@PathVariable String offerId, @RequestBody String cheaper) {
        Offer oldOffer = offersService.findOfferAndIncViews(offerId);
        if (oldOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //String userId = SecurityOperations.getLoggedUserId();
        String userId = "571a2fdd681db5eee71086c0";
        String buyByBonusAccount = null;

        //if user is author - he will receive additional fields
        if (oldOffer.getAuthorId().equals(userId)) {
            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
            if (cheaper_cost < amount){
                buyByBonusAccount = bankSession.buyByBonusAccount(userId, 2003, cheaper_cost, offerId);
                PaidServices paid = new PaidServices();
                paid.setIsCheaper(convertDate(cheaper));
                paid.setLastPaidUpdateDate( LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() );
                Offer newOffer = oldOffer.setPaidServices(paid);
                offersService.edit(newOffer);
            }
            return new ResponseEntity<>(buyByBonusAccount, HttpStatus.OK); //return new ResponseEntity<>(oldOffer.toString(), HttpStatus.OK);
//            int amount = Integer.valueOf(bankSession.getBonusByUserId(userId));
//            return new ResponseEntity<>(oldOffer.toString() + " ============== amount=" + amount, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * <обновить срок> ... поднять-отсортировать вверх списка по дате (одноразово)
     *
     * @param strDate
     * @return
     */


    // "30.06.2014" >> 1472936400000
    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }
}
