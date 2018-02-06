package ua.com.gup.rent.service.amqp;


import ua.com.gup.notify.model.Notification;

public interface RentNotificationService {

    void convertAndSend(Notification notification);
}
