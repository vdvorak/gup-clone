package ua.com.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.service.offers.OffersService;

import java.util.Set;

@RestController
public class OfferSearchRestController {


    @Autowired
    private OffersService offersService;

    @CrossOrigin
    @RequestMapping("/search/autocomplete/offer")
    public Set<String> getMachedNames(@RequestParam String term) {
        return offersService.getMatchedNames(term);
    }

}
