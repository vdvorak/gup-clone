package ua.com.itproekt.gup.model.order.blockchain_test.transaction;


public class MoneyTransferTransactionOutput {

    private int amount;
    private String to;
    private int type;

    public MoneyTransferTransactionOutput(){}
    public MoneyTransferTransactionOutput(int amount, String to, int type){
        this.amount = amount;
        this.to = to;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Output{" +
                "amount=" + amount +
                ", to='" + to + '\'' +
                ", type=" + type +
                '}';
    }
}
