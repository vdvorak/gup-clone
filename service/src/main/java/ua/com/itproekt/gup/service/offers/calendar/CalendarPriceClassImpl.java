package ua.com.itproekt.gup.service.offers.calendar;

public class CalendarPriceClassImpl {

    private Long weekdays;
    private Long weekends;
    private CalendarSpecialPriceClass[] specials;

    public Long getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Long weekdays) {
        this.weekdays = weekdays;
    }

    public Long getWeekends() {
        return weekends;
    }

    public void setWeekends(Long weekends) {
        this.weekends = weekends;
    }

    public CalendarSpecialPriceClass[] getSpecials() {
        return specials;
    }

    public void setSpecials(CalendarSpecialPriceClass[] specials) {
        this.specials = specials;
    }

    @Override
    public String toString() {
        return "{" +
                "\"weekdays\": {" + weekdays + "}" +
                ", \"weekends\": {" + weekends + "}" +
                ", \"specials\": [" + specials + "]" +
                '}';
    }
}
