package ua.com.gup.model.order.blockchain_test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import com.google.gson.Gson;

import org.bouncycastle.util.encoders.Hex;
import ua.com.gup.model.order.blockchain_test.transaction.TransactionDataAction;
import ua.com.gup.model.order.blockchain_test.transaction.TransactionDataContract;
import ua.com.gup.model.order.blockchain_test.transaction.TransactionDataMoneyTransfer;


abstract public class Transaction {

    abstract public String getType();
    abstract public String getData();
    abstract public TransactionSignature getSignature();
    abstract public void setSignature(TransactionSignature signature);
    abstract public long getTimestamp();

    /**
     * @return (type + json[data] + timestamp) SHA-256
     */
    public String get_hash()
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest       msg = MessageDigest.getInstance("SHA-256"); //TODO 1. Указываем тип шифрования ( формат: SHA-256 )
        String          strHash = getType() + getData() + getTimestamp();
        byte[] HASH_TRANSACTION = strHash.getBytes("UTF-8");            //TODO 2. Переводим данные в байт-код
        byte[]       digestHASH = msg.digest(HASH_TRANSACTION);         //TODO 3. шифруем ( формат: SHA-256 )
        return Hex.toHexString(digestHASH);
    }

    /**
     * JSON) SHA-256
     *
     * @param logicRef
     * @param members
     * @param price
     * @param productID
     */
    public String getData(String type, String _hash, String[] members, long timestamp, int logicRef, String productID, long price)
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Gson gson = new Gson();
        return gson.toJson(new TransactionDataContract(logicRef, members, productID, price)); // пользовательские данные которые нужно зашифровать, передать и проверить...
    }

    public String getData(String userCardDetails, long amount, String publicHashStore, String signatureStore, long bankTransactionID)
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Gson gson = new Gson();
        return gson.toJson(new TransactionDataMoneyTransfer(userCardDetails, amount, publicHashStore, signatureStore, bankTransactionID));
    }

    public String getData(String contractHash, String actionID)
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Gson gson = new Gson();
        return gson.toJson(new TransactionDataAction(contractHash, actionID));
    }

    public void setSignature(PrivateKey privateKey, String publicKey)
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Signature               signature = Signature.getInstance("RSA", "BC");
        signature.initSign(privateKey);                       //TODO 2. Создаем подпись-владельца на основе клиентского приватного ключа
        byte[]                  SIGNATURE = signature.sign(); //TODO 3. Подписываем эти данные-владельца...

        setSignature(new TransactionSignature(publicKey));    //TODO RSA Public Key = (String) Hex
        TransactionSignature objSignature = getSignature();
        objSignature.setSign(Hex.toHexString(SIGNATURE));     //TODO (String) Hex
    }

    private String hashGenerator(){
        char[]     chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random    random = new Random();
        for (int i=0; i<10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
