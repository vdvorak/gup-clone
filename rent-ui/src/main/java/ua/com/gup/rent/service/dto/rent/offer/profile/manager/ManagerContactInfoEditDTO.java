package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.InterestingStatus;

@Data
public class ManagerContactInfoEditDTO {
    private ManagerUserPhoneDTO contactPhone;
    private String skypeUserName;
    private String viberUserName;
    private String realAddress;
    private String additionalInfo;
    private InterestingStatus interestingStatus;
}
