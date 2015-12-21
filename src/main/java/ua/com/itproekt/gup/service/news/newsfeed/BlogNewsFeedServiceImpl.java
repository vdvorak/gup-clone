package ua.com.itproekt.gup.service.news.newsfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.news.newsfeed.BlogNewsFeedRepository;
import ua.com.itproekt.gup.model.news.newsfeed.BlogNewsFeed;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;

@Service
public class BlogNewsFeedServiceImpl implements BlogNewsFeedService {

    @Autowired
    BlogNewsFeedRepository blogNewsFeedRepository;

    @Override
    public void createBlogNewsFeed(BlogNewsFeed blogNewsFeed) {
        blogNewsFeedRepository.createBlogNewsFeed(blogNewsFeed);
    }

    @Override
    public void createBlogSubscription(BlogSubscription blogSubscription) {
        blogNewsFeedRepository.createBlogSubscription(blogSubscription);
    }

    @Override
    public BlogSubscription findBlogSubscriptionByUserId(String userId) {
        return blogNewsFeedRepository.findBlogSubscriptionByUserId(userId);
    }

    @Override
    public BlogNewsFeed findBlogNewsFeedByUserId(String userId, int skipNewsFeedItem, int limitNewsFeedItem) {
        return blogNewsFeedRepository.findBlogNewsFeedByUserId(userId, skipNewsFeedItem, limitNewsFeedItem);
    }

    @Override
    public void updateBlogNewsFeed(String userId) {
        blogNewsFeedRepository.updateBlogNewsFeed(userId);
    }

    @Override
    public boolean blogNewsFeedExistsForUser(String userId) {
        return blogNewsFeedRepository.blogNewsFeedExistsForUser(userId);
    }

    @Override
    public boolean blogSubscriptionExistsForUser(String userId) {
        return blogNewsFeedRepository.blogSubscriptionExistsForUser(userId);
    }

    @Override
    public void addSubscription(String userId, String blogId) {
        blogNewsFeedRepository.addSubscription(userId, blogId);
    }

    @Override
    public int deleteSubscription(String userId, String blogId) {
        return blogNewsFeedRepository.deleteSubscription(userId, blogId);
    }

    @Override
    public boolean subscriptionExist(String userId, String blogId) {
        return blogNewsFeedRepository.subscriptionExist(userId, blogId);
    }
}
