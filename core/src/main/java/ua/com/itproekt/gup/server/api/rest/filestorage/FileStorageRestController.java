package ua.com.itproekt.gup.server.api.rest.filestorage;

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
import ua.com.itproekt.gup.util.LogUtil;
import ua.com.itproekt.gup.util.ServiceNames;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/rest/fileStorage") //@RequestMapping("/api/rest/fileStorage")
public class FileStorageRestController {
    private static final Logger LOG = Logger.getLogger(FileStorageRestController.class);

    @Autowired
    private StorageService storageService;


    /**
     * @param serviceName servicename in lowercase or in uppercase
     * @param fileId      file ID to read
     * @param cachedImage boolean parameter. If true - read cached image
     * @return return file
     */
    @RequestMapping(value = "{serviceName}/file/read/id/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource>
    getById2(@PathVariable String serviceName, @PathVariable String fileId,
             @RequestParam(required = false, defaultValue = "false") boolean cachedImage) {

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
                    .header("Content-Disposition", "attachment; filename=" + gridFSDBFile.getFilename())
                    .body(new InputStreamResource(gridFSDBFile.getInputStream()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param serviceName servicename in lowercase or in uppercase
     * @param file        file
     * @param cachedImage boolean parameter. If true - read cached image
     * @return id of uploaded files
     */
    //ToDo поставить ПреАвторайз
    @RequestMapping(value = "{serviceName}/file/upload", method = RequestMethod.POST)
    public ResponseEntity<CreatedObjResp>
    fileUpload(@PathVariable String serviceName, @RequestParam MultipartFile file) {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!file.isEmpty()) {
            try {
                String uploadedFileId = storageService.save(serviceName.toUpperCase(),
                        file.getInputStream(),
                        file.getContentType(),
                        file.getOriginalFilename());

                return new ResponseEntity<>(new CreatedObjResp(uploadedFileId), HttpStatus.CREATED);
            } catch (IOException ex) {
                LOG.error(LogUtil.getExceptionStackTrace(ex));
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param serviceName servicename in lowercase or in uppercase
     * @param file        file
     * @param cachedImage boolean parameter. If true - read cached image
     * @return id of uploaded files
     */
    //ToDo поставить ПреАвторайз
    //ToDo boolean cachedImage заменить на параметр для определения Профиля или Оффера
    @RequestMapping(value = "{serviceName}/photo/upload", method = RequestMethod.POST)
    public ResponseEntity<CreatedObjResp>
    photoUpload(@PathVariable String serviceName, @RequestParam MultipartFile file,
                @RequestParam(required = false, defaultValue = "false") boolean cachedImage) {

        if (!EnumUtils.isValidEnum(ServiceNames.class, serviceName.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!file.isEmpty()) {
            try {
                String uploadedFileId = storageService.save(serviceName.toUpperCase(),
                        file.getInputStream(),
                        file.getContentType(),
                        file.getOriginalFilename());

                if (cachedImage) {
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
            } catch (IOException ex) {
                LOG.error(LogUtil.getExceptionStackTrace(ex));
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
