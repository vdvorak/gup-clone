package ua.com.itproekt.gup.model.order.blockchain;


import ua.com.itproekt.gup.model.order.blockchain.contract.Transaction;

public class Chain {

    private Transaction transaction;

    public Chain(Transaction transaction){
        this.transaction = transaction;
    }

    public Chain(Transaction transaction, String type){
        this.transaction = transaction;
        this.transaction.setType(type);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "transaction=" + transaction +
                '}';
    }
}
