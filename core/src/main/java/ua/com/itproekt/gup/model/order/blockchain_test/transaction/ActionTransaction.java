package ua.com.itproekt.gup.model.order.blockchain_test.transaction;

import ua.com.itproekt.gup.model.order.blockchain_test.Transaction;
import ua.com.itproekt.gup.model.order.blockchain_test.TransactionSignature;
import ua.com.itproekt.gup.util.FileKeyGenerator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;


public class ActionTransaction extends Transaction {

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
     * @param userId    String
     * @param timestamp Long
     * @param productID String
     * @param price     Long
     */
    public ActionTransaction(String _hash, String userId, long timestamp, String productID, long price, String contractHash, String actionID) //TODO ??????????
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchProviderException, IllegalArgumentException {
        this("ACTION", _hash, userId, timestamp, 0, productID, price, contractHash, actionID);
    }
    private ActionTransaction(String type, String _hash, String userId, long timestamp, int logicRef, String productID, long price, String contractHash, String actionID)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchProviderException, IllegalArgumentException {
        this.type = type;
        this.timestamp = timestamp;
        this._hash = (_hash!=null && this._hash==null) ? _hash : get_hash();
        getData(contractHash, actionID); //setData(getData(logicRef, new String[]{userId}, productID, price));
        setSignature(userId);
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
