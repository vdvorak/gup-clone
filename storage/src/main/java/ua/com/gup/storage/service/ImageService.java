package ua.com.gup.storage.service;

import ua.com.gup.storage.dto.ImageDTO;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    /**
     * @param imageKey
     * @param contentType
     * @param inputStream
     * @return md5 hash
     * @throws IOException
     */
    String saveImage(String imageKey, String contentType, InputStream inputStream) throws IOException;

    ImageDTO getImageByKey(String imageKey);

    void deleteImageByKey(String imageKey);

    Boolean doesObjectExists(String imageKey);
}
