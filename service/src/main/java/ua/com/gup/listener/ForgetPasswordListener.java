package ua.com.gup.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.email.EmailType;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.service.emailnotification.EmailService;
import ua.com.gup.event.OnForgetPasswordEvent;

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
