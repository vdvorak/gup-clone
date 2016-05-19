package ua.com.itproekt.gup.dao.news;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.news.Comment;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class BlogPostRepositoryImpl implements BlogPostRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(BlogPost.class)) {
            mongoTemplate.createCollection(BlogPost.class);
        }
    }

    @Override
    public BlogPost findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, BlogPost.class);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        String searchFieldRegex = "(?i:.*" + name + ".*)";

        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("title").regex(searchFieldRegex),
                Criteria.where("text").regex(searchFieldRegex)));

        query.fields().include("title");
        query.skip(0);
        query.limit(10);

        // ===============================
        /// distinct values!
        // ===============================


        return mongoTemplate.find(query, BlogPost.class).stream().map(BlogPost::getTitle).collect(Collectors.toSet());
    }

    @Override
    public EntityPage<BlogPost> findBlogPostsWihOptions(BlogPostFilterOptions blogPostFO) {
        Query query = new Query();

        if (blogPostFO.getBlogId() != null) {
            query.addCriteria(Criteria.where("blogId").is(blogPostFO.getBlogId()));
        }

        if (blogPostFO.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").is(blogPostFO.getAuthorId()));
        }

        if (blogPostFO.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + blogPostFO.getSearchField() + ".*)";
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("title").regex(searchFieldRegex),
                    Criteria.where("text").regex(searchFieldRegex)));
        }

        if (blogPostFO.getCategories() != null) {
            query.addCriteria(Criteria.where("categories").all(blogPostFO.getCategories()));
        }

