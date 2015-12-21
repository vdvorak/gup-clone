package ua.com.itproekt.gup.dao.news;

import ua.com.itproekt.gup.model.news.Blog;

public interface BlogRepository {
    //Blog
    void createBlog(Blog blog);

    Blog findBlog(String id);

    Blog findBlogAndUpdate(Blog blog);

    int deleteBlog(String id);

    boolean blogExists(String id);

//    EntityPage<Blog> findBlogWihOptions(BlogFilterOptions blogPostFilterOptions);
}
