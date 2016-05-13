package ua.com.itproekt.gup.api.rest.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.tender.TenderService;

import java.util.Set;

@RestController
public class TenderSearchRestController {

    @Autowired
    TenderService tenderService;


    @RequestMapping("/search/autocomplete/tender")
    public Set<String> getMachedNames(@RequestParam String term) {
        return tenderService.getMatchedNames(term);
    }

}
