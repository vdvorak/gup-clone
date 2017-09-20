package ua.com.gup.domain.offer.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

public class OfferCategory {

    @ApiModelProperty(example = "ode")
    private int code;
    @ApiModelProperty(example = "key")
    private String key;
    @ApiModelProperty(example = "title")
    private Map<String, String> title = new HashMap<>();


    public OfferCategory(int code, String key, Map<String, String> title) {
        this.code = code;
        this.key = key;
        this.title = title;
    }

    public OfferCategory() {

    }

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
}
