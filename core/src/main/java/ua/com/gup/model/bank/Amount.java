package ua.com.gup.model.bank;

public enum Amount {
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500);

    private int value;

    Amount(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
