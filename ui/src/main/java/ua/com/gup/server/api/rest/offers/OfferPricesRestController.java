package ua.com.itproekt.gup.server.api.rest.offers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.offer.Rent2;
import ua.com.itproekt.gup.service.offers.PriceOfRents;
import ua.com.itproekt.gup.service.offers.OfferPricesServiceImpl;
import ua.com.itproekt.gup.service.offers.RentsService;
import ua.com.itproekt.gup.service.offers.price.PriceOfRent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferPricesRestController {

    private static final String formatter = "d.MM.yyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String offerOctober = "offerOctoberOfPrices.json",
            offerRents = "offerRents.json"; //TODO
    private JsonObject objJsonMonth,objJsonRents;
    private Map<String, PriceOfRent> monthOfPrices;
    private Map<String, RentTest> rents;

    @Autowired
    private RentsService rentsService;

    @Autowired
    private OfferPricesServiceImpl monthOfPricesService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/price", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> viewOfferPrices(@PathVariable String offerId){
        if (!rentsService.exist(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Rent2 viewRent = rentsService.findById(offerId);
        monthOfPricesService = new OfferPricesServiceImpl(viewRent.getMonthOfPrices());

        return new ResponseEntity<>(monthOfPricesService.toJson(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/price", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOfferPrices(@PathVariable String offerId,
                                                    @RequestBody PriceOfRents monthOfPrices){
        if (rentsService.exist(offerId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (monthOfPrices.getWeekdayPrice()==null
                && monthOfPrices.getWeekendPrice()==null
                && monthOfPrices.getSpecialPrice()==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        monthOfPricesService = new OfferPricesServiceImpl(monthOfPrices.getWeekdayPrice(),monthOfPrices.getWeekendPrice()); // Устанавливаем дефолтную цену (на будни и выходные дни)
        monthOfPricesService.addPrices(monthOfPrices.getSpecialPrice().getPrice(), convertDate(monthOfPrices.getSpecialPrice().getDays())); // Устанавливаем специальную цену на отдельные дни
        Rent2 editRent = new Rent2();
        editRent.setMonthOfPrices(monthOfPricesService.toRestore());
        rentsService.create(editRent, offerId);

        return new ResponseEntity<>(monthOfPricesService.toJson(), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/price", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editOfferPrices(@PathVariable String offerId,
                                                  @RequestBody PriceOfRents monthOfPrice){
        if (!rentsService.exist(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (monthOfPrice.getWeekdayPrice()==null
                && monthOfPrice.getWeekendPrice()==null
                && monthOfPrice.getSpecialPrice()==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        JsonParser       parser = new JsonParser();
        ClassLoader classLoader = getClass().getClassLoader();
        Gson               gson = new Gson();
        try {
            objJsonMonth = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerOctober).getFile()));
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        monthOfPrices = gson.fromJson(objJsonMonth, new TypeToken<Map<String, PriceOfRent>>(){}.getType());

//        monthOfPricesService = new OfferPricesServiceImpl(10000l,15000l);
//        monthOfPricesService.addPrices(monthOfPrices.get("scheme4").getPrice(), convertDate(monthOfPrices.get("scheme4").getDays()));
        Rent2 editRent = rentsService.findById(offerId);
        monthOfPricesService = new OfferPricesServiceImpl(editRent.getMonthOfPrices());
        monthOfPricesService.addPrices(monthOfPrice.getSpecialPrice().getPrice(), convertDate(monthOfPrice.getSpecialPrice().getDays()));
//////        monthOfPricesService.edit(offerId, monthOfPricesService.toRestore());
////        if (offersService.offerExists(offerId)) {
//            Offer editOffer = offersService.findById(offerId);
        editRent.setMonthOfPrices(monthOfPricesService.toRestore());
        rentsService.update(editRent);
//        }

        return new ResponseEntity<>(monthOfPricesService.toJson(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/price", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteOfferPrices(@PathVariable String offerId){
        //FIXME:                                        @PathVariable String day){ ...forse FIXME!!!
        if (!rentsService.exist(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JsonParser       parser = new JsonParser();
        ClassLoader classLoader = getClass().getClassLoader();
        Gson               gson = new Gson();
        try {
            objJsonMonth = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerOctober).getFile()));
            objJsonRents = (JsonObject) parser.parse(new FileReader(classLoader.getResource(offerRents).getFile()));
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        monthOfPrices = gson.fromJson(objJsonMonth, new TypeToken<Map<String, PriceOfRent>>(){}.getType());
        rents = gson.fromJson(objJsonRents, new TypeToken<Map<String, RentTest>>(){}.getType());

        monthOfPricesService = new OfferPricesServiceImpl(10000l,15000l);
        monthOfPricesService.addPrices(monthOfPrices.get("scheme4").getPrice(), convertDate(monthOfPrices.get("scheme4").getDays()));

        return new ResponseEntity<>(monthOfPricesService.delPrices(convertDate(rents.get("delete42").getDays())).toString(), HttpStatus.OK);
    }

    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    private Long[] convertDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = convertDate(strDate[date]);
        }
        return longDate;
    }
}