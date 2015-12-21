package ua.com.itproekt.gup.model.news.newsfeed;

import ua.com.itproekt.gup.model.news.BlogPost;

public class BlogNewsFeedItem {
    private String blogPostId;
    private Long createdDate;
    // images ids ....

    public BlogNewsFeedItem() {
    }

    public BlogNewsFeedItem(BlogPost blogPost) {
        this.blogPostId = blogPost.getId();
        this.createdDate = blogPost.getCreatedDate();
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(String blogPostId) {
        this.blogPostId = blogPostId;
    }
}
