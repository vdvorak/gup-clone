package ua.com.itproekt.gup.model.offer.paidservices;

public enum Marked {
    THREE("3"),
    SIX("6"),
    TWELVE("12"),
    NONE("0");

    private String period;

    private Marked(String period) {
        this.period = period;
    }

    static public Marked getMarked(String period) {
        for (Marked marked: Marked.values()) {
            if (marked.period().equals(period)) {
                return marked;
            }
        }
        return NONE;
    }

    public String period() {
        return period;
    }
}
