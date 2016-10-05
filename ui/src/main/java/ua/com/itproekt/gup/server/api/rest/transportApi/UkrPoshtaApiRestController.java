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
import ua.com.itproekt.gup.service.transportApiService.UkrPoshtaService;
import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.MethodProperties;

@Controller
@RequestMapping("/api/rest/transportService")
public class UkrPoshtaApiRestController {



    @Autowired
    UkrPoshtaService ukrPoshtaService;


    @CrossOrigin
    @RequestMapping(value = "/up/tracking", method = RequestMethod.POST)
    public ResponseEntity<String> trackParcels(@RequestBody String barcode) {

        String result = ukrPoshtaService.tracking(barcode);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
