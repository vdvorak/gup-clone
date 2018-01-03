package ua.com.gup.common.model.mongo.manager;

import lombok.Data;

@Data
public class ContactInfo {
    public String skypeUserName;
    public String viberUserName;
    public String realAddress;
    public RelevancePhone contactPhone;

    public ContactInfo() {
    }
}
