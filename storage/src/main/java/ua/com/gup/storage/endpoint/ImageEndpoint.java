package ua.com.gup.storage.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.storage.dto.ImageDTO;
import ua.com.gup.storage.exception.BadRequestException;
import ua.com.gup.storage.exception.ImageNotFoundException;
import ua.com.gup.storage.service.ImageService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/images")
public class ImageEndpoint {


    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<ImageDTO> saveImage(@RequestParam(name = "image") MultipartFile multipartFile) throws BadRequestException, IOException {


        if (StringUtils.isEmpty(multipartFile.getContentType()) || !multipartFile.getContentType().startsWith("image/")) {
            throw new BadRequestException("Invalid content-type header, must start with \"image/\".");
        }
        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            throw new BadRequestException("Original file name is empty.");
        }
        if (multipartFile.getOriginalFilename().contains("..")) {
            throw new BadRequestException(String.format("Unacceptable (relative) path detected %s3id.", multipartFile.getOriginalFilename()));
        }
        if (multipartFile.isEmpty()) {
            throw new BadRequestException("File body is empty.");
        }

        String s3id = UUID.randomUUID().toString();
        while (imageService.doesObjectExists(s3id)) {
            s3id = UUID.randomUUID().toString();
        }

        String contentMD5 = imageService.saveImage(s3id, multipartFile.getContentType(), multipartFile.getInputStream());
        ImageDTO imageDTO = new ImageDTO(s3id, contentMD5);
        return new ResponseEntity<>(imageDTO, HttpStatus.CREATED);
    }


    @RequestMapping(path = {"/{imageKey}"}, method = RequestMethod.GET)
    public ResponseEntity<ImageDTO> findImageByKey(@PathVariable("imageKey") String imageKey)
            throws ImageNotFoundException {

        ImageDTO image = imageService.getImageByKey(imageKey);
        if (image == null)
            throw new ImageNotFoundException(imageKey);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }


    @RequestMapping(path = {"/{imageKey}"}, method = RequestMethod.DELETE)
    public ResponseEntity<ImageDTO> deleteImageByName(@PathVariable("imageKey") String imageKey) {

        imageService.deleteImageByKey(imageKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
