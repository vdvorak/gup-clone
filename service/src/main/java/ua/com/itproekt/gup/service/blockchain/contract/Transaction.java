package ua.com.itproekt.gup.service.blockchain.contract;


public class Transaction {

    private String                        type;
    private TransactionDataSignature signature;
    private long                     timestamp;
    private TransactionData               data;
    private String                       _hash;

    public Transaction(TransactionDataSignature signature, long timestamp, TransactionData data, String _hash){
        this.signature = signature;
        this.timestamp = timestamp;
        this.data = data;
        this._hash = _hash;
    }

    public Transaction(String type, TransactionDataSignature signature, long timestamp, TransactionData data, String _hash){
        this.type = type;
        this.signature = signature;
        this.timestamp = timestamp;
        this.data = data;
        this._hash = _hash;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionDataSignature getSignature() {
        return signature;
    }

    public void setSignature(TransactionDataSignature signature) {
        this.signature = signature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionData getData() {
        return data;
    }

    public void setData(TransactionData data) {
        this.data = data;
    }

    public String get_hash() {
        return _hash;
    }

    public void set_hash(String _hash) {
        this._hash = _hash;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", signature=" + signature +
                ", timestamp=" + timestamp +
                ", data=" + data +
                ", _hash='" + _hash + '\'' +
                '}';
    }
}
