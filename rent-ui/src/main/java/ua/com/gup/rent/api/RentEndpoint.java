package ua.com.gup.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.rent.service.rent.RentObjectService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rents")
public class RentEndpoint {

    @Autowired
    private RentObjectService rentObjectService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO> rentObjects = rentObjectService.findAll();
        return new ResponseEntity(rentObjects, HttpStatus.OK);
    }

    @RequestMapping(path = "/{seoUrl}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(name = "seoUrl") String seoUrl) {
        List<ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO> rentObjects = rentObjectService.findAll();
        return new ResponseEntity("STRIKE!!!!!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.RentObject).CLASS_NAME, 'create')")
    public ResponseEntity createRentObject(ua.com.gup.rent.dto.rent.RentCreateObjectDTO rentCreateObjectDTO) {

        rentObjectService.create(rentCreateObjectDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT})
    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.RentObject).CLASS_NAME, 'edit')")
    public ResponseEntity updateRentObject(@PathVariable(name = "id") String rentObjectId, ua.com.gup.rent.dto.rent.RentEditObjectDTO rentEditObjectDTO) {

        rentObjectService.update(rentEditObjectDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.RentObject).CLASS_NAME, 'delete')")
    public ResponseEntity deleteRentObject(@PathVariable(name = "id") String rentObjectId) {

        rentObjectService.deleteById(rentObjectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
