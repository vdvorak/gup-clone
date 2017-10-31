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
@RequestMapping(path = "/api/storage")
public class ImageEndpoint {


    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/images", method = RequestMethod.POST)
    public ResponseEntity<String> saveImage(@RequestParam(name = "image") MultipartFile multipartFile) throws BadRequestException, IOException {


        if (StringUtils.isEmpty(multipartFile.getContentType()) || !multipartFile.getContentType().startsWith("image/")) {
            throw new BadRequestException("Invalid content-type header, must start with \"image/\".");
        }
        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            throw new BadRequestException("Original file name is empty.");
        }
        if (multipartFile.getOriginalFilename().contains("..")) {
            throw new BadRequestException(String.format("Unacceptable (relative) path detected %s.", multipartFile.getOriginalFilename()));
        }
        if (multipartFile.isEmpty()) {
            throw new BadRequestException("File body is empty.");
        }

        String s = UUID.randomUUID().toString();
        String contentMD5 = imageService.saveImage(s, multipartFile.getContentType(), multipartFile.getInputStream());
        return new ResponseEntity<>(contentMD5, HttpStatus.CREATED);
    }


    @RequestMapping(path = {"/images/{imageKey}"}, method = RequestMethod.GET)
    public ResponseEntity<ImageDTO> findImageByKey(@PathVariable("imageKey") String imageKey)
            throws ImageNotFoundException {

        ImageDTO image = imageService.getImageByKey(imageKey);
        if (image == null)
            throw new ImageNotFoundException(imageKey);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }


    @RequestMapping(path = {"/images/{imageKey}"}, method = RequestMethod.DELETE)
    public ResponseEntity<ImageDTO> deleteImageByName(@PathVariable("imageKey") String imageKey) {

        imageService.deleteImageByKey(imageKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
