package ua.com.gup.repository;

import ua.com.gup.model.email.EmailMessage;

public interface EmailRepository {


    void createMessage(EmailMessage message);

    EmailMessage findOneMessage();

    void removeMessage(EmailMessage message);
}
