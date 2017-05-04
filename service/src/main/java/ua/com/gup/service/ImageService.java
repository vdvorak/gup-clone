package ua.com.gup.service;


import ua.com.gup.repository.file.FileWrapper;
import ua.com.gup.service.dto.OfferImageDTO;
import ua.com.gup.service.dto.enumeration.OfferImageSizeType;

import java.util.Map;

public interface ImageService {

    FileWrapper findOne(String id, OfferImageSizeType type);

    void deleteOfferImage(String id);

    String saveOfferImage(OfferImageDTO offerImageDTO, String fileName);

}
