package ua.com.itproekt.gup.dao.news;

import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.news.Comment;
import ua.com.itproekt.gup.util.EntityPage;


public interface BlogPostRepository {

    //Posts
    EntityPage<BlogPost> findBlogPostsWihOptions(BlogPostFilterOptions blogPostFilterOptions);

    BlogPost findById(String blogPostId);

    void create(BlogPost blogPost);

    BlogPost findBlogPostAndUpdate(BlogPost blogPost);

    int delete(String blogPostId);

    boolean blogPostExists(String id);

    boolean commentExists(String blogPostId, String commentId);


    //PostComments
    void createComment(String blogPostId, Comment comment);

    int deleteComment(String blogPostId, String commentId);

    void likeComment(String blogPostId, String commentId, String userId);

    BlogPost findComment(String blogPostId, String commentId);

    void likeBlogPost(String blogPostId, String userId);

    void dislikeBlogPost(String blogPostId, String userId);

    void incViewsAtOne(String blogPostId);

}
