package ua.com.itproekt.gup.dao.news.newsfeed;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.newsfeed.BlogNewsFeed;
import ua.com.itproekt.gup.model.news.newsfeed.BlogNewsFeedItem;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogNewsFeedRepositoryImpl implements BlogNewsFeedRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(BlogNewsFeed.class)) {
            mongoTemplate.createCollection(BlogNewsFeed.class);
        }

        if (!mongoTemplate.collectionExists(BlogSubscription.class)) {
            mongoTemplate.createCollection(BlogSubscription.class);
        }
    }

    @Override
    public void createBlogNewsFeed(BlogNewsFeed blogNewsFeed) {
        mongoTemplate.insert(blogNewsFeed);
    }

    @Override
    public void createBlogSubscription(BlogSubscription blogSubscription) {
        mongoTemplate.insert(blogSubscription);
    }

    @Override
    public BlogSubscription findBlogSubscription(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, BlogSubscription.class);
    }

    @Override
    public BlogSubscription findBlogSubscriptionByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, BlogSubscription.class);
    }

    @Override
    public BlogNewsFeed findBlogNewsFeed(String blogNewsFeedId, int skipNewsFeedItem, int limitNewsFeedItem) {
        Query query = new Query(Criteria.where("id").is(blogNewsFeedId));
        query.fields().slice("blogNewsFeedItems", skipNewsFeedItem * limitNewsFeedItem, limitNewsFeedItem);
        return mongoTemplate.findOne(query, BlogNewsFeed.class);
    }

    @Override
    public BlogNewsFeed findBlogNewsFeedByUserId(String userId, int skipNewsFeedItem, int limitNewsFeedItem) {
        Query query = new Query(Criteria.where("userId").is(userId));
        query.fields().slice("blogNewsFeedItems", skipNewsFeedItem * limitNewsFeedItem, limitNewsFeedItem);
        return mongoTemplate.findOne(query, BlogNewsFeed.class);
    }

    @Override
    public boolean blogNewsFeedExistsForUser(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.exists(query, BlogNewsFeed.class);
    }

    @Override
    public boolean blogSubscriptionExistsForUser(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.exists(query, BlogSubscription.class);
    }

    @Override
    public void addSubscription(String userId, String blogId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("userId").is(userId)),
                new Update().push("subscriptions", blogId),
                BlogSubscription.class);
    }

    @Override
    public int deleteSubscription(String userId, String blogId) {
        WriteResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("userId").is(userId)),
                new Update().pull("subscriptions", blogId),
                BlogSubscription.class);

        return result.getN();
    }

    @Override
    public boolean subscriptionExist(String userId, String blogId) {
        Query query = new Query()
            .addCriteria(Criteria.where("userId").is(userId))
            .addCriteria(Criteria.where("subscriptions").in(blogId));
        return mongoTemplate.exists(query, BlogSubscription.class);
    }

    @Override
    public void updateBlogNewsFeed(String userId) {
        BlogSubscription blogSubscription = findBlogSubscriptionByUserId(userId);
        Query lastUpdateNewsFeedQuery = new Query(Criteria.where("userId").is(userId));
        lastUpdateNewsFeedQuery.fields().include("lastUpdate");
        long lastUpdate = mongoTemplate.findOne(lastUpdateNewsFeedQuery, BlogNewsFeed.class).getLastUpdate();

        List<BlogNewsFeedItem> blogNewsFeedItems = getNewBlogNewsFeedItems(blogSubscription, lastUpdate);
        addNewFeedsToBlogNewsFeed(userId, blogNewsFeedItems);
    }

    public void addNewFeedsToBlogNewsFeed(String userId, List<BlogNewsFeedItem> blogNewsFeedItems) {
        Query blogNewsFeedQuery = new Query()
            .addCriteria(Criteria.where("userId").is(userId));

        Update newsFeedUpdate = new Update()
            .pushAll("blogNewsFeedItems", blogNewsFeedItems.toArray())
            .inc("blogNewsFeedItemsQuantity", blogNewsFeedItems.size())
            .set("lastUpdate", LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());

        mongoTemplate.updateFirst(
                blogNewsFeedQuery,
                newsFeedUpdate,
                BlogNewsFeed.class);
    }

    public List<BlogNewsFeedItem> getNewBlogNewsFeedItems(BlogSubscription blogSubscription, long lastUpdate) {
        Query blogPostQuery = new Query()
            .addCriteria(Criteria.where("blogId").in(blogSubscription.getSubscriptions()))
            .addCriteria(Criteria.where("createdDate").gte(lastUpdate));
        blogPostQuery.fields().include("id");
        blogPostQuery.fields().include("createdDate");
        List<BlogPost> blogPosts = mongoTemplate.find(blogPostQuery, BlogPost.class);

        List<BlogNewsFeedItem> blogNewsFeedItems = new ArrayList<>();

        blogPosts.forEach(blogPost -> {
            blogNewsFeedItems.add(new BlogNewsFeedItem(blogPost));
        });

        return blogNewsFeedItems;
    }

}
