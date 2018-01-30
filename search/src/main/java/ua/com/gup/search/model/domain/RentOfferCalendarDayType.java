package ua.com.gup.search.model.domain;

public enum RentOfferCalendarDayType {

    BUSINESS(0),
    WEEKEND(1),
    HOLIDAY(2),
    CUSTOM(3);

    private int type;

    RentOfferCalendarDayType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static RentOfferCalendarDayType fromInteger(int x) {
        switch (x) {
            case 0:
                return BUSINESS;
            case 1:
                return WEEKEND;
            case 2:
                return HOLIDAY;
            case 3:
                return CUSTOM;
        }
        return null;
    }

    @Override
    public String toString() {
        return "" + type;
    }
}
