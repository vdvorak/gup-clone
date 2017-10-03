package ua.com.gup.executor.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.email.EmailMessage;
import ua.com.gup.service.emailnotification.EmailService;
import ua.com.gup.service.emailnotification.EmailServiceImpl;

@Component
public class SendMailTask {

    private static Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailService emailService;

    @Autowired

    @Scheduled(cron = "* * * * * *")
    public void printMessages() {
        EmailMessage message = emailService.findOneMessage();
        if (message != null) {
            try {
                emailService.sendEmail(message);
                emailService.removeMessage(message);
            } catch (MailException e) {
                LOG.info("Sending mail exception", e);
                emailService.updateLastAttemptTimestamp(message);
            }

        }
    }
}
