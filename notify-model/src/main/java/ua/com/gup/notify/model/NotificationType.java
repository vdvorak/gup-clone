package ua.com.gup.notify.model;

import java.io.Serializable;

public enum NotificationType implements Serializable{

    USUAL(1),
    OFFER(2),
    DEAL(4);

    private int type;

    NotificationType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
