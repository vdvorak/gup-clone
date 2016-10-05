package ua.com.itproekt.gup.server.api.rest.transportApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.service.transportApiService.DeliveryService;

@Controller
@RequestMapping("/api/rest/transportService")
public class DeliveryApiRestController {

    @Autowired
    DeliveryService deliveryService;


    /**
     * @param trackNumber - number of parcel
     * @return always status 200 (Ok) and result in JSON format
     */
    @CrossOrigin
    @RequestMapping(value = "/dl/tracking", method = RequestMethod.POST)
    public ResponseEntity<String> trackParcels(@RequestBody String trackNumber) {

        String result = deliveryService.tracking(trackNumber);
        System.err.println("Result: " + result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
