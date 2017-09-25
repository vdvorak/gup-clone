package ua.com.gup.model.offer;


import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class AddressArea {

    @ApiModelProperty(position = 0, example = "code")
    private String code;

    @ApiModelProperty(position = 10,example = "4")
    private Integer level;

    @ApiModelProperty(position = 20, value = "{'en': 'Ukraine'}")
    private Map<String, String> name;

    @ApiModelProperty(position = 30, value = "{'en': 'Country'}")
    private Map<String, String> type;

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

    public Map<String, String> getType() {
        return type;
    }

    public void setType(Map<String, String> type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
