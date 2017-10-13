package ua.com.gup.mongo.model.offer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;
@ApiModel(description = "Address attribute ")
public class AddressArea {

    @ApiModelProperty(position = 10, example = "code")
    private String code;
    @ApiModelProperty(position = 20,example = "4")
    private Integer level;
    @ApiModelProperty(position = 30, value = "{'en': 'Ukraine'}")
    private Map<String, String> name;
    @ApiModelProperty(position = 40, value = "{'en': 'Country'}")
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
