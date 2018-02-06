package ua.com.gup.notify.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.notify.model.Notification;
import ua.com.gup.notify.service.NotificationService;

import java.io.IOException;

@Component
public class Receiver {

    @Autowired
    private NotificationService notificationService;
    private ObjectMapper mapper = new ObjectMapper();

    public void receiveMessage(byte[] o) {


        Notification readValue = null;
        try {
            readValue = mapper.readValue(o, Notification.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (readValue != null)
            notificationService.sendNotification(readValue);
    }


    public void receiveMessage(String o) {
        System.out.println(o);
    }

}