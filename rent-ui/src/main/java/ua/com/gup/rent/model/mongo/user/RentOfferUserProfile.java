package ua.com.gup.rent.model.mongo.user;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class RentOfferUserProfile extends RentOfferProfile {

    @Field("rentManager")
    private String manager;

    @Field("rentManagerInfo")
    private RentManagerUserInfo managerInfo;
}
