package ua.com.itproekt.gup.service.offers.calendar;

public class CalendarPriceClass {

    private Long weekdayPrice;
    private Long weekendPrice;
    private CalendarPrice specialPrice;

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

    public CalendarPrice getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(CalendarPrice specialPrice) {
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
