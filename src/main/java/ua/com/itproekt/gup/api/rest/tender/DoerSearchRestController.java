package ua.com.itproekt.gup.api.rest.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.tender.doer.DoerService;

import java.util.Set;

@RestController
public class DoerSearchRestController {


    @Autowired
    DoerService doerService;

    @RequestMapping("/search/autocomplete/doer")
    public Set<String> getMachedNames(@RequestParam String term) {
        return doerService.getMatchedNames(term);
    }
}
