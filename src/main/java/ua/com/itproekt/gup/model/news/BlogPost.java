package ua.com.itproekt.gup.model.news;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;

@Document
public class BlogPost {
    @Id
    private String id;
    private String authorId;
    private String blogId;
    private Integer views;
    private Long modifiedDate;
    private Long createdDate;
    private Integer totalComments;
    private Integer totalLikes;
    private Integer totalDislikes;

    private Set<Comment> comments;
    private Set<String> likedIds;
    private Set<String> dislikedIds;

    //user can edit
    @Size(min = 5, max = 70)
    private String title;
    @Size(min = 50, max = 50_000)
    private String text;
    private Address address;
    private Map<String, String> imagesIds;
    private Set<String> tags;
    private Set<String> categories;

    public BlogPost() {
    }

    public BlogPost setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public BlogPost setModifiedDateEqualsToCurrentDate() {
        this.modifiedDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }
    //------------------------------------------------------------------------


    public Address getAddress() {
        return address;
    }

    public BlogPost setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getId() {
        return id;
    }

    public BlogPost setId(String id) {
        this.id = id;
        return this;
    }

    public Map<String, String> getImagesIds() {
        return imagesIds;
    }

    public BlogPost setImagesIds(Map<String, String> imagesIds) {
        this.imagesIds = imagesIds;
        return this;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public BlogPost setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public Set<String> getTags() {
        return tags;
    }

    public BlogPost setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public String getText() {
        return text;
    }

    public BlogPost setText(String text) {
        this.text = text;
        return this;
    }

    public Set<String> getDislikedIds() {
        return dislikedIds;
    }

    public BlogPost setDislikedIds(Set<String> dislikedIds) {
        this.dislikedIds = dislikedIds;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BlogPost setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<String> getLikedIds() {
        return likedIds;
    }

    public BlogPost setLikedIds(Set<String> likedIds) {
        this.likedIds = likedIds;
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public BlogPost setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Integer getTotalDislikes() {
        return totalDislikes;
    }

    public BlogPost setTotalDislikes(Integer totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public BlogPost setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public BlogPost setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public BlogPost setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public BlogPost setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public BlogPost setViews(Integer views) {
        this.views = views;
        return this;
    }

    public String getBlogId() {
        return blogId;
    }

    public BlogPost setBlogId(String blogId) {
        this.blogId = blogId;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public BlogPost setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }
}
