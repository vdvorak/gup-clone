package ua.com.gup.service;


import ua.com.gup.repository.file.FileWrapper;
import ua.com.gup.dto.offer.OfferImageDTO;
import ua.com.gup.dto.offer.enumeration.OfferImageSizeType;

public interface ImageService {

    FileWrapper findOne(String id, OfferImageSizeType type);

    void deleteOfferImage(String id);

    String saveOfferImage(OfferImageDTO offerImageDTO, String fileName);

}
