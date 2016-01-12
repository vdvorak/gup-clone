package ua.com.itproekt.gup.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.dao.news.BlogRepository;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.ServiceNames;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public void createBlog(Blog blog) {
        blogRepository.createBlog(blog);
    }

    @Override
    public Blog findBlog(String id) {
        return blogRepository.findBlog(id);
    }

    @Override
    public Blog findBlogAndUpdate(Blog blog) {
        storageRepository.delete(ServiceNames.NEWS.toString(), blog.getId());
        return blogRepository.findBlogAndUpdate(blog);
    }

    @Override
    public int deleteBlog(String id) {
        return blogRepository.deleteBlog(id);
    }

    @Override
    public boolean blogExists(String id) {
        return blogRepository.blogExists(id);
    }

    @Override
    public EntityPage<Blog> findBlogWihOptions(BlogFilterOptions blogFO) {
        return blogRepository.findBlogWihOptions(blogFO);
    }
}
