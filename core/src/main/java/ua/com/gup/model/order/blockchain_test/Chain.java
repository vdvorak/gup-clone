package ua.com.itproekt.gup.model.order.blockchain_test;


public class Chain {
    private Transaction transaction;

    private Chain() {
    }
    public Chain(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
