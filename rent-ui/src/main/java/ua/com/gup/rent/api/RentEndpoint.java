package ua.com.gup.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.rent.dto.rentobject.price.RentObjectPriceDTO;
import ua.com.gup.rent.dto.rentobject.period.RentPeriodDTO;
import ua.com.gup.rent.dto.rentobject.CreateRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.EditRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.ShortDetailsRentObjectDTO;
import ua.com.gup.rent.editor.RentObjectDTORentObjectPriceEditor;
import ua.com.gup.rent.editor.RentObjectDTORentPeriodEditor;
import ua.com.gup.rent.service.rent.RentObjectService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rents")
public class RentEndpoint {

    @Autowired
    private RentObjectService rentObjectService;

//    @Autowired
//    private RentObjectDTORentPeriodEditor periodEditor;
//    @Autowired
//    private RentObjectDTORentObjectPriceEditor priceEditor;
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//
//        if (binder.getTarget() != null) {
//            final Class<?> clazz = binder.getTarget().getClass();
//            if (CreateRentObjectDTO.class.equals(clazz)) {
//                binder.registerCustomEditor(RentPeriodDTO.class, periodEditor);
//                binder.registerCustomEditor(RentObjectPriceDTO.class, priceEditor);
//            }
//        }
//
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ShortDetailsRentObjectDTO> rentObjects = rentObjectService.findAll();
        return new ResponseEntity(rentObjects, HttpStatus.OK);
    }

    @RequestMapping(path = "/{seoUrl}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(name = "seoUrl") String seoUrl) {
        List<ShortDetailsRentObjectDTO> rentObjects = rentObjectService.findAll();
        return new ResponseEntity("STRIKE!!!!!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.RentObject).CLASS_NAME, 'create')")
    public ResponseEntity createRentObject(CreateRentObjectDTO createRentObjectDTO)  {

        rentObjectService.create(createRentObjectDTO);
        return new ResponseEntity(createRentObjectDTO, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.RentObject).CLASS_NAME, 'edit')")
    public ResponseEntity updateRentObject(@PathVariable(name = "id") String rentObjectId, EditRentObjectDTO editRentObjectDTO) {

        rentObjectService.update(editRentObjectDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.RentObject).CLASS_NAME, 'delete')")
    public ResponseEntity deleteRentObject(@PathVariable(name = "id") String rentObjectId) {

        rentObjectService.deleteById(rentObjectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
