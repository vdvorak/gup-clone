package ua.com.gup.rent.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.gup.common.command.CommandException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CommandException.class})
    public ResponseEntity handleAccessDeniedException(Exception e) {
        return new ResponseEntity("Внутрішня помилка, адміністратора вже повідомлено", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
