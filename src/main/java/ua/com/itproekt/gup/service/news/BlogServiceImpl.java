package ua.com.itproekt.gup.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.dao.news.BlogRepository;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.ServiceNames;

import java.util.HashMap;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public void createBlog(Blog blog) {
        Map<String, String> editorIds = new HashMap<>();
        editorIds.put("author", blog.getAuthorId());
        if (blog.getEditorsIds() != null) {
            editorIds.putAll(blog.getEditorsIds());
        }

        Blog newBlog = new Blog()
                .setCreatedDateEqualsToCurrentDate()
                .setAuthorId(blog.getAuthorId())
                .setTitle(blog.getTitle())
                .setDescription(blog.getDescription())
                .setImageId(blog.getImageId())
                .setCategories(blog.getCategories())
                .setEditorsIds(editorIds)
                .setSocLinks(blog.getSocLinks());

        blogRepository.createBlog(newBlog);

        blog.setId(newBlog.getId());
    }

    @Override
    public Blog findBlog(String id) {
        return blogRepository.findBlog(id);
    }

    @Override
    public Blog findBlogAndUpdate(Blog blog) {
        Blog newBlog = new Blog()
                .setTitle(blog.getTitle())
                .setDescription(blog.getDescription())
                .setImageId(blog.getImageId())
                .setCategories(blog.getCategories())
                .setEditorsIds(blog.getEditorsIds()) // ?? add list
                .setSocLinks(blog.getSocLinks());

        return blogRepository.findBlogAndUpdate(newBlog);
    }

    @Override
    public int deleteBlog(String id) {
        storageRepository.delete(ServiceNames.NEWS.toString(), blogRepository.findBlog(id).getImageId());
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
