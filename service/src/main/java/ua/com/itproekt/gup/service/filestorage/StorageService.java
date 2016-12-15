package ua.com.itproekt.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StorageService {

    /**
     * Delete file from database
     *
     * @param serviceName   - the service name
     * @param fileId        - the file ID
     */
    void delete(String serviceName, String fileId);

    /**
     * @param serviceName   - the service name.
     * @param fileIds       - the set of the fields ID.
     */
    void delete(String serviceName, Set<String> fileIds);

    /**
     * @param imagesId - the image ID.
     */
    void deleteListOfOfferImages(Set<String> imagesId);


    /**
     * Find and return one image.
     *
     * @param serviceName   - the service name.
     * @param filePath      - the file path.
     * @param fileId        - the file ID.
     * @return              - the image in the GridFSDBFile format.
     */
    GridFSDBFile getCachedImage(String serviceName, String filePath, String fileId);


    /**
     *
     * @param serviceName
     * @param fileId
     * @param cachedSize
     * @return
     */
    ResponseEntity readCachedImage(String serviceName, String fileId, String cachedSize);

    /**
     * Save photo in two variants: large and small
     *
     * @return              - id of image
     */
    String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper);


    /**
     * Save photo in several size variants.
     *
     * @return              - id of image
     */
    String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper);


    /**
     * Download images with urls and return array of MultipartFile
     *
     * @param imagesUrlList - the list of the images URLs
     * @return              - the files array.
     */
    MultipartFile[] imageDownloader(List<String> imagesUrlList);


    /**
     * Download one image with urls and return one multipartfile.
     *
     * @param imageUrl      - the image url.
     * @return              - the multipart file.
     */
    MultipartFile imageDownloader(String imageUrl);

}
