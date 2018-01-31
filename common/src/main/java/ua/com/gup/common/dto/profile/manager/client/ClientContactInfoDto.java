package ua.com.gup.common.dto.profile.manager.client;

import lombok.Data;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;

@Data
public class ClientContactInfoDto {
    public String skypeUserName;
    public String viberUserName;
    public String realAddress;
    public ManagerUserPhoneDto contactPhone;

    public ClientContactInfoDto(ContactInfo contactInfo) {
        if(contactInfo != null) {
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
}
