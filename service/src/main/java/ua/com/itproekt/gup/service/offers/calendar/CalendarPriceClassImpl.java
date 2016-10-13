package ua.com.itproekt.gup.service.offers.calendar;

public class CalendarPriceClassImpl {

    private CalendarSpecialPriceClass2 weekdays;
    private CalendarSpecialPriceClass2 weekends;
    private CalendarSpecialPriceClass2[] specials;

    public CalendarSpecialPriceClass2 getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(CalendarSpecialPriceClass2 weekdays) {
        this.weekdays = weekdays;
    }

    public CalendarSpecialPriceClass2 getWeekends() {
        return weekends;
    }

    public void setWeekends(CalendarSpecialPriceClass2 weekends) {
        this.weekends = weekends;
    }

    public CalendarSpecialPriceClass2[] getSpecials() {
        return specials;
    }

    public void setSpecials(CalendarSpecialPriceClass2[] specials) {
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
