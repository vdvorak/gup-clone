package com.journaldev.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.journaldev.spring.CreatedObjResp;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "/fileStorage")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * Upload single file using Spring Controller
     * ******************************************
     * 200 OK («хорошо»)
     * 201 Created («создано»)
     * 400 Bad Request («плохой, неверный запрос»)
     * 406 Not Acceptable («неприемлемо»)
     *
     * @param name
     * @param file
     * @return
     */
    @RequestMapping(value = "/photo/upload", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<CreatedObjResp> photoUpload(@RequestParam("name") String name,
                                                                    @RequestParam("file") MultipartFile file,
                                                                    @RequestParam(required = false, defaultValue = "false") boolean cachedImage) {
        ResponseEntity<CreatedObjResp> resp = null;
        if (!file.isEmpty()) {
            try {
                // Creating the directory to store file
                String rootPath =  "D:\\IdeaProjects\\GUP\\gup\\file-storage";
                File        dir = new File(rootPath + File.separator + "photos");
                if (!dir.exists()) dir.mkdirs();

                // Create the file on server
                byte[]                bytes = file.getBytes();
                File             serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                resp = new ResponseEntity<>(new CreatedObjResp(name), HttpStatus.CREATED);
                logger.info("Server File Location=" + serverFile.getAbsolutePath() + " | " + name);
            } catch (Exception e) {
                resp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                logger.error("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            logger.error("You failed to upload " + name + " because the file was empty.");
        }
        return resp;
    }

}
