package ua.com.itproekt.gup.model.order.blockchain_test.transaction;


public class TransactionDataAction {

    private String contractHash;
    private String actionID;

    public TransactionDataAction(String contractHash, String actionID){
        this.contractHash = contractHash;;
        this.actionID = actionID;
    }

    public String getContractHash() {
        return contractHash;
    }

    public void setContractHash(String contractHash) {
        this.contractHash = contractHash;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "contractHash='" + contractHash + '\'' +
                ", actionID='" + actionID + '\'' +
                '}';
    }
}
