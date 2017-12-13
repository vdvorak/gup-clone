/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.web.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.service.CommonOfferService;

public abstract class AbstractImageEndpoint {
    
    private final Logger log = LoggerFactory.getLogger(AbstractImageEndpoint.class);
    
    @Autowired
    private CommonOfferService commonOfferService;
    
    
    @RequestMapping(value = "/offers/{offerId}/images", method = RequestMethod.GET)
    public ResponseEntity getOfferImages(@PathVariable("offerId") String offerId) throws URISyntaxException {
        if (!commonOfferService.exists(offerId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<ImageStorage> images = commonOfferService.getImages(offerId);
        return new ResponseEntity(images, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/offers/{offerId}/images/{imageId}", method = RequestMethod.GET)
    public ResponseEntity getOfferImage(
            @PathVariable("offerId") String offerId,
            @PathVariable("imageId") String imageId) {
        if (!commonOfferService.isExistsImage(offerId, imageId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ImageStorage image = commonOfferService.getImage(offerId, imageId);
        return new ResponseEntity(image, HttpStatus.OK);

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offers/{offerId}/images", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity createOfferImage(
            @PathVariable("offerId") String offerId,
            @RequestParam(name = "image", required = true) MultipartFile image) throws URISyntaxException {

        if (!commonOfferService.exists(offerId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        try {
            commonOfferService.addImage(offerId, image);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IOException ex) {
            log.error("Error add image", ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offers/{offerId}/images/{imageId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOfferImage(
            @PathVariable("offerId") String offerId,
            @PathVariable("imageId") String imageId) throws URISyntaxException {

        if (!commonOfferService.isExistsImage(offerId, imageId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        try {
            commonOfferService.deleteImage(offerId, imageId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException ex) {
            log.error("Error add image", ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
