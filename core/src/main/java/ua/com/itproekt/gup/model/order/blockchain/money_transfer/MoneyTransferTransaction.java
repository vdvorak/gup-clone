package ua.com.itproekt.gup.model.order.blockchain.money_transfer;

import ua.com.itproekt.gup.model.order.blockchain.Transaction;
import ua.com.itproekt.gup.model.order.blockchain.TransactionSignature;

import java.util.Arrays;


public class MoneyTransferTransaction implements Transaction {

    private String                    type; /* String                                */
    private String                    data; /* (JSON) SHA-256                        */
    private TransactionSignature signature; /* Class                                 */
    private long                 timestamp; /* Long                                  */
    private String                   _hash; /* (type + <random> + timestamp) SHA-256 */
    private String[]                inputs; /* SHA-256                               */
    private TransactionOutput[]   outputs; /* Class                                 */

    /**
     *
     * @param data      (JSON) SHA-256
     * @param signature Class
     * @param timestamp Long
     * @param _hash     (type + <random> + timestamp) SHA-256
     * @param inputs    SHA-256
     * @param outputs   Class
     */
    public MoneyTransferTransaction(String data, TransactionSignature signature, long timestamp, String _hash, String[] inputs, TransactionOutput[] outputs){
        this.data = data;
        this.signature = signature;
        this.timestamp = timestamp;
        this._hash = _hash;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String get_hash() {
        return _hash;
    }

    public void set_hash(String _hash) {
        this._hash = _hash;
    }

    public String[] getInputs() {
        return inputs;
    }

    public void setInputs(String[] inputs) {
        this.inputs = inputs;
    }

    public TransactionOutput[] getOutputs() {
        return outputs;
    }

    public void setOutputs(TransactionOutput[] outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "MoneyTransferTransaction{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", signature=" + signature +
                ", timestamp=" + timestamp +
                ", _hash='" + _hash + '\'' +
                ", inputs=" + Arrays.toString(inputs) +
                ", outputs=" + Arrays.toString(outputs) +
                '}';
    }
}
