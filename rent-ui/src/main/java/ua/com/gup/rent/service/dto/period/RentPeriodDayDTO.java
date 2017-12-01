package ua.com.gup.rent.service.dto.period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentPeriodDayDTO {

    @JsonProperty("type")
    private RentPeriodDayTypeDTO type;

    @JsonProperty("price")
    private RentPeriodDayPriceDTO price;

    public RentPeriodDayDTO() {
    }

    public RentPeriodDayTypeDTO getType() {
        return type;
    }

    public void setType(RentPeriodDayTypeDTO type) {
        this.type = type;
    }

    public RentPeriodDayPriceDTO getPrice() {
        return price;
    }

    public void setPrice(RentPeriodDayPriceDTO price) {
        this.price = price;
    }
}
