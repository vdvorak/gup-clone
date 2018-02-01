package ua.com.gup.common.dto.profile.manager.client;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.InterestingStatus;

@Data
public class ManagerContactInfoEditDto {
    private ManagerUserPhoneDto contactPhone;
    private String skypeUserName;
    private String viberUserName;
    private String realAddress;
    private String additionalInfo;
    private InterestingStatus interestingStatus;
}
