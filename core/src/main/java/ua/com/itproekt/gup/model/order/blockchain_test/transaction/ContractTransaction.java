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


public class ContractTransaction extends Transaction {

    private String                    type; /* String                                */
    private String                   _hash; /* (type + <random> + timestamp) SHA-256 */
    private String                    data; /* (JSON) SHA-256                        */
    private TransactionSignature signature; /* Class                                 */
    private long                 timestamp; /* Long                                  */

    /**
     *
     * @param _hash          String
     * @param members        String[]
     * @param timestamp      Long
     * @param additionalInfo String
     */
    public ContractTransaction(String _hash, String[] members, long timestamp, String additionalInfo)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchProviderException, IllegalArgumentException {
        this("CONTRACT", _hash, members, timestamp, 0, additionalInfo);
    }
    private ContractTransaction(String type, String _hash, String[] members, long timestamp, int logicRef, String additionalInfo)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchProviderException, IllegalArgumentException {
        this.type = type;
        this.timestamp = timestamp;
        this._hash = (_hash!=null && this._hash==null) ? _hash : get_hash();
        setData(logicRef, members, additionalInfo);
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

    @Override
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

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", _hash=" + _hash +
                ", data=" + data +
                ", signature=" + signature +
                ", timestamp=" + timestamp +
                '}';
    }
}
