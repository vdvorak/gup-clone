package ua.com.gup.rent.model.mongo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static ua.com.gup.rent.model.mongo.user.RentOfferProfile.COLLECTION_NAME;

@Document(collection = COLLECTION_NAME)
public class RentOfferProfile {

    public static final String COLLECTION_NAME = "users"; //"rent.users"

    @Id
    private String id;
    private String username;

    public RentOfferProfile() {
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
}
