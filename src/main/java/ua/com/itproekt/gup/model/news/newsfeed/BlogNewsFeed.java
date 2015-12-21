package ua.com.itproekt.gup.model.news.newsfeed;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Document(collection = "blogNewsFeed")
public class BlogNewsFeed {
    @Id
    private String id;
    private String userId;
    private Long blogNewsFeedItemsQuantity;
    private Long createdDate;
    private Long lastUpdate;
    private Set<BlogNewsFeedItem> blogNewsFeedItems;

    public BlogNewsFeed() {
        this.blogNewsFeedItemsQuantity = 0L;
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.lastUpdate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getBlogNewsFeedItemsQuantity() {
        return blogNewsFeedItemsQuantity;
    }

    public void setBlogNewsFeedItemsQuantity(Long blogNewsFeedItemsQuantity) {
        this.blogNewsFeedItemsQuantity = blogNewsFeedItemsQuantity;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<BlogNewsFeedItem> getBlogNewsFeedItems() {
        return blogNewsFeedItems;
    }

    public void setBlogNewsFeedItems(Set<BlogNewsFeedItem> blogNewsFeedItems) {
        this.blogNewsFeedItems = blogNewsFeedItems;
    }
}
