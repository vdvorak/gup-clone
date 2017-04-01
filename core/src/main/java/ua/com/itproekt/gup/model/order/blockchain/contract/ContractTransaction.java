package ua.com.itproekt.gup.model.order.blockchain.contract;


public class ContractTransaction implements Transaction {

    private String                    type; /* String                                */
    private String                    data; /* (JSON) SHA-256                        */
    private TransactionSignature signature; /* Class                                 */
    private long                 timestamp; /* Long                                  */
    private String                   _hash; /* (type + <random> + timestamp) SHA-256 */

    /**
     * @param data      (JSON) SHA-256
     * @param signature Class
     * @param timestamp Long
     * @param _hash     (type + <random> + timestamp) SHA-256
     */
    public ContractTransaction(String data, TransactionSignature signature, long timestamp, String _hash){
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

    public TransactionSignature getSignature() {
        return signature;
    }

    public void setSignature(TransactionSignature signature) {
        this.signature = signature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
