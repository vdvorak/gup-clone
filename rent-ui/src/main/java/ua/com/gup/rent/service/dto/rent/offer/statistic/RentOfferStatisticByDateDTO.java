package ua.com.gup.rent.service.dto.rent.offer.statistic;

public class RentOfferStatisticByDateDTO extends RentOfferStatisticDTO {

    private String date;

    public RentOfferStatisticByDateDTO() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
