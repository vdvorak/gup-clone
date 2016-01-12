package ua.com.itproekt.gup.dao.news;

import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface BlogRepository {
    void createBlog(Blog blog);

    Blog findBlog(String id);

    Blog findBlogAndUpdate(Blog blog);

    int deleteBlog(String id);

    boolean blogExists(String id);

    EntityPage<Blog> findBlogWihOptions(BlogFilterOptions blogPostFilterOptions);
}
