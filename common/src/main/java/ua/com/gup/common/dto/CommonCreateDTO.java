package ua.com.gup.common.dto;

import lombok.Getter;

@Getter
public class CommonCreateDTO {
    private String id;

    public CommonCreateDTO(String id) {
        this.id = id;
    }
}
