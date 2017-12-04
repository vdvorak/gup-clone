package ua.com.gup.repository.email;

import ua.com.gup.mongo.composition.domain.email.EmailMessage;

public interface EmailRepository {


    void createMessage(EmailMessage message);

    EmailMessage findOneMessage();
    
    EmailMessage findOneMessageInQueue();

    void removeMessage(EmailMessage message);

    EmailMessage updateLastAttemptTimestamp(EmailMessage message);
    
    void save(EmailMessage message);
}
