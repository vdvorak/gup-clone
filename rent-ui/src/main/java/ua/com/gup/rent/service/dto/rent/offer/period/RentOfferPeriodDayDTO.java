package ua.com.gup.rent.service.dto.rent.offer.period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferPeriodDayDTO {

    @JsonProperty("type")
    private RentOfferPeriodDayTypeDTO type;

    @JsonProperty("price")
    private RentOfferPeriodDayPriceDTO price;

    public RentOfferPeriodDayDTO() {
    }

    public RentOfferPeriodDayTypeDTO getType() {
        return type;
    }

    public void setType(RentOfferPeriodDayTypeDTO type) {
        this.type = type;
    }

    public RentOfferPeriodDayPriceDTO getPrice() {
        return price;
    }

    public void setPrice(RentOfferPeriodDayPriceDTO price) {
        this.price = price;
    }
}
