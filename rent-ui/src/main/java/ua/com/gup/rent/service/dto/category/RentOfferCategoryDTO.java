package ua.com.gup.rent.service.dto.category;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RentOfferCategoryDTO implements Serializable {

    @ApiModelProperty(example = "code")
    private int code;
    @ApiModelProperty(example = "key")
    private String key;
    @ApiModelProperty(example = "title")
    private Map<String, String> title = new HashMap<>();


    public RentOfferCategoryDTO() {
    }

    public RentOfferCategoryDTO(RentOfferCategoryDTO rentCategory) {
        this.code = rentCategory.getCode();
        this.key = rentCategory.getKey();
        this.title = rentCategory.getTitle();

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
