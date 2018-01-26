package ua.com.gup.listener.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.com.gup.mongo.composition.domain.email.EmailMessage;
import ua.com.gup.mongo.model.offer.EmailStatus;
import ua.com.gup.service.EmailService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Profile("pre-prod")
public class SendMailTask {

    private static Logger LOG = LoggerFactory.getLogger(SendMailTask.class);

    @Autowired
    private EmailService emailService;

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(fixedDelay = 1000)
    public void printMessages() {
        List<EmailMessage> messages = emailService.findAndModifyMessages(EmailStatus.QUEUE, EmailStatus.PROCESSING, 1);

        for (EmailMessage message : messages) {
            try {
                emailService.sendEmail(message);
            } catch (Exception e) {
                LOG.info("Sending mail exception", e);
                emailService.updateStatusAndLastAttemptTimestamp(message);
            }
        }

    }
}
