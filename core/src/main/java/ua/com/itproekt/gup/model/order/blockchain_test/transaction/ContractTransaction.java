package ua.com.itproekt.gup.model.order.blockchain_test.transaction;

import ua.com.itproekt.gup.model.order.blockchain_test.Transaction;
import ua.com.itproekt.gup.model.order.blockchain_test.TransactionSignature;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;


public class ContractTransaction extends Transaction {

    private String                    type; /* String                                */
    private String                   _hash; /* (type + <random> + timestamp) SHA-256 */
    private String                    data; /* (JSON) SHA-256                        */
    private TransactionSignature signature; /* Class                                 */
    private long                 timestamp; /* Long                                  */
    private ContractTransactionInput[] inputs;
    private ContractTransactionOutput[] outputs;

    /**
     *
     * @param _hash     String
     * @param members   String[]
     * @param timestamp Long
     * @param productID String
     * @param price     Long
     */
    public ContractTransaction(String _hash, String[] members, long timestamp, String productID, long price)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchProviderException, IllegalArgumentException {
        this("CONTRACT", _hash, members, timestamp, 0, productID, price);
    }
    private ContractTransaction(String type, String _hash, String[] members, long timestamp, int logicRef, String productID, long price)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchProviderException, IllegalArgumentException {
        this.type = type;
        this.timestamp = timestamp;
        setData(getData(type, _hash, members, timestamp, logicRef, productID, price));
        this._hash = (_hash!=null && this._hash==null) ? _hash : get_hash();
        if (2<=members.length) setSignature(members[0]); else throw new IllegalArgumentException("ID_SELLER & ID_BUYER ?");
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public TransactionSignature getSignature(){
        return signature;
    }

    @Override
    public void setSignature(TransactionSignature signature){
        this.signature = signature;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ContractTransactionInput[] getInputs() {
        return inputs;
    }

    public void setInputs(ContractTransactionInput[] inputs) {
        this.inputs = inputs;
    }

    public ContractTransactionOutput[] getOutputs() {
        return outputs;
    }

    public void setOutputs(ContractTransactionOutput[] outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", _hash='" + _hash + '\'' +
                ", data='" + data + '\'' +
                ", signature=" + signature +
                ", timestamp=" + timestamp +
                ", inputs=" + Arrays.toString(inputs) +
                ", outputs=" + Arrays.toString(outputs) +
                '}';
    }
}
