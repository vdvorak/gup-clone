package ua.com.gup.notify.model



class Notification implements Serializable {

//    NotificationType type
    int type
    String[] users
    NotificationMessage message

    Notification() {
        users = []
    }

    @Override
    String toString() {
        return "Notification{" +
                "type=" + type +
                ", users=" + users +
                ", message=" + message +
                '}'
    }
}
