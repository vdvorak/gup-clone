/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.filter.CommonOfferFilter;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.model.offer.CommonCategoryCount;

public interface CommonOfferRepository<T extends CommonRentOffer, F extends CommonOfferFilter> {

    List<ImageStorage> findOfferImages(String offerId);

    ImageStorage findOfferImage(String offerId, String imageId);

    Boolean isExistsOfferImage(String offerId, String imageId);

    void deleteOfferImage(ImageStorage image);

    public T findOne(String offerId);

    public void save(T offer);

    public boolean exists(String id);

    List<CommonCategoryCount> searchCategoriesByString(String string, int page, int size);

    public boolean isOwner(String rentObjectId, String userId);


    long countByFilter(F offerFilter, CommonStatus offerStatus);

    List<T> findByFilter(F offerFilter, CommonStatus offerStatus, Pageable pageable);

    List<T> findByFilter(F offerFilter, CommonStatus offerStatus, String excludedId, Pageable pageable);

    List<T> findByFilter(F offerFilter, List<CommonStatus> offerStatuses, Pageable pageable);

    List<T> findByFilter(F offerFilter, List<CommonStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

}
