package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.InterestingStatus;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;

@Data
public class ManagerContactInfoEditDto {
    private ManagerUserPhoneDto contactPhone;
    private String skypeUserName;
    private String viberUserName;
    private String realAddress;
    private String additionalInfo;
    private InterestingStatus interestingStatus;
}
