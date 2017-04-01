package ua.com.itproekt.gup.service.blockchain;

import java.security.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import sun.misc.BASE64Encoder;
import ua.com.itproekt.gup.model.order.blockchain.contract.Contract;
import ua.com.itproekt.gup.model.order.blockchain.contract.Transaction;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionSignature;
import ua.com.itproekt.gup.util.FileKeyGenerator;


public class TypeContractGenerator {

    public static final String URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction
    public static final String     TYPE_TRANSACTION = "CONTRACT"; // тип транзакции
    public static final String ID_SELLER = "587ca08e4c8e89327948309e", ID_BUYER = "58cae20e4c8e9634fe40e852"; // Seller (продавец: User-0); Buyer (покупатель: User-1) - иннициатор;


    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        Gson gson = new Gson();

        ///////////////////////////////////////////////////
        long timestamp = new Date().getTime();
        String    TEST = gson.toJson(new TransactionData(0, new String[]{ID_SELLER, ID_BUYER}, "ul-drahomanova-dlia-odnoho-muzhchiny-ili-pary-bez-detei-h7")); // Есть пользовательские данные которые нужно зашифровать, передать и проверить...
        String strHash = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265



        System.err.println(timestamp);
        System.err.println(TEST);
        System.err.println(strHash);





        FileKeyGenerator keyPair = new FileKeyGenerator();        // 1. Создаем пользовательские ключи для шифрования (generate RSA-Key...)


        /////////////////////////////////////////////////// Профиль (0)
        byte[] SIGNATURE, DATA_TRANSACTION, HASH_TRANSACTION;
        String SIGNATURE_TRANSACTION, CONTRACT_TRANSACTION;


        System.out.println();
        /////////////////////////////////////////////////// Клиент (сторона-1)
        DATA_TRANSACTION  = TEST.getBytes("UTF8");                // 2. Переводим пользовательские данные в байт-код
        HASH_TRANSACTION  = strHash.getBytes("UTF-8");
        MessageDigest msg = MessageDigest.getInstance("SHA-256"); // 3. Указываем тип шифрования ( формат: SHA-256 )
        byte[] digestDATA = msg.digest(DATA_TRANSACTION);

        Signature signature1 = Signature.getInstance("RSA", "BC");
        signature1.initSign( keyPair.getPrivate() );              // 4. Создаем подпись-владельца на основе клиентского приватного ключа
        signature1.update(digestDATA);

        SIGNATURE = signature1.sign();                            // 5. Подписываем эти данные-владельца...
        System.out.println( "type: " + TYPE_TRANSACTION );
        System.out.println( "timestamp: " + timestamp );
        System.out.println( "digestDATA: " + Hex.toHexString(digestDATA) ); //        System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        System.out.println( "hash: " + Hex.toHexString(HASH_TRANSACTION));
        System.out.println( "PRIVATE-KEY: " + keyPair.getPrivate() );
//        System.out.println( "Singature: " + Hex.toHexString(SIGNATURE)); //        System.out.println( "Singature: " + new BASE64Encoder().encode( SIGNATURE ) );
        byte[] digestSIGNATURE = msg.digest(SIGNATURE);
//        System.out.println( "Singature: " + (Hex.toHexString(digestSIGNATURE)));
        SIGNATURE_TRANSACTION = gson.toJson(new TransactionSignature(Hex.toHexString(digestSIGNATURE), keyPair.readPublic()));
        System.out.println( "Singature (TRANSACTION): " + SIGNATURE_TRANSACTION);
        // Create the JSON representation of the transaction to post
        Transaction transaction = new Transaction(new TransactionSignature(Hex.toHexString(digestSIGNATURE), keyPair.readPublic()),
                timestamp,
                new TransactionData(0, new String[]{ID_SELLER, ID_BUYER}, "ul-drahomanova-dlia-odnoho-muzhchiny-ili-pary-bez-detei-h7"), // Hex.toHexString(digestDATA)
                Hex.toHexString(HASH_TRANSACTION));
        Contract contract = new Contract(transaction, TYPE_TRANSACTION);
        CONTRACT_TRANSACTION = gson.toJson(contract);
        System.out.println( "Contract (TRANSACTION): " + CONTRACT_TRANSACTION);


        System.out.println();
        System.out.println();
        System.out.println();
        /////////////////////////////////////////////////// Клиент (сторона-2)
        Signature signature2 = Signature.getInstance("RSA", "BC"); // Signature  signature2 = Signature.getInstance(HMAC256);
        signature2.initVerify( keyPair.getPublic() );  // 6. Расшифровываем подпись-владельца на основе клиентского публичного ключа
        signature2.update(digestDATA);

        System.out.println( "digestDATA: " + Hex.toHexString(digestDATA) ); //        System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        System.out.println( "PUBLIC-KEY: " + keyPair.getPublic() );
        System.out.println( "Verify by Singature: " + signature2.verify( SIGNATURE ) );
    }

    public static String hashGenerator(){
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
