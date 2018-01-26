package ua.com.gup.repository.email;

import ua.com.gup.mongo.composition.domain.email.EmailMessage;
import ua.com.gup.mongo.model.offer.EmailStatus;

import java.util.List;

public interface EmailRepository {


    void createMessage(EmailMessage message);

    void removeMessage(EmailMessage message);

    EmailMessage findAndModifyMessage(EmailStatus oldStatus, EmailStatus newStatus);

    EmailMessage updateStatusAndLastAttemptTimestamp(EmailMessage message);

    void save(EmailMessage message);
}
