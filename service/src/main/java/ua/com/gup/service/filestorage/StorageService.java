package ua.com.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import java.util.Map;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.mongo.model.file.FileUploadWrapper;
import ua.com.gup.mongo.model.other.CreatedObjResp;

import java.util.Set;

public interface StorageService {

    /**
     * Delete file from database
     *
     * @param serviceName - the service name
     * @param fileId      - the file ID
     */
    void delete(String serviceName, String fileId);

    /**
     * @param serviceName - the service name.
     * @param fileIds     - the set of the fields ID.
     */
    void delete(String serviceName, Set<String> fileIds);

    /**
     * Delete profile photo (avatar) and replace it with stub image.
     *
     * @param userId - the user ID.
     */
    void deleteProfileImage(String userId);

    /**
     * @param imagesId - the image ID.
     */
    void deleteListOfOfferImages(Set<String> imagesId);


    /**
     * Find and return one image.
     *
     * @param serviceName - the service name.
     * @param filePath    - the file path.
     * @param fileId      - the file ID.
     * @return - the image in the GridFSDBFile format.
     */
    GridFSDBFile getCachedImage(String serviceName, String filePath, String fileId);
  

    /**
     * Save photo in two variants: large and small
     *
     * @param file - the image.
     * @return
     */
    public Map<String,String> saveCachedImageProfile(String profileId, MultipartFile file);


    /**
     * Save photo in several size variants.
     *
     * @return - id of image
     */
    String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper);

}
