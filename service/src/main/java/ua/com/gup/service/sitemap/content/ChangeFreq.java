package ua.com.gup.service.sitemap.content;

public enum ChangeFreq {
    ALWAYS,
    HOURLY,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY,
    NEVER;

    String lowerCase;

    ChangeFreq() {
        lowerCase = this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return lowerCase;
    }
}
