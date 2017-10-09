package ua.com.gup.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.profile.Profile;
import ua.com.gup.event.OnInitialRegistrationByEmailEvent;
import ua.com.gup.model.enumeration.EmailType;
import ua.com.gup.service.emailnotification.EmailService;

@Component
public class InitialRegistrationByEmailListener implements ApplicationListener<OnInitialRegistrationByEmailEvent> {

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnInitialRegistrationByEmailEvent onInitialRegistrationByEmailEvent) {
        prepareEmailMessage((Profile) onInitialRegistrationByEmailEvent.getSource());
    }

    private void prepareEmailMessage(Profile profile) {
        emailService.prepareMessageAndAddToQueue(profile, EmailType.EMAIL_REGISTRATION);
    }
}
