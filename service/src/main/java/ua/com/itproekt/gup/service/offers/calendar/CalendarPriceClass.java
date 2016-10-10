package ua.com.itproekt.gup.service.offers.calendar;

public class CalendarPriceClass {

    private Long weekdayPrice;
    private Long weekendPrice;
    private CalendarSpecialPriceClass specialPrice;

    public Long getWeekdayPrice() {
        return weekdayPrice;
    }

    public void setWeekdayPrice(Long weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }

    public Long getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(Long weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public CalendarSpecialPriceClass getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(CalendarSpecialPriceClass specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public String toString() {
        return "prices{" +
                "weekdayPrice=" + weekdayPrice +
                ", weekendPrice=" + weekendPrice +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
