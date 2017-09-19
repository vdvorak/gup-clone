package ua.com.gup.model.order.blockchain_test.transaction;


public class TransactionDataMoneyTransfer {

    private String userCardDetails;
    private long amount;
    private String publicHashStore;
    private String signatureStore;
    private long bankTransactionID;

    public TransactionDataMoneyTransfer(String userCardDetails, long amount, String publicHashStore, String signatureStore, long bankTransactionID){
        this.userCardDetails = userCardDetails;
        this.amount = amount;
        this.publicHashStore = publicHashStore;
        this.signatureStore = signatureStore;
        this.bankTransactionID = bankTransactionID;
    }

    public String getUserCardDetails() {
        return userCardDetails;
    }

    public void setUserCardDetails(String userCardDetails) {
        this.userCardDetails = userCardDetails;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPublicHashStore() {
        return publicHashStore;
    }

    public void setPublicHashStore(String publicHashStore) {
        this.publicHashStore = publicHashStore;
    }

    public String getSignatureStore() {
        return signatureStore;
    }

    public void setSignatureStore(String signatureStore) {
        this.signatureStore = signatureStore;
    }

    public long getBankTransactionID() {
        return bankTransactionID;
    }

    public void setBankTransactionID(long bankTransactionID) {
        this.bankTransactionID = bankTransactionID;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userCardDetails='" + userCardDetails + '\'' +
                ", amount='" + amount + '\'' +
                ", publicHashStore='" + publicHashStore + '\'' +
                ", signatureStore='" + signatureStore + '\'' +
                ", bankTransactionID='" + bankTransactionID + '\'' +
                '}';
    }
}
