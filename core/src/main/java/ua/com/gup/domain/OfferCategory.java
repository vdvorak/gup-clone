package ua.com.gup.domain;

import io.swagger.annotations.ApiModelProperty;

public class OfferCategory {

    @ApiModelProperty(example = "category_code")
    private String code;

    @ApiModelProperty(example = "category_name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
