package ua.com.itproekt.gup.service.news;

import ua.com.itproekt.gup.model.news.Blog;

public interface BlogService {
    void createBlog(Blog blog);
    Blog findBlog(String id);
    Blog findBlogAndUpdate(Blog blog);
    int deleteBlog(String id);
    boolean blogExists(String id);
}
