package ua.com.itproekt.gup.service.offers.calendar;

public enum TestDays {
    ONE("06.06.2014");

    private final String name;

    TestDays(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName != null) && name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
