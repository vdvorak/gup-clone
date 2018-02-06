package ua.com.gup.notify.factory;

import ua.com.gup.notify.model.Notification;
import ua.com.gup.notify.model.NotificationMessage;
import ua.com.gup.notify.model.NotificationType;

public class NotificationFactory {

    private NotificationFactory() {
    }

    /**
     *  all parameters are required
     *
     * @param type
     * @param title
     * @param description
     * @param users - ids list of users
     * @return
     */
    public static Notification createNotification(NotificationType type, String title, String description, String... users) {
        Notification notification = new Notification();
        notification.setType(type.getType());
        notification.setUsers(users);

        NotificationMessage message = new NotificationMessage();
        message.setTitle(title);
        message.setDescription(description);

        notification.setMessage(message);
        return notification;
    }

}
