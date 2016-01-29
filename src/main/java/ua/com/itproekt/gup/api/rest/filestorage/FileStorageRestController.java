package ua.com.itproekt.gup.api.rest.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.util.CreatedObjResponse;
import ua.com.itproekt.gup.util.ServiceNames;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/api/rest/fileStorage")
public class FileStorageRestController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "{serviceName}/file/read/id/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getById(@PathVariable String serviceName,
                                                       @PathVariable String fileId) throws IOException {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        GridFSDBFile gridFSDBFile = storageService.get(serviceName.toUpperCase(), fileId);

        if (gridFSDBFile != null) {
            return ResponseEntity.ok()
                    .contentLength(gridFSDBFile.getLength())
                    .contentType(MediaType.parseMediaType(gridFSDBFile.getContentType()))
                    .body(new InputStreamResource(gridFSDBFile.getInputStream()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="{serviceName}/file/upload", method=RequestMethod.POST)
    public ResponseEntity<CreatedObjResponse> fileUpload(@PathVariable String serviceName,
                                                         @RequestParam MultipartFile file){

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!file.isEmpty()) {
            try {
                String uploadedFileId = storageService.save(serviceName.toUpperCase(),
                        file.getInputStream(),
                        file.getContentType(),
                        file.getOriginalFilename());

                return new ResponseEntity<>(new CreatedObjResponse(uploadedFileId), HttpStatus.CREATED);
            } catch (IOException e) {
//                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{serviceName}/file/delete/id/{fileId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteFile(@PathVariable String serviceName,
                                           @PathVariable String fileId) {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        storageService.delete(serviceName.toUpperCase(), fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{serviceName}/file/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteFiles(@PathVariable String serviceName,
                                            @RequestParam(value = "fileId") Set<String> fileIds) {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        storageService.delete(serviceName.toUpperCase(), fileIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
