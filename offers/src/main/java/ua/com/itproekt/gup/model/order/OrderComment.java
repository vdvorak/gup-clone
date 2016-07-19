package ua.com.itproekt.gup.model.order;


import java.util.Date;

public class OrderComment {
    String userId;
    String message;
    Date date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
