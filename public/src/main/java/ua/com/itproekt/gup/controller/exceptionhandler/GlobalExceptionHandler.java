package ua.com.itproekt.gup.controller.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(HttpServletRequest request, Principal principal,
                                              AccessDeniedException ex) {
        logException(request, principal, ex);

        return "error/403";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "redirect:404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException() {
        return "redirect:404";
    }

    @ExceptionHandler(Exception.class)
    public String handleUncaughtException() {
        return "redirect:500";
    }

}
