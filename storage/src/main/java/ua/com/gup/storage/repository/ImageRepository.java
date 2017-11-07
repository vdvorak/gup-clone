package ua.com.gup.storage.repository;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import ua.com.gup.storage.dto.ImageDTO;

import java.io.IOException;
import java.io.InputStream;

public interface ImageRepository {

    String saveImage(String imageKey, String contentType, InputStream inputStream) throws AmazonS3Exception, IOException;

    void deleteImageByKey(String imageKey) throws AmazonS3Exception;

    ImageDTO getImageInputStreamByKey(String imageKey) throws AmazonS3Exception;
}
