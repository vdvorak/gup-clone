package ua.com.itproekt.gup.dao.filestorage;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@Repository
public class StorageRepositoryImpl implements StorageRepository {
    private static final String FILE_STORAGE_PATH = ".file.storage";
    private static final String CACHE_IMAGE_STORAGE_PATH = ".file.storage.cache";

//    @Value("${upload.profile.photo.cached.size}")
    private String size = "40";

//    @Value("${upload.offer.photo.cached.size.offerSmall}")
//    private String offerSmall;
//
//    @Value("${upload.offer.photo.cached.size.offerMedium}")
//    private String offerMedium;


    @Autowired
    private MongoTemplate mongoTemplate;

    private GridFS gridFs;

    @Override
    public String save(String serviceName, InputStream inputStream, String contentType, String filename) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE_PATH);
        GridFSInputFile input = gridFs.createFile(inputStream, true);
        input.setContentType(contentType);
        input.setFilename(filename);
        input.save();
        return input.getId().toString();
    }

    @Override
    public GridFSDBFile get(String serviceName, String fileId) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE_PATH);
        return gridFs.findOne(new ObjectId(fileId));
    }

    @Override
    public void delete(String serviceName, String fileId) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE_PATH);
        if (ObjectId.isValid(fileId)) {
            gridFs.remove(new ObjectId(fileId));
        }
    }

    @Override
    public void delete(String serviceName, Set<String> fileIds) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE_PATH);
        for (String fileId : fileIds) {
            if (ObjectId.isValid(fileId)) {
                gridFs.remove(new ObjectId(fileId));
            }
        }
    }

    @Override
    public void cacheImage(String serviceName, String originalImageId, BufferedImage bufferedImage, String contentType, String originalFilename) throws IOException {
        int intSize = Integer.valueOf(size);

        BufferedImage scaledImage = Scalr.resize(bufferedImage, intSize);
        String imageFormatName = contentType.substring(contentType.lastIndexOf("/") + 1);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, imageFormatName, os);

        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + CACHE_IMAGE_STORAGE_PATH);
        GridFSInputFile inputFile = gridFs.createFile(new ByteArrayInputStream(os.toByteArray()),
                originalFilename, true);
        inputFile.setId(new ObjectId(originalImageId));
        inputFile.setContentType(contentType);
        inputFile.save();
    }

    @Override
    public GridFSDBFile getCachedImage(String serviceName, String imageId) {
        this.gridFs = new GridFS(mongoTemplate.getDb(), serviceName + CACHE_IMAGE_STORAGE_PATH);
        return gridFs.findOne(new ObjectId(imageId));
    }
}
