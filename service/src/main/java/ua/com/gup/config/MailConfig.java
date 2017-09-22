package ua.com.gup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:properties/email.properties")
public class MailConfig {

    @Autowired
    private Environment e;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(e.getRequiredProperty("email.services.smtp.host"));
        mailSender.setPort(e.getRequiredProperty("email.services.smtp.port", Integer.class));

        mailSender.setUsername(e.getRequiredProperty("email.services.smtp.username"));
        mailSender.setPassword(e.getRequiredProperty("email.services.smtp.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", e.getRequiredProperty("email.services.smtp.auth"));
        props.put("mail.smtp.starttls.enable", e.getRequiredProperty("email.services.smtp.starttls.enable"));
        props.put("mail.debug", "true");

        return mailSender;
    }

}
