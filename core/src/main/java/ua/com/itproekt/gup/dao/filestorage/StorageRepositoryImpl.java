package ua.com.itproekt.gup.dao.filestorage;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

@Repository
public class StorageRepositoryImpl implements StorageRepository {

    private static final String LARGE_CACHE_IMAGE_STORAGE_PATH = ".file.storage.large.cache";
    private static final String MEDIUM_CACHE_IMAGE_STORAGE_PATH = ".file.storage.large.cache";
    private static final String SMALL_CACHE_IMAGE_STORAGE_PATH = ".file.storage.small.cache";

    private int profileSmallCachedSize = 40;
    private int profileLargeCachedSize = 175;


    @Autowired
    private MongoTemplate mongoTemplate;

    private GridFS gridFs;



    @Override
    public void delete(String serviceName, String filePath, String fileId) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + filePath);
        if (ObjectId.isValid(fileId)) {
            gridFs.remove(new ObjectId(fileId));
        }
    }

    @Override
    public void delete(String serviceName, String filePath, Set<String> fileIds) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + filePath);
        for (String fileId : fileIds) {
            if (ObjectId.isValid(fileId)) {
                gridFs.remove(new ObjectId(fileId));
            }
        }
    }

    @Override
    public GridFSDBFile getCachedImage(String serviceName, String filePath, String imageId) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName.toUpperCase() + filePath);
        return gridFs.findOne(new ObjectId(imageId));
    }


    private String cacheImage(FileUploadWrapper fileUploadWrapper,BufferedImage bufferedImage, int size, String originalImageId, String filePath) throws IOException {
        // Cache image

        BufferedImage scaledImage = Scalr.resize(bufferedImage, size);
        String imageFormatName = fileUploadWrapper.getContentType().substring(fileUploadWrapper.getContentType().lastIndexOf("/") + 1);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, imageFormatName, os);


        // save image
        this.gridFs = new GridFS(mongoTemplate.getDb(), fileUploadWrapper.getServiceName() + filePath);

        GridFSInputFile inputFile = gridFs.createFile(new ByteArrayInputStream(os.toByteArray()),
                fileUploadWrapper.getFilename(), true);

        // it can be null if it first image
        if (originalImageId != null) {
            inputFile.setId(new ObjectId(originalImageId));
        }


        inputFile.setContentType(fileUploadWrapper.getContentType());
        inputFile.save();
        return inputFile.getId().toString();
    }


    @Override
    public String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper) {


        // first call of method with originalImageId = null
        String originalImageId = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(fileUploadWrapper.getInputStream());

            originalImageId = cacheImage(fileUploadWrapper, bufferedImage, profileLargeCachedSize, originalImageId, LARGE_CACHE_IMAGE_STORAGE_PATH);

            cacheImage(fileUploadWrapper,bufferedImage, profileSmallCachedSize, originalImageId, SMALL_CACHE_IMAGE_STORAGE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return originalImageId;
    }


}
