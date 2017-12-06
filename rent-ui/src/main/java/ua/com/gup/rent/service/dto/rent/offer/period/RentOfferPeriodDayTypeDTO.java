package ua.com.gup.rent.service.dto.rent.offer.period;

public enum RentOfferPeriodDayTypeDTO {

    BUSINESS(0),
    WEEKEND(1),
    HOLIDAY(2),
    CUSTOM(3);


    private Integer type;

    RentOfferPeriodDayTypeDTO(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
