package ua.com.gup.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

public class OfferCategory {

    @ApiModelProperty(example = "category_code")
    private int code;

    @ApiModelProperty(example = "category_title")
    private Map<String, String> title = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }
}
