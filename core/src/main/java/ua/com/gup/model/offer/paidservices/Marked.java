package ua.com.itproekt.gup.model.offer.paidservices;

public enum Marked {
    ONE(1),
    THREE(3),
    SIX(6),
    TWELVE(12),
    NONE(0);

    private int period;

    private Marked(int period) {
        this.period = period;
    }

    static public Marked getMarked(int period) {
        for (Marked marked: Marked.values()) {
            if( marked.period()==period ){
                return marked;
            }
        }
        return NONE;
    }

    public int period() {
        return period;
    }
}
