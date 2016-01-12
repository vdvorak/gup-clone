package ua.com.itproekt.gup.service.news;

import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface BlogService {
    void createBlog(Blog blog);

    Blog findBlog(String blogId);

    Blog findBlogAndUpdate(Blog blog);

    int deleteBlog(String blogId);

    boolean blogExists(String blogId);

    EntityPage<Blog> findBlogWihOptions(BlogFilterOptions blogFO);
}
