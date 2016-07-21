package ua.com.itproekt.gup.model.order;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OrderComment {
    String userId;
    String message;
    Long date;

    public String getUserId() {
        return userId;
    }

    public OrderComment setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public OrderComment setMessage(String message) {
        this.message = message;
        this.date = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Long getDate() {
        return date;
    }

    public OrderComment setDate(Long date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "OrderComment{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
