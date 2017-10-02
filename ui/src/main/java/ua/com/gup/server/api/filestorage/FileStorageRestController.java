package ua.com.gup.server.api.filestorage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.model.CreatedObjResp;
import ua.com.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/fileStorage")
public class FileStorageRestController {
    private static final Logger LOG = Logger.getLogger(FileStorageRestController.class);

    @Autowired
    private StorageService storageService;

    /**
     * @param serviceName - service name in lower or upper case
     * @param fileId      - id of image.
     * @param cachedSize  - for Profile: large, small. For Offer: large, medium, small.
     * @return - the file/
     */
    @CrossOrigin
    @RequestMapping(value = "{serviceName}/photo/read/id/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource>
    getById(@PathVariable String serviceName, @PathVariable String fileId,
            @RequestParam(defaultValue = "large") String cachedSize) {

        if (!isServiceNameAndRequestParamValid(serviceName, cachedSize)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return storageService.readCachedImage(serviceName, fileId, cachedSize);
    }


    /**
     * Return main user photo (avatar)
     *
     * @param userId     - the user ID which photo need to be downloaded.
     * @param cachedSize - the cacheSize of the image.
     * @return file if ok, 404 if profile is not found or if there is no photo of user
     */
    @CrossOrigin
    @RequestMapping(value = "profile/photo/read/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource>
    getAvatarPictureByUserId(@PathVariable String userId,
                             @RequestParam(defaultValue = "large") String cachedSize) {

        return storageService.readProfileCachedImage(userId, cachedSize);
    }


    /**
     * @param file
     * @return id of uploaded files
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "profile/photo/upload", method = RequestMethod.POST)
    public ResponseEntity<CreatedObjResp>
    photoUpload(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!file.getContentType().startsWith("image/")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return storageService.saveCachedImageProfile(file);
    }


    /**
     * Delete profile photo (avatar), and replace it with stub.
     *
     * @return - status Ok or Bad Request
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "profile/photo/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteProfilePhoto() {
        String userId = SecurityOperations.getLoggedUserId();
        if (userId != null) {
            storageService.deleteProfileImage(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Easy-delete profile photo (avatar), and replace it with stub.
     *
     * @return - status Ok or Bad Request
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "profile/photo/justDelete", method = RequestMethod.POST)
    public ResponseEntity<Void>
    justDeleteProfilePhoto(@RequestParam MultipartFile file) {
        String userId = SecurityOperations.getLoggedUserId();
        if (userId != null) {
            storageService.deleteProfileImage(userId);
            if (file.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!file.getContentType().startsWith("image/")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            storageService.saveCachedImageProfile(file);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean isServiceNameAndRequestParamValid(String serviceName, String param) {
        if (serviceName.toLowerCase().equals("profile")) {
            if (param.equals("large") || param.equals("small")) {
                return true;
            }
        }
        return true;
    }


}