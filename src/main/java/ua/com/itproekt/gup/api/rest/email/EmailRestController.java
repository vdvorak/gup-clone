//package ua.com.itproekt.gup.api.rest.email;
//
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import ua.com.itproekt.gup.model.emailnotification.EmailMessage;
//import ua.com.itproekt.gup.service.emailnotification.EmailService;
//import ua.com.itproekt.gup.util.InputValidator;
//
///**
// * Created by Fairy on 23.11.2015.
// */
//@RestController
//@RequestMapping("/api/rest/emailService")
//public class EmailRestController {
//    private static final String SERVICE_NAME = "emailService";
//    private Logger log = Logger.getLogger(SERVICE_NAME);
//    private static final String LOGGED_TITLE = "- EmailRestController - api/rest/emailService/";
//
//    @Autowired
//    EmailService emailService;
//
//    //------------------------------------------ Create -----------------------------------------------------------------
//    @RequestMapping(value = "/email/create/",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity sendEmail(@RequestBody EmailMessage message) {
//
//        if (message.getReceiverEmail() == null || !InputValidator.validateEmail(message.getReceiverEmail())) {
//            log.log(Level.ERROR, SERVICE_NAME + LOGGED_TITLE + "email/create/ - uncorrect receiver email " + message.getReceiverEmail());
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        emailService.sendEmail(message.getReceiverEmail(), message.getSubject(), message.getText());
//        log.log(Level.ERROR, SERVICE_NAME + LOGGED_TITLE + "email/create/ - message for " + message.getReceiverEmail() + "was send successfully");
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}