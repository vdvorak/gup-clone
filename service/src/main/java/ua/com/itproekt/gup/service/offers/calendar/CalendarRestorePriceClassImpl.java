package ua.com.itproekt.gup.service.offers.calendar;

public class CalendarRestorePriceClassImpl {

    private CalendarRestorePriceClass weekdays;
    private CalendarRestorePriceClass weekends;
    private CalendarRestorePriceClass[] specials;

    public CalendarRestorePriceClass getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(CalendarRestorePriceClass weekdays) {
        this.weekdays = weekdays;
    }

    public CalendarRestorePriceClass getWeekends() {
        return weekends;
    }

    public void setWeekends(CalendarRestorePriceClass weekends) {
        this.weekends = weekends;
    }

    public CalendarRestorePriceClass[] getSpecials() {
        return specials;
    }

    public void setSpecials(CalendarRestorePriceClass[] specials) {
        this.specials = specials;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "\"weekdays\": {" + weekdays + "}" +
//                ", \"weekends\": {" + weekends + "}" +
//                ", \"specials\": [" + specials + "]" +
//                '}';
//    }
}
