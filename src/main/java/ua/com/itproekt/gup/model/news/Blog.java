package ua.com.itproekt.gup.model.news;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.util.SocialNetwork;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;

@Document
public class Blog {
    @Id
    private String id;
    private Long createdDate;
    private String authorId;
    @Size(min = 2, max = 70)
    private String title;
    @Size(min = 50, max = 5000)
    private String description;
    private String imageId;
    private Map<SocialNetwork, String> socLinks;
    private Map<String, String> editorsIds;
    private Set<String> categories;
    private String seoUrl; // full SEO url with key - for siteMap
    private String seoKey; // only key - for search in DB

    public void addEditor(String name, String uId) {
        editorsIds.put(name, uId);
    }

    public Blog setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    //===============================================================================

    public Map<SocialNetwork, String> getSocLinks() {
        return socLinks;
    }

    public Blog setSocLinks(Map<SocialNetwork, String> socLinks) {
        this.socLinks = socLinks;
        return this;
    }

    public String getId() {
        return id;
    }

    public Blog setId(String id) {
        this.id = id;
        return this;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public Blog setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Blog setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Blog setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public Blog setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Blog setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public Map<String, String> getEditorsIds() {
        return editorsIds;
    }

    public Blog setEditorsIds(Map<String, String> editorsIds) {
        this.editorsIds = editorsIds;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Blog setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public Blog setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
        return this;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public Blog setSeoKey(String seoKey) {
        this.seoKey = seoKey;
        return this;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", authorId='" + authorId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId='" + imageId + '\'' +
                ", socLinks=" + socLinks +
                ", editorsIds=" + editorsIds +
                ", categories=" + categories +
                ", seoUrl='" + seoUrl + '\'' +
                ", seoKey='" + seoKey + '\'' +
                '}';
    }
}
