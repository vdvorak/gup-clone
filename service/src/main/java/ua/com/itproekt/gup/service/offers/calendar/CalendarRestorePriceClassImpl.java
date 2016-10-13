package ua.com.itproekt.gup.service.offers.calendar;

public class CalendarRestorePriceClassImpl {

    private CalendarPrice weekdays;
    private CalendarPrice weekends;
    private CalendarPrice[] specialdays;

    public CalendarPrice getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(CalendarPrice weekdays) {
        this.weekdays = weekdays;
    }

    public CalendarPrice getWeekends() {
        return weekends;
    }

    public void setWeekends(CalendarPrice weekends) {
        this.weekends = weekends;
    }

    public CalendarPrice[] getSpecialdays() {
        return specialdays;
    }

    public void setSpecialdays(CalendarPrice[] specialdays) {
        this.specialdays = specialdays;
    }

}
