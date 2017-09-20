package ua.com.gup.bank_api.entity;


public class Bonus {

    private String userId;

    private int amount;

    public Bonus() {}

    public Bonus(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