//        if (blogPostFO.getTags() != null) {
//            query.addCriteria(Criteria.where("tags").all(blogPostFO.getTags()));
//        }

        if (blogPostFO.getAddress() != null) {
            if (blogPostFO.getAddress().getCountry() != null) {
                query.addCriteria(Criteria.where("address.country").is(blogPostFO.getAddress().getCountry()));
            }

            if (blogPostFO.getAddress().getArea() != null) {
                query.addCriteria(Criteria.where("address.area").is(blogPostFO.getAddress().getArea()));
            }

            if (blogPostFO.getAddress().getCity() != null) {
                query.addCriteria(Criteria.where("address.city").is(blogPostFO.getAddress().getCity()));
            }
        }

        if (blogPostFO.getAfterDate() != null) {
            query.addCriteria(Criteria.where("createdDate").gte(blogPostFO.getAfterDate()));
        }

        if (blogPostFO.getCreatedDateSortDirection() != null) {
            query.with(new Sort(blogPostFO.getCreatedDateSortDirection(), "createdDate"));
        }

        if (blogPostFO.getViewsSortDirection() != null) {
            query.with(new Sort(blogPostFO.getViewsSortDirection(), "views"));
        }

        if (blogPostFO.getTotalCommentsSortDirection() != null) {
            query.with(new Sort(blogPostFO.getTotalCommentsSortDirection(), "totalComments"));
        }

        if (blogPostFO.getTotalLikesSortDirection() != null) {
            query.with(new Sort(blogPostFO.getTotalLikesSortDirection(), "totalLikes"));
        }

        query.skip(blogPostFO.getSkip());
        query.limit(blogPostFO.getLimit());

        return new EntityPage<>(mongoTemplate.count(query, BlogPost.class),
                mongoTemplate.find(query, BlogPost.class));
    }

    @Override
    public void create(BlogPost post) {
        mongoTemplate.insert(post);
    }

    @Override
    public BlogPost findBlogPostAndUpdate(BlogPost post) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(post);
    }

    @Override
    public int delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, BlogPost.class);
        return result.getN();
    }

    @Override
    public BlogPost findBySeoKey(String seoKey) {
        Query query = new Query(Criteria.where("seoKey").is(seoKey));
        return mongoTemplate.findOne(query, BlogPost.class);
    }

    @Override
    public boolean blogPostExists(String blogPostId) {
        Query query = new Query(Criteria.where("id").is(blogPostId));
        return mongoTemplate.exists(query, BlogPost.class);
    }

    @Override
    public boolean commentExists(String blogPostId, String commentId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(blogPostId))
                .addCriteria(Criteria.where("comments.cId").is(commentId));
        return mongoTemplate.exists(query, BlogPost.class);
    }

    @Override
    public void likeComment(String blogPostId, String commentId, String userId) {
        Query addLikeQuery = new Query()
                .addCriteria(Criteria.where("id").is(blogPostId))
                .addCriteria(Criteria.where("comments.cId").is(commentId));

        Query existsQuery = new Query()
                .addCriteria(Criteria.where("id").is(blogPostId))
                .addCriteria(Criteria.where("comments").elemMatch(new Criteria().andOperator(
                        Criteria.where("cId").is(commentId),
                        Criteria.where("likedIds").in(userId))));

        Update update = new Update();
        if (mongoTemplate.exists(existsQuery, BlogPost.class)) {
            update.pull("comments.$.likedIds", userId).inc("comments.$.totalLikes", -1);
        } else {
            update.push("comments.$.likedIds", userId).inc("comments.$.totalLikes", 1);
        }

        mongoTemplate.updateFirst(addLikeQuery, update, BlogPost.class);
    }

    @Override
    public void createComment(String blogPostId, Comment comment) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(blogPostId)),
                new Update().push("comments", comment).inc("totalComments", 1),
                BlogPost.class);
    }

    @Override
    public int deleteComment(String blogPostId, String commentId) {
        WriteResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(blogPostId)),
                new Update().pull("comments", Query.query(Criteria.where("cId").is(commentId))).inc("totalComments", -1),
                BlogPost.class);

        return result.getN();
    }

    @Override
    public Comment findComment(String blogPostId, String commentId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(blogPostId))
                .addCriteria(Criteria.where("comments.cId").is(commentId));
        query.fields().slice("comments", 1);

        return mongoTemplate.findOne(query, BlogPost.class).getComments().iterator().next();
    }

    @Override
    public void likeBlogPost(String blogPostId, String userId) {
        if (checkIfUserHasVoted(blogPostId, userId, "dislikedIds")) {
            mongoTemplate.updateFirst(
                    Query.query(Criteria.where("id").is(blogPostId)),
                    new Update().pull("dislikedIds", userId).inc("totalDislikes", -1),
                    BlogPost.class);
        }
        if (!checkIfUserHasVoted(blogPostId, userId, "likedIds")) {
            mongoTemplate.updateFirst(
                    Query.query(Criteria.where("id").is(blogPostId)),
                    new Update().push("likedIds", userId).inc("totalLikes", 1),
                    BlogPost.class);
        }
    }

    @Override
    public void dislikeBlogPost(String blogPostId, String userId) {
        if (checkIfUserHasVoted(blogPostId, userId, "likedIds")) {
            mongoTemplate.updateFirst(
                    Query.query(Criteria.where("id").is(blogPostId)),
                    new Update().pull("likedIds", userId).inc("totalLikes", -1),
                    BlogPost.class);
        }
        if (!checkIfUserHasVoted(blogPostId, userId, "dislikedIds")) {
            mongoTemplate.updateFirst(
                    Query.query(Criteria.where("id").is(blogPostId)),
                    new Update().push("dislikedIds", userId).inc("totalDislikes", 1),
                    BlogPost.class);
        }
    }

    @Override
    public void incViewsAtOne(String blogPostId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(blogPostId)),
                new Update().inc("views", 1),
                BlogPost.class);
    }

    public boolean checkIfUserHasVoted(String blogPostId, String userId, String votesArrayName) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(blogPostId))
                .addCriteria(Criteria.where(votesArrayName).in(userId));
        return mongoTemplate.exists(query, BlogPost.class);
    }
}
