package ua.com.itproekt.gup.model.order.blockchain_test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.util.FileKeyGenerator;


abstract public class Transaction {

    abstract public String getType();
    abstract public String getData();
    abstract public void setData(String data);
    abstract public TransactionSignature getSignature();
    abstract public void setSignature(TransactionSignature signature);
    abstract public long getTimestamp();

    /**
     * @return (type + <random> + timestamp) SHA-256
     */
    protected String get_hash()
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest       msg = MessageDigest.getInstance("SHA-256"); // 1. Указываем тип шифрования ( формат: SHA-256 )
        String          strHash = getType() + hashGenerator() + getTimestamp();
        byte[] HASH_TRANSACTION = strHash.getBytes("UTF-8");            // 2. Переводим данные в байт-код
        byte[]       digestHASH = msg.digest(HASH_TRANSACTION);         // 3. шифруем ( формат: SHA-256 )
        return Hex.toHexString(digestHASH);
    }

    /**
     * JSON) SHA-256
     *
     * @param logicRef
     * @param members
     * @param additionalInfo
     */
    protected void setData(int logicRef, String[] members, String additionalInfo)
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Gson gson = new Gson();
        String data = gson.toJson(new TransactionData(logicRef, members, additionalInfo)); // пользовательские данные которые нужно зашифровать, передать и проверить...
        MessageDigest msg = MessageDigest.getInstance("SHA-256");   // 1. Указываем тип шифрования ( формат: SHA-256 )
        byte[] DATA_TRANSACTION  = data.getBytes("UTF8");           // 2. Переводим пользовательские данные в байт-код
        byte[] digestDATA = msg.digest(DATA_TRANSACTION);

        FileKeyGenerator          keyPair = new FileKeyGenerator(); // 3. Создаем пользовательские ключи для шифрования (generate RSA-Key...)   ???   вытягиваем ключ из базы-users...
        Signature               signature = Signature.getInstance("RSA", "BC");
        signature.initSign( keyPair.getPrivate() );                 // 4. Создаем подпись-владельца на основе клиентского приватного ключа
        signature.update(digestDATA);                               // ???????????????????????
        setData(Hex.toHexString(digestDATA));
    }

    protected void setSignature(String idUser)           // вытягиваем ключ по пользователю-владельцу
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        FileKeyGenerator          keyPair = new FileKeyGenerator();   // 1. Создаем пользовательские ключи для шифрования (generate RSA-Key...)   ???   вытягиваем ключ из базы-users...
        Signature               signature = Signature.getInstance("RSA", "BC");
        signature.initSign( keyPair.getPrivate() );                   // 2. Создаем подпись-владельца на основе клиентского приватного ключа
//        signature.update(digestDATA);                               // ???????????????????????
        byte[]                  SIGNATURE = signature.sign();         // 3. Подписываем эти данные-владельца...
        setSignature(new TransactionSignature(keyPair.readPublic())); // RSA Public Key = (String) Hex
        TransactionSignature objSignature = getSignature();
        objSignature.setSign(Hex.toHexString(SIGNATURE));             // (String) Hex
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
