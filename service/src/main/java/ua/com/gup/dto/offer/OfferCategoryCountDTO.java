package ua.com.gup.dto.offer;


import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

public class OfferCategoryCountDTO {

    @ApiModelProperty(example = "category_code")
    private int code;

    private String key;

    @ApiModelProperty(example = "category_title")
    private Map<String, String> title = new HashMap<>();

    private int count;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
