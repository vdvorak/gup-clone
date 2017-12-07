package ua.com.gup.rent.service.dto.rent.offer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.rent.service.dto.rent.offer.period.RentOfferPeriodDTO;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO for creation the RentOffer Offer entity.
 */
@ApiModel(description = "DTO for RentOffer Offer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCreateDTO extends RentOfferDTO {

    @ApiModelProperty(position = 30)
    private Integer category;

    @ApiModelProperty(position = 60, example = "ret price")
    private RentOfferPriceDTO price;

    @ApiModelProperty(position = 70, example = "send file's")
    private MultipartFile[] images;

    @ApiModelProperty(position = 80, example = "rent  period")
    private RentOfferPeriodDTO period;

    @ApiModelProperty(position = 110, value = "{'color': 'Black', 'size':'XL'}")
    private Map<String, String> attrs = new HashMap<>();

    @ApiModelProperty(position = 115, value = "{'deliveryType': 'NOVAPOSHTA,INTIME'}")
    private Map<String, String> multiAttrs = new HashMap<>();

    @ApiModelProperty(position = 120, value = "{'year': 2011, 'volume_sm3':1500}")
    private Map<String, BigDecimal> numAttrs = new HashMap<>();

    @ApiModelProperty(position = 130, value = "{'used': true, 'mediator':false}")
    private Map<String, Boolean> boolAttrs = new HashMap<>();

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public RentOfferPriceDTO getPrice() {
        return price;
    }

    public void setPrice(RentOfferPriceDTO price) {
        this.price = price;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public RentOfferPeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(RentOfferPeriodDTO period) {
        this.period = period;
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

    public Map<String, BigDecimal> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(Map<String, BigDecimal> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public Map<String, Boolean> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(Map<String, Boolean> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }
}