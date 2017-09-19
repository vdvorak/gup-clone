package ua.com.itproekt.gup.model.order.blockchain_test.transaction;


public class TransactionDataContract {

    private int logicRef;
    private String[] members;
    private String productID;
    private long price;

    private TransactionDataContract(){
    }
    public TransactionDataContract(int logicRef, String[] members, String productID, long price){
        this.logicRef = logicRef;
        this.members = members;
        this.productID = productID;
        this.price = price;
    }

    public int getLogicRef() {
        return logicRef;
    }

    public void setLogicRef(int logicRef) {
        this.logicRef = logicRef;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
