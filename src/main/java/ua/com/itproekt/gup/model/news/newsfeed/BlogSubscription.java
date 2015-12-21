package ua.com.itproekt.gup.model.news.newsfeed;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "blogSubscriptions")
public class BlogSubscription {
    @Id
    private String id;
    private String userId;
    private Set<String> subscriptions;

    public BlogSubscription() {
        subscriptions = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<String> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BlogSubscription{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
