package ua.com.gup.rent.service.dto.period;

public enum RentPeriodDayTypeDTO {

    BUSINESS(0),
    WEEKEND(1),
    HOLIDAY(2),
    CUSTOM(3);


    private Integer type;

    private RentPeriodDayTypeDTO(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
