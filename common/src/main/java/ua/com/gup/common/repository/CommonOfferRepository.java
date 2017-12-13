/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.repository;

import java.util.List;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.CommonRentOffer;

public interface CommonOfferRepository<T extends CommonRentOffer> {

    List<ImageStorage> findOfferImages(String offerId);

    ImageStorage findOfferImage(String offerId, String imageId);

    Boolean isExistsOfferImage(String offerId, String imageId);

    void deleteOfferImage(ImageStorage image);

    public T findOne(String offerId);

    public void save(T offer);

    public boolean exists(String id);
    
}
