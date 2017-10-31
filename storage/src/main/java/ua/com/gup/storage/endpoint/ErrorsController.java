package ua.com.gup.storage.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.com.gup.storage.exception.BadRequestException;
import ua.com.gup.storage.exception.ImageNotFoundException;

@ControllerAdvice
public class ErrorsController {

    final static Logger logger = LoggerFactory.getLogger(ErrorsController.class);

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity imageNotFound(ImageNotFoundException ex) {
        String exMessage = String.format("Image with key: %s not found", ex.getImageKey());
        logger.info(exMessage);
        return new ResponseEntity(exMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequest(BadRequestException ex) {
        logger.info(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
