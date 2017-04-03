package ua.com.itproekt.gup.model.order.blockchain_test.transaction;


public class TransactionDataMoneyTransfer {

    private int logicRef;
    private String[] members;
    private AdditionalInfo additionalInfo;

    private TransactionDataMoneyTransfer(){
    }
    public TransactionDataMoneyTransfer(int logicRef, String[] members, String additionalInfo){
        this.logicRef = logicRef;
        this.members = members;
        this.additionalInfo = new AdditionalInfo(additionalInfo);
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

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    private class AdditionalInfo {
        private String message;

        public AdditionalInfo(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "AdditionalInfo{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
