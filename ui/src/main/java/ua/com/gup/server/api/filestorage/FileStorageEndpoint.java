package ua.com.gup.server.api.filestorage;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.util.security.SecurityUtils;

@RestController
@RequestMapping("/api/rest/fileStorage")
public class FileStorageEndpoint {

    private static final Logger LOG = Logger.getLogger(FileStorageEndpoint.class);

    @Autowired
    private StorageService storageService;

    /**
     * @param file
     * @return id of uploaded files
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "profile/photo", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> photoUpload(@RequestParam MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!file.getContentType().startsWith("image/")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String currentUserId = SecurityUtils.getCurrentUserId();        
        Map<String, String> imageUrls = storageService.saveCachedImageProfile(currentUserId, file);
        if (imageUrls == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(imageUrls, HttpStatus.CREATED);
    }

    /**
     * Delete profile photo (avatar), and replace it with stub.
     *
     * @return - status Ok or Bad Request
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "profile/photo", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProfilePhoto() {
       String userId = SecurityUtils.getCurrentUserId();       
        if (userId != null) {
            storageService.deleteProfileImage(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }   
}
