package ua.com.itproekt.gup.dao.news;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.news.Blog;
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
    public Blog findBlogAndUpdate(Blog blog) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(blog);
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
