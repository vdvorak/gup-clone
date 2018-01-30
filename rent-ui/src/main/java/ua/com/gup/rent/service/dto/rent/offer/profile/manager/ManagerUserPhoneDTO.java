package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import lombok.Data;

@Data
public class ManagerUserPhoneDTO {
    public String number;
    public Boolean relevance;

    public ManagerUserPhoneDTO() {
    }

    public ManagerUserPhoneDTO(String number, Boolean relevance) {
        this.number = number;
        this.relevance = relevance;
    }
}
