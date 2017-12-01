package ua.com.gup.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.rent.service.dto.rent.RentCreateDTO;
import ua.com.gup.rent.service.dto.rent.RentEditDTO;
import ua.com.gup.rent.service.dto.rent.RentShortDetailsDTO;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rents")
public class RentEndpoint {

    @Autowired
    private ua.com.gup.rent.service.rent.RentObjectServiceRent rentObjectService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<RentShortDetailsDTO> rentObjects = rentObjectService.findAll();
        return new ResponseEntity(rentObjects, HttpStatus.OK);
    }

    @RequestMapping(path = "/{seoUrl}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(name = "seoUrl") String seoUrl) {
        List<RentShortDetailsDTO> rentObjects = rentObjectService.findAll();
        return new ResponseEntity("STRIKE!!!!!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.rent.Rent).CLASS_NAME, 'create')")
    public ResponseEntity createRentObject(RentCreateDTO rentCreateObjectDTO) {

        rentObjectService.create(rentCreateObjectDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT})
    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.rent.Rent).CLASS_NAME, 'edit')")
    public ResponseEntity updateRentObject(@PathVariable(name = "id") String rentObjectId, RentEditDTO rentEditObjectDTO) {

        rentObjectService.update(rentEditObjectDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.rent.Rent).CLASS_NAME, 'delete')")
    public ResponseEntity deleteRentObject(@PathVariable(name = "id") String rentObjectId) {

        rentObjectService.deleteById(rentObjectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
