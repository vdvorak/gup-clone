package ua.com.itproekt.gup.server.api.rest.transportApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.service.transportApiService.NovaPoshtaService;
import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.MethodProperties;


@Controller
@RequestMapping("/api/rest/transportService")
public class NovaPoshtaApiRestController {

    @Autowired
    NovaPoshtaService novaPoshtaService;

    /**
     *
     * @param methodProperties
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/np/tracking", method = RequestMethod.POST)
    public ResponseEntity<String> trackParcels(@RequestBody MethodProperties methodProperties) {

        String result = novaPoshtaService.tracking(methodProperties);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
