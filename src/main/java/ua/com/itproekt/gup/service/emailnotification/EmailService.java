package ua.com.itproekt.gup.service.emailnotification;

/**
 * Created by Комп2 on 17.11.2015.
 */
public interface EmailService {
    String sendEmail(String to, String subject, String msg);
}
