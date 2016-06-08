package ua.com.itproekt.gup.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.offers.OffersService;

import java.util.Set;

@RestController
public class OfferSearchRestController {


    @Autowired
    OffersService offersService;


    @RequestMapping("/search/autocomplete/offer")
    public Set<String> getMachedNames(@RequestParam String term) {
        return offersService.getMatchedNames(term);
    }

}
