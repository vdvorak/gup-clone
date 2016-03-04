package ua.com.itproekt.gup.api.rest.nace;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.model.nace.NACE;
import ua.com.itproekt.gup.service.nace.NaceService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Комп2 on 04.03.2016.
 */
@RestController
public class NaceSearchRestController {

    @Autowired
    NaceService naceService;

    @RequestMapping("/search/autocomplete/nace")
    public Set<String> getMachedNames(@RequestParam String term){
        Set<NACE> naces = new HashSet<>();
        naces.addAll(naceService.findAll());
        return naces.stream().filter(n -> n.getName().contains(term)).map(NACE::getName).collect(Collectors.toSet());
    }
}
