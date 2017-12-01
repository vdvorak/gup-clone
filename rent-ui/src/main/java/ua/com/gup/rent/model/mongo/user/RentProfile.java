package ua.com.gup.rent.model.mongo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static ua.com.gup.rent.model.mongo.user.RentProfile.COLLECTION_NAME;

@Document(collection = COLLECTION_NAME)
public class RentProfile {

    public static final String COLLECTION_NAME = "users";//"rent.profile"

    @Id
    private String id;
    private String username;
//    private List<ImageInfo> images;

    public RentProfile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public List<ImageInfo> getImages() {
//        return images;
//    }
//
//    public void setImages(List<ImageInfo> images) {
//        this.images = images;
//    }
}
