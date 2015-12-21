package ua.com.itproekt.gup.controller.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ProfilesService profilesService;

    private static final Logger logger = Logger.getLogger("globalExceptionHandler");

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody Map<String, Object> handleValidationException(HttpServletRequest request,
                                                                       Principal principal,
                                                                       MethodArgumentNotValidException ex) {

        String userEmail = (principal == null ? "NULL" : principal.getName());
        StringWriter stack = new StringWriter();

        ex.printStackTrace(new PrintWriter(stack)); // **********

        logger.error("UncaughtException Occured:: URL=" + request.getRequestURL() + ";" +
                "   User email: " + userEmail + ";" +
                "   Exception: " + stack.toString());

        Map<String, Object>  map = new HashMap();
        map.put("error", "Validation Failure");
        map.put("violations", convertConstraintViolation(ex));
        return map;
    }

    private Map<String, Map<String, Object> > convertConstraintViolation(MethodArgumentNotValidException ex) {
        Map<String, Map<String, Object> > result = new HashMap();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            Map<String, Object>  violationMap = new HashMap();
            violationMap.put("target", ex.getBindingResult().getTarget());
            violationMap.put("type", ex.getBindingResult().getTarget().getClass());
            violationMap.put("message", error.getDefaultMessage());
            result.put(error.getObjectName(), violationMap);
        }
        return result;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>>  handleAccessDeniedException(HttpServletRequest request,
                                                                     Principal principal,
                                                                     Exception ex) {
        String userEmail = (principal == null ? "NULL" : principal.getName());
        StringWriter stack = new StringWriter();

        ex.printStackTrace(new PrintWriter(stack)); // **********

        logger.error("AccessDeniedException Occured:: URL=" + request.getRequestURL() + ";" +
                "   User email: " + userEmail + ";" +
                "   Exception: " + stack.toString());

        Map<String, Object>  map = new HashMap();
        map.put("ex", "AccessDeniedException");
        if (ex.getCause() != null) {
            map.put("cause", ex.getCause().getMessage());
        } else {
            map.put("cause", ex.getMessage());
        }

        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
    handleAuthenticationCredentialsNotFoundException(HttpServletRequest request,
                                                     Principal principal,
                                                     Exception ex) {
        String userEmail = (principal == null ? "NULL" : principal.getName());
        StringWriter stack = new StringWriter();

        ex.printStackTrace(new PrintWriter(stack)); // **********

        logger.error("AuthenticationCredentialsNotFoundException Occured:: URL=" + request.getRequestURL() + ";" +
                "   User email: " + userEmail + ";" +
                "   Exception: " + stack.toString());

        Map<String, Object>  map = new HashMap();
        map.put("ex", "AuthenticationCredentialsNotFoundException");
        if (ex.getCause() != null) {
            map.put("cause", ex.getCause().getMessage());
        } else {
            map.put("cause", ex.getMessage());
        }

        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Map<String, Object> handleUncaughtException(HttpServletRequest request,
                                                                     Principal principal,
                                                                     Exception ex) {
        String userEmail = (principal == null ? "NULL" : principal.getName());
        StringWriter stack = new StringWriter();

        ex.printStackTrace(new PrintWriter(stack)); // **********

        logger.error("UncaughtException Occured:: URL=" + request.getRequestURL() + ";" +
                "   User email: " + userEmail + ";" +
                "   Exception: " + stack.toString());

        ////////////////////////////////////////////////////////////////
        Map<String, Object>  map = new HashMap();
        map.put("error", "Unknown Error");
        if (ex.getCause() != null) {
            map.put("cause", ex.getCause().getMessage());
        } else {
            map.put("cause", ex.getMessage());
        }
        return map;
    }

}
