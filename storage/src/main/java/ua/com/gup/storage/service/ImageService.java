package ua.com.gup.storage.service;

import ua.com.gup.storage.dto.ImageDTO;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    /**
     * @param imageId
     * @param contentType
     * @param inputStream
     * @return md5 hash
     * @throws IOException
     */
    String saveImage(String imageId, String contentType, InputStream inputStream) throws IOException;

    ImageDTO getImageByKey(String imageId);

    void deleteImageByKey(String imageId);
}
