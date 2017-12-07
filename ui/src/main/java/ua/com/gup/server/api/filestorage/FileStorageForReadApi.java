package ua.com.gup.server.api.filestorage;


import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.service.profile.ProfilesService;


/**
 * This class designed for shorten images url.
 */
@Controller
public class FileStorageForReadApi {


    @Autowired
    private StorageService storageService;

    /**
     * @param fileId     - id of image
     * @param cachedSize - for Profile: large, small. For Offer: large, medium, small.
     * @return file
     */
    @CrossOrigin
    @RequestMapping(value = "/obyavlenie/image/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource>
    getById(@PathVariable String fileId, @RequestParam(defaultValue = "large") String cachedSize) {

        GridFSDBFile gridFSDBFile;

        String path = ".file.storage." + cachedSize + ".cache";

        gridFSDBFile = storageService.getCachedImage("offers", path, fileId);

        if (gridFSDBFile != null) {
            return ResponseEntity.ok()
                    .contentLength(gridFSDBFile.getLength())
                    .contentType(MediaType.parseMediaType(gridFSDBFile.getContentType()))
                    .header("Content-Disposition", "attachment; filename=" + gridFSDBFile.getFilename())
                    .body(new InputStreamResource(gridFSDBFile.getInputStream()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    
}
