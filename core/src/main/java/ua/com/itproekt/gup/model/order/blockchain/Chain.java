package ua.com.itproekt.gup.model.order.blockchain;


import ua.com.itproekt.gup.model.order.blockchain.contract.ContractTransaction;

public class Chain {

    private Transaction transaction;

    public Chain(String type, ContractTransaction transaction){
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
        return "Chain{" +
                "transaction=" + transaction +
                '}';
    }
}
