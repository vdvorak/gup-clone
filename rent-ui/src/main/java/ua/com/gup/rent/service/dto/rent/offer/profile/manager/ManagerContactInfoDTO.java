package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;

@Data
public class ManagerContactInfoDTO {
    public String skypeUserName;
    public String viberUserName;
    public String realAddress;
    public ManagerUserPhoneDTO contactPhone;

    public ManagerContactInfoDTO(ContactInfo contactInfo) {
        this.skypeUserName = contactInfo.skypeUserName;
        this.viberUserName = contactInfo.viberUserName;
        this.realAddress = contactInfo.realAddress;
        if (contactInfo.getContactPhone() != null) {
            RelevancePhone phone = contactInfo.getContactPhone();
            this.contactPhone = new ManagerUserPhoneDTO(
                    phone.number,
                    phone.relevance);
        }
    }
}
