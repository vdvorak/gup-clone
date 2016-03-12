package ua.com.itproekt.gup.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.dao.news.BlogPostRepository;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.news.Comment;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.ServiceNames;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public EntityPage<BlogPost> findBlogPostsWihOptions(BlogPostFilterOptions blogPostFilterOptions) {
        return blogPostRepository.findBlogPostsWihOptions(blogPostFilterOptions);
    }

    @Override
    public BlogPost findById(String id) {
        return blogPostRepository.findById(id);
    }

    @Override
    public void create(BlogPost blogPost) {
        BlogPost newBlogPost = new BlogPost()
                .setId(null)
                .setAuthorId(blogPost.getAuthorId())
                .setBlogId(blogPost.getBlogId())
                .setViews(0)
                .setTotalComments(0)
                .setComments(new HashSet<>())
                .setTotalLikes(0)
                .setLikedIds(new HashSet<>())
                .setTotalDislikes(0)
                .setDislikedIds(new HashSet<>())
                .setModifiedDateEqualsToCurrentDate()
                .setCreatedDateEqualsToCurrentDate()
                .setTitle(blogPost.getTitle())
                .setText(blogPost.getText())
                .setAddress(blogPost.getAddress())
//                .setTags(blogPost.getTags())
                .setCategories(blogPost.getCategories())
                .setImagesIds(blogPost.getImagesIds());

        blogPostRepository.create(newBlogPost);

        blogPost.setId(newBlogPost.getId());
    }

    @Override
    public BlogPost update(BlogPost post) {
        return blogPostRepository.findBlogPostAndUpdate(post);
    }

    @Override
    public int delete(String id) {
        Map<String, String> imagesIds = findById(id).getImagesIds();
        if (imagesIds != null) {
            storageRepository.delete(ServiceNames.NEWS.toString(), (Set<String>)imagesIds.values());
        }
        return blogPostRepository.delete(id);
    }

    @Override
    public boolean blogPostExists(String id) {
        return blogPostRepository.blogPostExists(id);
    }

    @Override
    public void likeComment(String blogPostId, String commentId, String userId) {
        blogPostRepository.likeComment(blogPostId, commentId, userId);
        String authorId = findComment(blogPostId, commentId).getFromId();
        activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_COMMENT_LIKE, blogPostId, commentId, userId));
    }

    @Override
    public void createComment(String blogPostId, Comment comment) {
        Comment newComment = new Comment()
                .setComment(comment.getComment())
                .setFromId(comment.getFromId())
                .setToId(comment.getToId())
                .setCreatedDateEqualsToCurrentDate();

        blogPostRepository.createComment(blogPostId, newComment);

        String toId = comment.getToId();
        //TODO ** проверять существует ли пользователь с toId
        if( blogPostId.equals(toId)) {
            String authorId = findById(toId).getAuthorId();
            activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_COMMENT, blogPostId, comment.getcId(), SecurityOperations.getLoggedUserId()));
        } else {
            activityFeedService.createEvent(new Event(toId, EventType.BLOG_POST_COMMENT_REPLY, blogPostId, comment.getcId(), SecurityOperations.getLoggedUserId()));
        }
        comment.setcId(newComment.getcId());
    }

    @Override
    public int deleteComment(String blogPostId, String commentId) {
        return blogPostRepository.deleteComment(blogPostId, commentId);
    }

    @Override
    public Comment findComment(String blogPostId, String commentId) {
        return blogPostRepository.findComment(blogPostId, commentId);
    }

    @Override
    public void likeBlogPost(String blogPostId, String userId) {
        blogPostRepository.likeBlogPost(blogPostId, userId);
    }

    @Override
    public void dislikeBlogPost(String blogPostId, String userId) {
        blogPostRepository.dislikeBlogPost(blogPostId, userId);
    }

    @Override
    public boolean commentExists(String blogPostId, String commentId) {
        return blogPostRepository.commentExists(blogPostId, commentId);
    }

    @Override
    public BlogPost findBlogPostAndIncViews(String blogPostId) {
        blogPostRepository.incViewsAtOne(blogPostId);
        return blogPostRepository.findById(blogPostId);
    }

    @Override
    public BlogPost edit(BlogPost blogPost) {
        BlogPost newBlogPost = new BlogPost()
                .setId(blogPost.getId())
                .setTitle(blogPost.getTitle())
                .setText(blogPost.getText())
                .setAddress(blogPost.getAddress())
                .setImagesIds(blogPost.getImagesIds())
//                .setTags(blogPost.getTags())
                .setCategories(blogPost.getCategories())
                .setModifiedDateEqualsToCurrentDate();
        return blogPostRepository.findBlogPostAndUpdate(newBlogPost);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return blogPostRepository.getMatchedNames(name);
    }
}
