package ua.com.gup.dto.offer.statistic;

import java.time.LocalDate;


public class OfferStatisticByDateDTO {

    private String date;
    private Integer offerViews;
    private Integer offerPhonesViews;


    public OfferStatisticByDateDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
