package ua.com.gup.common.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import ua.com.gup.common.model.image.ImageStorage;

/**
 * Service Interface for managing Offer.
 */
public interface ImageOfferService {  

    List<ImageStorage> getImages(String offerId);

    ImageStorage getImage(String offerId, String imageId);

    ImageStorage addImage(String offerId, MultipartFile file) throws IOException;

    boolean isExistsImage(String offerId, String imageId);

    void deleteImage(String offerId, String imageId) throws IOException;
}
