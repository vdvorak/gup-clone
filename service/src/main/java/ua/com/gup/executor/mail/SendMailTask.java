package ua.com.gup.executor.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.com.gup.model.email.EmailMessage;
import ua.com.gup.service.emailnotification.EmailService;

@Component
public class SendMailTask {

    @Autowired
    private EmailService emailService;
    @Autowired

    @Scheduled(fixedRate = 5000)
    public void printMessages() {
        EmailMessage message = emailService.findOneMessage();
        if (message != null) {
            emailService.sendEmail(message);
            emailService.removeMessage(message);
        }
    }
}
