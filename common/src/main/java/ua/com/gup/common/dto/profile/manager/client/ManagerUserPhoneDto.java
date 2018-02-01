package ua.com.gup.common.dto.profile.manager.client;

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
