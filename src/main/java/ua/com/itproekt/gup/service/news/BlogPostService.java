package ua.com.itproekt.gup.service.news;

import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.news.Comment;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;

public interface BlogPostService {
    //Posts
    EntityPage<BlogPost> findBlogPostsWihOptions(BlogPostFilterOptions blogPostFilterOptions);

    BlogPost findById(String id);

    void create(BlogPost post);

    BlogPost update(BlogPost post);

    int delete(String id);

    boolean blogPostExists(String id);

    //PostComments
    void likeComment(String blogPostId, String commentId, String userId);

    void createComment(String blogPostId, Comment comment);

    int deleteComment(String blogPostId, String commentId);

    BlogPost findComment(String blogPostId, String commentId);

    void likeBlogPost(String blogPostId, String userId);

    void dislikeBlogPost(String blogPostId, String userId);

    boolean commentExists(String blogPostId, String commentId);

    BlogPost findBlogPostAndIncViews(String blogPostId);

    BlogPost edit(BlogPost blogPost);

    List<String> getMatchedNames(String name);
}
