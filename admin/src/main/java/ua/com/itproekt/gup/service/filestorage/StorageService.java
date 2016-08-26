package ua.com.itproekt.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public interface StorageService {
    String save(String serviceName, InputStream inputStream, String contentType, String filename);

    GridFSDBFile get(String serviceName, String fileId);

    void delete(String serviceName, String fileId);

    void delete(String serviceName, Set<String> fileIds);

    void cacheImage(String serviceName, String originalImageId, BufferedImage bufferedImage, String contentType, String originalFilename) throws IOException;

    GridFSDBFile getCachedImage(String serviceName, String fileId);
}
