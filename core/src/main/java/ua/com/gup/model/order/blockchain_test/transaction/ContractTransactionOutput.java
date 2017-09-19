package ua.com.itproekt.gup.model.order.blockchain_test.transaction;


public class ContractTransactionOutput {

    private String _hash;
    private int index;

    public ContractTransactionOutput(){
    }

    public ContractTransactionOutput(String _hash, int index){
        this._hash = _hash;
        this.index = index;
    }

    public String get_hash() {
        return _hash;
    }

    public void set_hash(String _hash) {
        this._hash = _hash;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Output{" +
                "_hash='" + _hash + '\'' +
                ", index=" + index +
                '}';
    }
}
