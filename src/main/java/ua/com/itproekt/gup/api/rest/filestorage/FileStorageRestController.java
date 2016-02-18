package ua.com.itproekt.gup.api.rest.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.EnumUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.ServiceNames;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

@RestController
@RequestMapping("/api/rest/fileStorage")
public class FileStorageRestController {
    private static final Logger LOG = Logger.getLogger(FileStorageRestController.class);

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "{serviceName}/file/read/id/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource>
    getById(@PathVariable String serviceName, @PathVariable String fileId,
            @RequestParam(required = false, defaultValue = "false") boolean cachedImage) throws IOException {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        GridFSDBFile gridFSDBFile;
        if (cachedImage) {
            gridFSDBFile = storageService.getCachedImage(serviceName.toUpperCase(), fileId);
        } else {
            gridFSDBFile = storageService.get(serviceName.toUpperCase(), fileId);
        }

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
    public ResponseEntity<CreatedObjResp>
    fileUpload(@PathVariable String serviceName, @RequestParam MultipartFile file,
               @RequestParam(required = false, defaultValue = "false") boolean cacheImage){

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            System.err.println("        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!file.isEmpty()) {
            try {
                String uploadedFileId = storageService.save(serviceName.toUpperCase(),
                        file.getInputStream(),
                        file.getContentType(),
                        file.getOriginalFilename());

                if (cacheImage){
                    System.err.println("if cacheImage file.getContentType()::" + file.getContentType());
                    if (file.getContentType().startsWith("image/")) {
                        storageService.cacheImage(serviceName.toUpperCase(),
                                uploadedFileId,
                                ImageIO.read(file.getInputStream()),
                                file.getContentType(),
                                file.getOriginalFilename());
                    } else {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }

                return new ResponseEntity<>(new CreatedObjResp(uploadedFileId), HttpStatus.CREATED);
            } catch (IOException e) {
                System.err.println("IOException");

                StringWriter stack = new StringWriter();
                e.printStackTrace(new PrintWriter(stack));
                LOG.error(stack.toString());

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            System.err.println("else BAD_REQUEST");

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
