package ua.com.gup.domain.offer.model;


import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class AddressArea {

    @ApiModelProperty(position = 0, example = "code")
    private String code;

    @ApiModelProperty(position = 10, value = "{\"en\": \"Ukraine\"}")
    private Map<String, String> name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getName() {
        return name;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }
}
