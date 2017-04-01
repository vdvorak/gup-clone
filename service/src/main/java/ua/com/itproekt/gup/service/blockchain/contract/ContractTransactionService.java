package ua.com.itproekt.gup.service.blockchain.contract;

import java.security.*;

import java.security.Security;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.model.order.blockchain.Chain;
import ua.com.itproekt.gup.model.order.blockchain.contract.Transaction;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionSignature;
import ua.com.itproekt.gup.util.FileKeyGenerator;


public class ContractTransactionService {

    public static final String URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction
    public static final String     TYPE_TRANSACTION = "CONTRACT"; // тип транзакции
    public static final String ID_SELLER = "587ca08e4c8e89327948309e", ID_BUYER = "58cae20e4c8e9634fe40e852"; // Seller (продавец: User-0); Buyer (покупатель: User-1) - иннициатор;


    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        /////////////////////////////////////////////////// Профиль (0.1)
        // Делаем в момент регистрации нового юзера
        FileKeyGenerator keyPair = new FileKeyGenerator();        // 1. Создаем пользовательские ключи для шифрования (generate RSA-Key...)


        Gson gson = new Gson();
        /////////////////////////////////////////////////// Профиль (0.2)
        // При покупке вытягиваю информацию-покупателя об юзере
        long timestamp = new Date().getTime();
        String    TEST = gson.toJson(new TransactionData(0, new String[]{ID_SELLER, ID_BUYER}, "ul-drahomanova-dlia-odnoho-muzhchiny-ili-pary-bez-detei-h7")); // Есть пользовательские данные которые нужно зашифровать, передать и проверить...
        String strHash = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265

        System.err.println(timestamp);
        System.err.println(TEST);
        System.err.println(strHash);


        byte[] DATA_TRANSACTION, SIGNATURE, HASH_TRANSACTION;
        String SIGNATURE_TRANSACTION, CONTRACT_TRANSACTION;
        /////////////////////////////////////////////////// Клиент (0.1)
        // (сторона-1)
        // Делаем запрос в банк - узнаем счет-покупателя и баланс на этом счете покупателя
        // Дальше, если покупатель платеже-способен - тогда шифруем данные клиента
        MessageDigest msg = MessageDigest.getInstance("SHA-256"); // 2. Указываем тип шифрования ( формат: SHA-256 )
        DATA_TRANSACTION  = TEST.getBytes("UTF8");                // 3. Переводим пользовательские данные в байт-код
        byte[] digestDATA = msg.digest(DATA_TRANSACTION);
        HASH_TRANSACTION  = strHash.getBytes("UTF-8");
        byte[] digestHASH = msg.digest(HASH_TRANSACTION);

        Signature signature1 = Signature.getInstance("RSA", "BC");
        signature1.initSign( keyPair.getPrivate() );              // 4. Создаем подпись-владельца на основе клиентского приватного ключа
        signature1.update(digestDATA);

        SIGNATURE = signature1.sign();                            // 5. Подписываем эти данные-владельца...
        System.out.println( "(String) type:        " + TYPE_TRANSACTION );
        System.out.println( "  (Long) timestamp:   " + timestamp );
        System.out.println( "(digest) DATA:        " + Hex.toHexString(digestDATA) ); // System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        System.out.println( "(digest) hash:        " + Hex.toHexString(digestHASH));
        System.out.println( "   (RSA) PRIVATE-KEY: " + keyPair.getPrivate() );
//        byte[] digestSIGNATURE = msg.digest(SIGNATURE);
//        System.out.println( "Singature: " + Hex.toHexString(SIGNATURE));  // System.out.println( "Singature: " + new BASE64Encoder().encode( SIGNATURE ) );
//        System.out.println( "Singature: " + (Hex.toHexString(digestSIGNATURE)));
        SIGNATURE_TRANSACTION = gson.toJson(new TransactionSignature(
                Hex.toHexString(SIGNATURE), // (String) Hex
                keyPair.readPublic())       // RSA Public Key
        );
        System.out.println( "Singature (TRANSACTION): " + SIGNATURE_TRANSACTION);


        /////////////////////////////////////////////////// Клиент (0.2)
        // Создаем транзакцию типа: 'CONTRACT'
        Transaction transaction = new Transaction(
                Hex.toHexString(digestDATA),                                                // (JSON) SHA-256
                new TransactionSignature(Hex.toHexString(SIGNATURE), keyPair.readPublic()), // Class
                timestamp,                                                                  // Long
                Hex.toHexString(digestHASH)                                                 // (type + <random> + timestamp) SHA-256
        );
        Chain contract = new Chain(TYPE_TRANSACTION, transaction);
        CONTRACT_TRANSACTION = gson.toJson(contract);
        System.out.println( "Contract (TRANSACTION): " + CONTRACT_TRANSACTION);















        System.out.println();
        System.out.println();
        System.out.println();
        /////////////////////////////////////////////////// Клиент (сторона-2)
        Signature signature2 = Signature.getInstance("RSA", "BC");
        signature2.initVerify( keyPair.getPublic() );  // 6. Расшифровываем подпись-владельца на основе клиентского публичного ключа
        signature2.update(digestDATA);

        System.out.println( "digestDATA: " + Hex.toHexString(digestDATA) ); // System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
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
