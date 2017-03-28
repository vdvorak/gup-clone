package ua.com.itproekt.gup.service.blockchain.contract;


public class Contract {

    private Transaction transaction;

    public Contract(Transaction transaction){
        this.transaction = transaction;
    }

    public Contract(Transaction transaction, String type){
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
