package ua.com.gup.model.order.blockchain_test.transaction;


public class ContractTransactionInput {

    private String _hash;
    private int index;

    public ContractTransactionInput(){
    }

    public ContractTransactionInput(String _hash, int index){
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
        return "Input{" +
                "_hash='" + _hash + '\'' +
                ", index=" + index +
                '}';
    }
}
