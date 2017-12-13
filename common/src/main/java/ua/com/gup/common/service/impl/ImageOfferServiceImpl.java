package ua.com.gup.common.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.service.ImageOfferService;
import ua.com.gup.common.service.ImageService;
import ua.com.gup.common.repository.CommonOfferRepository;

/**
 * Service Implementation for managing Offer.
 */
@Service
public class ImageOfferServiceImpl implements ImageOfferService {
    private final Logger log = LoggerFactory.getLogger(ImageOfferServiceImpl.class);
    
    @Autowired
    private ImageService imageService;
    @Autowired
    private CommonOfferRepository commonOfferService;
    

    
    @Override
    public List<ImageStorage> getImages(String offerId){
        return commonOfferService.findOfferImages(offerId);
    }
    
    @Override
    public ImageStorage getImage(String offerId, String imageId){
        return commonOfferService.findOfferImage(offerId, imageId);
    }   
    
    @Override
    public ImageStorage addImage(String offerId, MultipartFile file) throws IOException {
        ImageStorage image = imageService.saveImageStorage(file);
        CommonRentOffer offer = commonOfferService.findOne(offerId);
        offer.getImages().add(image);
        commonOfferService.save(offer);
        return image;
    }    
    
    @Override
    public boolean isExistsImage(String offerId, String imageId){
        return commonOfferService.isExistsOfferImage(offerId, imageId);
    }
    
    @Override
    public void deleteImage(String offerId, String imageId) throws IOException{        
        ImageStorage image = commonOfferService.findOfferImage(offerId, imageId);        
        imageService.deleteImageStorage(image);
        commonOfferService.deleteOfferImage(image);
    }
}