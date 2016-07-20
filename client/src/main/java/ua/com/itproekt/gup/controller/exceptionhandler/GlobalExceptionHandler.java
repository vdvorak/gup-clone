package ua.com.itproekt.gup.controller.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    //    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(HttpServletRequest request, Principal principal,
                                              AccessDeniedException ex) {
        logException(request, principal, ex);

        return "error/403";
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(HttpServletResponse response) {
//        logException(request, principal, ex);
        System.err.println("#2");
        return "redirect:404";
    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public String handleValidationException(HttpServletRequest request, Principal principal,
//                                            MethodArgumentNotValidException ex) {
//        logException(request, principal, ex);
//
//        return "error/error";
//    }
//

    //    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleUncaughtException(HttpServletRequest request, Principal principal,
                                          Exception ex) {
        System.err.println("#2");
        return "redirect:404";
    }

}
