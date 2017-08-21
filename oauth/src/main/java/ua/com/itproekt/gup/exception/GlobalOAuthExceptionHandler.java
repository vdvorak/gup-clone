package ua.com.itproekt.gup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler  extends BaseException{


    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomizerError methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new CustomizerError(ex.getBindingResult().getFieldError().getField(),ex.getBindingResult().getFieldError().getCode(),ex.getBindingResult().getFieldError().getDefaultMessage());
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomizerError Exception(Exception ex) {
        return new CustomizerError("no filed","500",new String(ex.getMessage()));
    }

}
