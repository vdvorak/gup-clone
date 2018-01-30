package ua.com.gup.rent.model.mongo.user;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class UserProfile extends Profile {

    @Field("rentManager")
    private String manager;

    @Field("rentManagerInfo")
    private ExtendManagerUserInfo managerInfo;
}
