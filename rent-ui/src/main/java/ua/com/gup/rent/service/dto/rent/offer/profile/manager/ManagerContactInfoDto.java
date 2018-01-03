package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;

@Data
public class ManagerContactInfoDto {
    public String skypeUserName;
    public String viberUserName;
    public String realAddress;
    public ManagerUserPhoneDto contactPhone;

    public ManagerContactInfoDto(ContactInfo contactInfo) {
        this.skypeUserName = contactInfo.skypeUserName;
        this.viberUserName = contactInfo.viberUserName;
        this.realAddress = contactInfo.realAddress;
        if (contactInfo.getContactPhone() != null) {
            RelevancePhone phone = contactInfo.getContactPhone();
            this.contactPhone = new ManagerUserPhoneDto(
                    phone.number,
                    phone.relevance);
        }
    }
}
