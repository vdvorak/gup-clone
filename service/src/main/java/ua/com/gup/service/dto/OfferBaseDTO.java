package ua.com.gup.service.dto;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.OfferCategory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class OfferBaseDTO implements Serializable {

    @ApiModelProperty(position = 40, example = "title")
    private String title;

    @ApiModelProperty(position = 50, example = "description")
    private String description;

    @ApiModelProperty(position = 70)
    private OfferPriceDTO price;

    @ApiModelProperty(position = 110, value = "{\"color\": \"Black\", \"size\":\"XL\"}")
    private Map<String, String> attrs = new HashMap<>();

    @ApiModelProperty(position = 115, value = "{\"deliveryType\": \"NOVAPOSHTA,INTIME\"}")
    private Map<String,String> multiAttrs = new HashMap<>();

    @ApiModelProperty(position = 120, value = "{\"year\": 2011, \"volume_sm3\":1500}")
    private Map<String, Long> numAttrs = new HashMap<>();

    @ApiModelProperty(position = 130, value = "{\"used\": true, \"mediator\":false}")
    private Map<String, Boolean> boolAttrs = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferPriceDTO getPrice() {
        return price;
    }

    public void setPrice(OfferPriceDTO price) {
        this.price = price;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    public Map<String, String> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(Map<String, String> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public Map<String, Long> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(Map<String, Long> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public Map<String, Boolean> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(Map<String, Boolean> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }
}
