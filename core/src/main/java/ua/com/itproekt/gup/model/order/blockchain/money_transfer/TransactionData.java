package ua.com.itproekt.gup.model.order.blockchain.money_transfer;


public class TransactionData {

    private String userCardDetails;   /* String                                */
    private long amount;              /* Long                                  */
    private String publicHashStore;   /* RSA Public Key                        */
    private String signatureStore;    /* Class                                 */
    private long bankTransactionID;   /* Long                                  */

    private TransactionData(){
    }

    public TransactionData(String userCardDetails, long amount, String publicHashStore, String signatureStore, long bankTransactionID){
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
        return "TransactionData{" +
                "userCardDetails='" + userCardDetails + '\'' +
                ", amount=" + amount +
                ", publicHashStore='" + publicHashStore + '\'' +
                ", signatureStore='" + signatureStore + '\'' +
                ", bankTransactionID='" + bankTransactionID + '\'' +
                '}';
    }
}
