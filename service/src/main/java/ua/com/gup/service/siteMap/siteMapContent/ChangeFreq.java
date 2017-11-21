package ua.com.gup.service.siteMap.siteMapContent;

public enum ChangeFreq {
    ALWAYS,
    HOURLY,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY,
    NEVER;

    String lowerCase;

    private ChangeFreq() {
        lowerCase = this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return lowerCase;
    }
}
