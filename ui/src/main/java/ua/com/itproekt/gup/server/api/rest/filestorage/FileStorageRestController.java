package ua.com.itproekt.gup.server.api.rest.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.EnumUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.LogUtil;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.ServiceNames;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

@RestController
@RequestMapping("/api/rest/fileStorage")
public class FileStorageRestController {
    private static final Logger LOG = Logger.getLogger(FileStorageRestController.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProfilesService profilesService;


    /**
     * @param serviceName - service name in lower or upper case
     * @param fileId      - id of image
     * @param cachedSize  - for Profile: large, small. For Offer: large, medium, small.
     * @return file
     */
    @CrossOrigin
    @RequestMapping(value = "{serviceName}/photo/read/id/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource>
    getById(@PathVariable String serviceName, @PathVariable String fileId,
            @RequestParam(required = true, defaultValue = "large") String cachedSize) {

        if (!isServiceNameAndRequestParamValid(serviceName, cachedSize)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        GridFSDBFile gridFSDBFile;

        String path = ".file.storage." + cachedSize + ".cache";

        gridFSDBFile = storageService.getCachedImage(serviceName, path, fileId);

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


    /**
     * @param serviceName service name in lowercase or in uppercase
     * @param file        file
     * @return id of uploaded files
     */
    //ToDo включить ПреАвторайз
//    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "{serviceName}/photo/upload", method = RequestMethod.POST)
    public ResponseEntity<CreatedObjResp>
    photoUpload(@PathVariable String serviceName, @RequestParam MultipartFile file) {


        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!file.getContentType().startsWith("image/")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();


        // Prepare file
        try {
            fileUploadWrapper
                    .setServiceName(serviceName.toUpperCase())
                    .setInputStream(file.getInputStream())
                    .setContentType(file.getContentType())
                    .setFilename(file.getOriginalFilename());
        } catch (IOException ex) {
            LOG.error(LogUtil.getExceptionStackTrace(ex));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // if service is "Profile"
        if (serviceName.toLowerCase().equals("profile")) {
            String uploadedFileId = storageService.saveCachedImageProfile(fileUploadWrapper);
            return new ResponseEntity<>(new CreatedObjResp(uploadedFileId), HttpStatus.CREATED);

        } else {

            // ToDo скорее всего часть для оффера можно будет удалить, т.к.

            // if service is "offers"
            if (serviceName.toLowerCase().equals("offers")) {
                String uploadedFileId = storageService.saveCachedImageOffer(fileUploadWrapper);
                return new ResponseEntity<>(new CreatedObjResp(uploadedFileId), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method accept set of files Ides
     *
     * @param serviceName - service name in uppercase or in lowercase
     * @param fileIds     - set of files Ides
     * @return - status code 204 if all is ok, 404 - if id of photo not found
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "{serviceName}/file/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteFiles(@PathVariable String serviceName,
                                            @RequestParam(value = "param[]") Set<String> fileIds) {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (serviceName.toUpperCase().equals("PROFILE")) {
            // can be deleted only avatar picture
            String userId = SecurityOperations.getLoggedUserId();
            Profile profile = profilesService.findById(userId);

            Iterator iter = fileIds.iterator();

            if (iter.next() != profile.getImgId()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            storageService.delete(serviceName.toUpperCase(), fileIds);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        storageService.delete(serviceName.toUpperCase(), fileIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private boolean isServiceNameAndRequestParamValid(String serviceName, String param) {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return false;
        }

        if (serviceName.toLowerCase().equals("profile")) {
            if (param.equals("large") || param.equals("small")) {
                return true;
            }
        }

        if (serviceName.toLowerCase().equals("offers")) {
            if (param.equals("large") || param.equals("medium") || param.equals("small")) {
                return true;
            }
        }

        return true;
    }




    //---------------------------------------------------------- TEST ------------------------------
    @CrossOrigin
    @RequestMapping(value = "{serviceName}/photo/multi/upload", method = RequestMethod.POST)
    public ResponseEntity<CreatedObjResp>
    multiplyPhotoUpload(@PathVariable String serviceName, @RequestParam MultipartFile[] files) {

        System.err.println("Now will be file names");

        for (MultipartFile file : files) {
            System.err.println("File originalname: " + file.getOriginalFilename());
            System.err.println("File name: " + file.getName());
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

}