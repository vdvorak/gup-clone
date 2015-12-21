package ua.com.itproekt.gup.dao.news.newsfeed;

import ua.com.itproekt.gup.model.news.newsfeed.BlogNewsFeed;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;

public interface BlogNewsFeedRepository {

     void createBlogNewsFeed(BlogNewsFeed blogNewsFeed);
     void createBlogSubscription(BlogSubscription blogSubscription);

     BlogSubscription findBlogSubscription(String id);
     BlogNewsFeed findBlogNewsFeed(String id, int skipNewsFeedItem, int limitNewsFeedItem);
     BlogSubscription findBlogSubscriptionByUserId(String userId);
     BlogNewsFeed findBlogNewsFeedByUserId(String userId, int skipNewsFeedItem, int limitNewsFeedItem);

     void updateBlogNewsFeed(String userId);

     boolean blogNewsFeedExistsForUser(String userId);

     boolean blogSubscriptionExistsForUser(String userId);

     void addSubscription(String userId, String blogId);

     int deleteSubscription(String userId, String blogId);

     boolean subscriptionExist(String userId, String blogId);
}
