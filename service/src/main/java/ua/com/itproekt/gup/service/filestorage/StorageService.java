package ua.com.itproekt.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;

import java.util.Map;
import java.util.Set;

public interface StorageService {

    /**
     * @param serviceName
     * @param fileId
     */
    void delete(String serviceName, String fileId);

    /**
     * @param serviceName
     * @param fileIds
     */
    void delete(String serviceName, Set<String> fileIds);

    /**
     * @param imagesId
     */
    void deleteListOfOfferImages(Set<String> imagesId);

    /**
     * Find images in old offer version< that were deleted in new and delete them from base in all cached variants.
     *
     * @param oldImagesMap
     * @param newImagesMap
     */
    void deleteDiffImagesAfterOfferUpdate(Map<String, String> oldImagesMap, Map<String, String> newImagesMap);


    /**
     * @param serviceName
     * @param filePath
     * @param fileId
     * @return
     */
    GridFSDBFile getCachedImage(String serviceName, String filePath, String fileId);

    /**
     * Save photo in two variants: large and small
     *
     * @return - id of image
     */
    String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper);


    /**
     * Save photo in several size variants.
     *
     * @return - id of image
     */
    String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper);


    /**
     * Method save multiply images in different sizes (cached), and return map of ImagesId and image position
     *
     * @param files
     * @param firstPosition
     * @return
     */
    Map<String, String> saveCachedMultiplyImageOffer(MultipartFile[] files, int firstPosition);
}
