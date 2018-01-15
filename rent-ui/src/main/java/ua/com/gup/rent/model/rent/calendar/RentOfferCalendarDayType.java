package ua.com.gup.rent.model.rent.calendar;

public enum RentOfferCalendarDayType {

    BUSINESS(0),
    WEEKEND(1);

    private int type;

    RentOfferCalendarDayType(int type) {
        this.type = type;
    }
}
