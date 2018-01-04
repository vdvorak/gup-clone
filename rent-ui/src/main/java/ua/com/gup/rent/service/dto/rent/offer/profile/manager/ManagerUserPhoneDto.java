package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import lombok.Data;

@Data
public class ManagerUserPhoneDto {
    public String number;
    public Boolean relevance;

    public ManagerUserPhoneDto() {
    }

    public ManagerUserPhoneDto(String number, Boolean relevance) {
        this.number = number;
        this.relevance = relevance;
    }
}
