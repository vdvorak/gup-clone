package ua.com.itproekt.gup.util;


public enum ServiceNames {
    OFFERS("offers"),
    PROFILE("profile");

    private final String name;

    ServiceNames(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName != null) && name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
