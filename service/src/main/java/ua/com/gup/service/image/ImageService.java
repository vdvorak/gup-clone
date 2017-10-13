package ua.com.gup.service.image;


import ua.com.gup.dto.offer.OfferImageDTO;
import ua.com.gup.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.mongo.model.file.FileWrapper;

public interface ImageService {

    FileWrapper findOne(String id, OfferImageSizeType type);

    void deleteOfferImage(String id);

    String saveOfferImage(OfferImageDTO offerImageDTO, String fileName);

}
