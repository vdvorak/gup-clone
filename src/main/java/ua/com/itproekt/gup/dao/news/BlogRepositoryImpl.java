package ua.com.itproekt.gup.dao.news;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;

@Repository
public class BlogRepositoryImpl implements BlogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Blog.class)) {
            mongoTemplate.createCollection(Blog.class);
        }
    }

    @Override
    public void createBlog(Blog blog) {
        mongoTemplate.insert(blog);
    }

    @Override
    public Blog findBlog(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Blog.class);
    }

    @Override
    public Blog findBySeoKey(String seoKey) {
        Query query = new Query(Criteria.where("seoKey").is(seoKey));
        return mongoTemplate.findOne(query, Blog.class);
    }

    @Override
    public Blog findBlogAndUpdate(Blog blog) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(blog);
    }

    @Override
    public EntityPage<Blog> findBlogWihOptions(BlogFilterOptions blogFO) {
        Query query = new Query();

        if (blogFO.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").is(blogFO.getAuthorId()));
        }

        if (blogFO.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + blogFO.getSearchField() + ".*)";
            query.addCriteria(Criteria.where("title").regex(searchFieldRegex));
        }

        if (blogFO.getCategories() != null) {
            query.addCriteria(Criteria.where("categories").all(blogFO.getCategories()));
        }

        query.skip(blogFO.getSkip());
        query.limit(blogFO.getLimit());

        return new EntityPage<>(mongoTemplate.count(query, Blog.class),
                mongoTemplate.find(query, Blog.class));
    }

    @Override
    public int deleteBlog(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Blog.class);
        return result.getN();
    }

    @Override
    public boolean blogExists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Blog.class);
    }
}
