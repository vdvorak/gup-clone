package ua.com.itproekt.gup.controller.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);
    @Autowired
    ProfilesService profilesService;

    public void logException(HttpServletRequest request, Principal principal, Exception ex) {
        String userEmail = (principal == null ? "NULL" : principal.getName());

        StringWriter stack = new StringWriter();
        ex.printStackTrace(new PrintWriter(stack));

        LOG.error("   URL: [" + request.getRequestURL() + "]" +
                "   User email: [" + userEmail + "]" +
                "   Exception: [" + stack.toString() + "]");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public String handleAuthCredentialsNotFoundEx(HttpServletRequest request, Principal principal,
                                                  AuthenticationCredentialsNotFoundException ex) {
        logException(request, principal, ex);

        return "error/401";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(HttpServletRequest request, Principal principal,
                                              AccessDeniedException ex) {
        logException(request, principal, ex);

        return "error/403";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(HttpServletRequest request, Principal principal,
                                                  ResourceNotFoundException ex) {
        logException(request, principal, ex);

        return "error/404";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleValidationException(HttpServletRequest request, Principal principal,
                                            MethodArgumentNotValidException ex) {
        logException(request, principal, ex);

        return "error/error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUncaughtException(HttpServletRequest request, Principal principal,
                                          Exception ex) {
        logException(request, principal, ex);

        return "error/error";
    }

}
