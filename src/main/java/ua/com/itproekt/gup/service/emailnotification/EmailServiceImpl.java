package ua.com.itproekt.gup.service.emailnotification;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
/** Send Email Using GMail SMTP
 * Created by Комп2 on 13.10.2015.
 */
@Service
public class EmailServiceImpl implements EmailService {
    public String sendEmail(String to, String subject, String msg) {
        // Recipient's email ID needs to be mentioned.
        // Sender's email ID needs to be mentioned
//        String from = "itprojectinvest@gmail.com";
//        final String username = "itprojectinvest";
//        final String password = "vbrhjcthdjghbdjl";

        String from = "inform@e-otg.com";
        final String username = "inform@e-otg.com";
        final String password = "Kt801Ma5cYuI";

//        Webmail адрес: https://webmail.ukraine.com.ua/
//        username: inform@e-otg.com
//        Пароль: Kt801Ma5cYuI
//        POP3/IMAP сервер: mail.ukraine.com.ua
//        POP3 порт: 110 или 995 (POP3+SSL)
//        IMAP порт: 143 или 993 (IMAP4+SSL)
//        SMTP сервер: mail.ukraine.com.ua
//        SMTP порт: 25 или 2525 или 465 (SMTP+SSL)



        // Assuming you are sending email through relay.jangosmtp.net
//        String host = "smtp.gmail.com";
        String host = "mail.ukraine.com.ua";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.port", "2525");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(msg);
            // Send message
            Transport.send(message);

            return "Sent message successfully....";

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
//            return "Message sending failure....";
        }
    }

    //test
    public static void main(String[] args) {
//        String to = "fairy.freyja@gmail.com";//change accordingly
//        String to = "serg@e-otg.com";//change accordingly
//        String to = "van.danilov@gmail.com";//change accordingly
//        String to = "minchuck.sasha@gmail.com";//change accordingly
        EmailServiceImpl ems = new EmailServiceImpl();
        String to = "oleksii.holyk@outlook.com";//change accordingly
        String subject = "this is subject";
        String msg = "Hi, this is msg, Проверка кириллицы, Пыщь-пыщь) Фейка молодец, я настроила отправку имела не от гугла!";
        System.out.println(ems.sendEmail(to, subject, msg));
    }
}