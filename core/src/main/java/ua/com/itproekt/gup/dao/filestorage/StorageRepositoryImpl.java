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
    private static final String MEDIUM_CACHE_IMAGE_STORAGE_PATH = ".file.storage.medium.cache";
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
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + filePath);
        return gridFs.findOne(new ObjectId(imageId));
    }


    /**
     * @param fileUploadWrapper
     * @return
     */
    @Override
    public String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper) {


        // first call of method with originalImageId = null
        String originalImageId = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(fileUploadWrapper.getInputStream());

            originalImageId = cacheImage(fileUploadWrapper, largeBufferedImageProfilePreparator(bufferedImage), originalImageId, LARGE_CACHE_IMAGE_STORAGE_PATH);

            cacheImage(fileUploadWrapper, smallBufferedImageProfilePreparator(bufferedImage), originalImageId, SMALL_CACHE_IMAGE_STORAGE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return originalImageId;
    }

    /**
     * @param fileUploadWrapper
     * @return
     */
    @Override
    public String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper) {

        // first call of method with originalImageId = null
        String originalImageId = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(fileUploadWrapper.getInputStream());

            originalImageId = cacheImage(fileUploadWrapper, largeBufferedImageOfferPreparator(bufferedImage), originalImageId, LARGE_CACHE_IMAGE_STORAGE_PATH);

            cacheImage(fileUploadWrapper, mediumBufferedImageOfferPreparator(bufferedImage), originalImageId, MEDIUM_CACHE_IMAGE_STORAGE_PATH);

            cacheImage(fileUploadWrapper, smallBufferedImageOfferPreparator(bufferedImage), originalImageId, SMALL_CACHE_IMAGE_STORAGE_PATH);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return originalImageId;
    }

    /**
     * @param fileUploadWrapper
     * @param scaledImage
     * @param originalImageId
     * @param filePath
     * @return
     * @throws IOException
     */
    private String cacheImage(FileUploadWrapper fileUploadWrapper, BufferedImage scaledImage, String originalImageId, String filePath) throws IOException {

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


        //ToDo Delete this in future
//        System.err.println("FileName: " + fileUploadWrapper.getFilename()  + " filePath: " + filePath + " ||| imgId: " + originalImageId);

        return inputFile.getId().toString();
    }

    /**
     * @param bufferedImage
     * @return
     */
    private BufferedImage smallBufferedImageProfilePreparator(BufferedImage bufferedImage) {
        return Scalr.resize(bufferedImage, profileSmallCachedSize);
    }

    /**
     * @param bufferedImage
     * @return
     */
    private BufferedImage largeBufferedImageProfilePreparator(BufferedImage bufferedImage) {
        return Scalr.resize(bufferedImage, profileLargeCachedSize);
    }


    /**
     * @param inputImage
     * @return
     */
    private BufferedImage largeBufferedImageOfferPreparator(BufferedImage inputImage) {


        int originHeight = inputImage.getHeight();


        if (originHeight > 600) {
            return Scalr.resize(inputImage, Scalr.Mode.FIT_TO_HEIGHT, 600);
        } else {
            return Scalr.resize(inputImage, Scalr.Mode.FIT_TO_HEIGHT, originHeight);
        }
    }


    /**
     * @param inputImage
     * @return
     */
    private BufferedImage mediumBufferedImageOfferPreparator(BufferedImage inputImage) {
        return Scalr.resize(inputImage, Scalr.Mode.AUTOMATIC, 165, 120);
    }


    /**
     * @param inputImage
     * @return
     */
    private BufferedImage smallBufferedImageOfferPreparator(BufferedImage inputImage) {
        return Scalr.resize(inputImage, Scalr.Mode.AUTOMATIC, 90, 90);
    }

}
