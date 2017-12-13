/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.common.service.CommonOfferService;
import ua.com.gup.common.service.ImageService;

@Service
public class CommonOfferServiceImpl  implements CommonOfferService{
    
    @Autowired
    private CommonOfferRepository commonOfferRepository;
        @Autowired
    private ImageService imageService;

    @Override
    public boolean exists(String id) {
        return commonOfferRepository.exists(id);
    }
    
     @Override
    public List<ImageStorage> getImages(String offerId){
        return commonOfferRepository.findOfferImages(offerId);
    }
    
    @Override
    public ImageStorage getImage(String offerId, String imageId){
        return commonOfferRepository.findOfferImage(offerId, imageId);
    }   
    
    @Override
    public ImageStorage addImage(String offerId, MultipartFile file) throws IOException {
        ImageStorage image = imageService.saveImageStorage(file);
        CommonRentOffer offer = commonOfferRepository.findOne(offerId);
        offer.getImages().add(image);
        commonOfferRepository.save(offer);
        return image;
    }    
    
    @Override
    public boolean isExistsImage(String offerId, String imageId){
        return commonOfferRepository.isExistsOfferImage(offerId, imageId);
    }
    
    @Override
    public void deleteImage(String offerId, String imageId) throws IOException{        
        ImageStorage image = commonOfferRepository.findOfferImage(offerId, imageId);        
        imageService.deleteImageStorage(image);
        commonOfferRepository.deleteOfferImage(image);
    }
    
}
