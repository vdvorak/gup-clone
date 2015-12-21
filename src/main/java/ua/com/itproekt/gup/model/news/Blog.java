package ua.com.itproekt.gup.model.news;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;

@Document(collection = "blogs")
public class Blog {
    @Id
    private String id;
    private Long createdDate;
    private String authorId;

    @Size(min = 2, max = 70)
    private String title;
//    @Size(min = 50, max = 5000)
    private String description;
    private String imageId;
    private Map<String, String> editorsIds;
    private Set<String> categories;

    public Blog() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public Blog(String name, String authorId) {
        this();
        this.title = name;
        this.authorId = authorId;
        editorsIds.put( "TEST", authorId);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", name='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId='" + imageId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", editorsIds=" + editorsIds +
                ", tags=" + categories +
                ", createdDate=" + createdDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Map<String, String> getEditorsIds() {
        return editorsIds;
    }

    public void setEditorsIds(Map<String, String> editorsIds) {
        this.editorsIds = editorsIds;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
