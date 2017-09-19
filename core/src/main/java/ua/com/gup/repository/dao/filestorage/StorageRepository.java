package ua.com.itproekt.gup.dao.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;

import java.util.Set;

public interface StorageRepository {

    /**
     * @param serviceName
     * @param filePath
     * @param fileId
     */
    void delete(String serviceName, String filePath, String fileId);

    /**
     * @param serviceName
     * @param filePath
     * @param fileIds
     */
    void delete(String serviceName, String filePath, Set<String> fileIds);

    /**
     * @param serviceName
     * @param filePath
     * @param imageId
     * @return
     */
    GridFSDBFile getCachedImage(String serviceName, String filePath, String imageId);


    /**
     *
     * @param fileUploadWrapper
     * @return
     */
    String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper);


    /**
     *
     * @param fileUploadWrapper
     * @return
     */
    String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper);
}
