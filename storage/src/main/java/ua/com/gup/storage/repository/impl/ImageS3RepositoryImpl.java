package ua.com.gup.storage.repository.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.gup.storage.dto.ImageDTO;
import ua.com.gup.storage.repository.ImageRepository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class ImageS3RepositoryImpl implements ImageRepository {
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private Bucket imagesBucket;

    @Override
    public String saveImage(String imageKey, String contentType, InputStream inputStream) throws AmazonS3Exception, IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStream.available());
        objectMetadata.setContentType(contentType);
        PutObjectResult putObjectResult = amazonS3.putObject(imagesBucket.getName(), imageKey, inputStream, objectMetadata);
        return putObjectResult.getContentMd5();
    }

    @Override
    public void deleteImageByKey(String imageKey) throws AmazonS3Exception {
        if (amazonS3.doesObjectExist(imagesBucket.getName(), imageKey)) {
            amazonS3.deleteObject(new DeleteObjectRequest(imagesBucket.getName(), imageKey));
        }
    }


    @Override
    public ImageDTO getImageInputStreamByKey(String imageKey) throws AmazonS3Exception {

        if (amazonS3.doesObjectExist(imagesBucket.getName(), imageKey)) {
            ObjectMetadata objectMetadata = amazonS3.getObjectMetadata(imagesBucket.getName(), imageKey);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(imagesBucket.getName(), imageKey);

            return new ImageDTO(objectMetadata.getContentType(), amazonS3.generatePresignedUrl(request));
        }
        return null;
    }

}
