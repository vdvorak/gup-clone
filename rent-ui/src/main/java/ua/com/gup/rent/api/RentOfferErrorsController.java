package ua.com.gup.rent.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RentOfferErrorsController {

    final static Logger logger = LoggerFactory.getLogger(RentOfferErrorsController.class);

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindException(BindException ex) {

        System.out.println(ex);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
