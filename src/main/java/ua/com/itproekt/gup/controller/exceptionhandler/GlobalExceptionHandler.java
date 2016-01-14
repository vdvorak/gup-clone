package ua.com.itproekt.gup.controller.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    public void logException(HttpServletRequest request, Principal principal, Exception ex) {
        String userEmail = (principal == null ? "NULL" : principal.getName());

        StringWriter stack = new StringWriter();
        ex.printStackTrace(new PrintWriter(stack));

        LOG.error(" URL [" + request.getRequestURL() + "];"
                + "   User email: " + userEmail + ";"
                + "   Exception: " + stack.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleValidationException(HttpServletRequest request, Principal principal,
                                            MethodArgumentNotValidException ex) {

        logException(request, principal, ex);

        return "error/error";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String  handleAccessDeniedException(HttpServletRequest request, Principal principal,
                                               AccessDeniedException ex) {
        logException(request, principal, ex);

        return "error/403";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public String handleAuthCredentialsNotFoundEx(HttpServletRequest request, Principal principal,
                                                  AuthenticationCredentialsNotFoundException ex) {
        logException(request, principal, ex);

        return "error/401";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleUncaughtException(HttpServletRequest request, Principal principal,
                                          Exception ex) {
        logException(request, principal, ex);

        return "error/error";
    }

}
