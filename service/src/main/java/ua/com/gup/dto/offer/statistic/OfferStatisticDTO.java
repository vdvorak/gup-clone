package ua.com.gup.dto.offer.statistic;

import io.swagger.annotations.ApiModelProperty;

public class OfferStatisticDTO {

    @ApiModelProperty(position = 10, example = "10")
    private Integer offerViews;

    @ApiModelProperty(position = 10, example = "20")
    private Integer offerPhonesViews;

    public OfferStatisticDTO() {
    }

    public OfferStatisticDTO(Integer offerViews, Integer offerPhonesViews) {
        this.offerViews = offerViews;
        this.offerPhonesViews = offerPhonesViews;
    }

    public Integer getOfferViews() {
        return offerViews;
    }

    public void setOfferViews(Integer offerViews) {
        this.offerViews = offerViews;
    }

    public Integer getOfferPhonesViews() {
        return offerPhonesViews;
    }

    public void setOfferPhonesViews(Integer offerPhonesViews) {
        this.offerPhonesViews = offerPhonesViews;
    }
}
