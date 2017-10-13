package ua.com.gup.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ua.com.gup.event.OnForgetPasswordEvent;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.enumeration.EmailType;
import ua.com.gup.service.emailnotification.EmailService;

@Component
public class ForgetPasswordListener implements ApplicationListener<OnForgetPasswordEvent> {

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnForgetPasswordEvent forgetPasswordEvent) {
        prepareEmailMessage((Profile) forgetPasswordEvent.getSource());
    }

    private void prepareEmailMessage(Profile profile) {
        emailService.prepareMessageAndAddToQueue(profile, EmailType.EMAIL_FORGET_PASSWORD);
    }
}
