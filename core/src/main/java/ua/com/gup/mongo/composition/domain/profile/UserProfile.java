package ua.com.gup.mongo.composition.domain.profile;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.object.ObjectType;

@Document(collection = ObjectType.USER)
public class UserProfile extends Profile {

    private String manager;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
