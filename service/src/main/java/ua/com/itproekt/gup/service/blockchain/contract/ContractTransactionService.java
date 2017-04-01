package ua.com.itproekt.gup.service.blockchain.contract;

import java.io.IOException;
import java.security.*;

import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.model.order.blockchain.Chain;
import ua.com.itproekt.gup.model.order.blockchain.contract.ContractTransaction;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionSignature;
import ua.com.itproekt.gup.util.FileKeyGenerator;

import javax.net.ssl.*;

import java.security.cert.CertificateException;

import org.apache.log4j.Logger;


public class ContractTransactionService implements TransactionService {

    protected final static Logger            LOGGER = Logger.getLogger(ContractTransactionService.class);
    public static final String URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction
    public static final String     TYPE_TRANSACTION = "CONTRACT"; // тип транзакции
    private String             CONTRACT_TRANSACTION;
    private String                             hash;
    private FileKeyGenerator                keyPair;

    public ContractTransactionService(String[] members, String additionalInfo)
            throws NullPointerException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException
    {
        Security.addProvider(new BouncyCastleProvider());

        /////////////////////////////////////////////////// Профиль (0.1)
        // Делаем в момент регистрации нового юзера
        String ID_SELLER = null, ID_BUYER = null;
        FileKeyGenerator keyPair = null;
        if (2<=members.length){
            ID_SELLER = members[0];
            ID_BUYER = members[1];
            keyPair = new FileKeyGenerator();                     // 1. Создаем пользовательские ключи для шифрования (generate RSA-Key...)
            this.keyPair = keyPair;
        } else {
            throw new IllegalArgumentException("ID_SELLER & ID_BUYER ?");
        }


        Gson gson = new Gson();
        /////////////////////////////////////////////////// Профиль (0.2)
        // При покупке вытягиваю информацию-покупателя об юзере
        long timestamp = new Date().getTime();
        String    TEST = gson.toJson(new TransactionData(0, new String[]{ID_SELLER, ID_BUYER}, additionalInfo)); // Есть пользовательские данные которые нужно зашифровать, передать и проверить...
        String strHash = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265

        LOGGER.info(timestamp);
        LOGGER.info(TEST);
        LOGGER.info(strHash);


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
        LOGGER.info("(String) type:        " + TYPE_TRANSACTION);
        LOGGER.info("  (Long) timestamp:   " + timestamp);
        LOGGER.info("(digest) DATA:        " + Hex.toHexString(digestDATA)); // System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        LOGGER.info("(digest) hash:        " + Hex.toHexString(digestHASH));
        LOGGER.info("   (RSA) PRIVATE-KEY: " + keyPair.getPrivate());
//        byte[] digestSIGNATURE = msg.digest(SIGNATURE);
//        System.out.println( "Singature: " + Hex.toHexString(SIGNATURE));  // System.out.println( "Singature: " + new BASE64Encoder().encode( SIGNATURE ) );
//        System.out.println( "Singature: " + (Hex.toHexString(digestSIGNATURE)));
        SIGNATURE_TRANSACTION = gson.toJson(new TransactionSignature(
                Hex.toHexString(SIGNATURE), // (String) Hex
                keyPair.readPublic())       // RSA Public Key
        );
        LOGGER.info("Singature (TRANSACTION): " + SIGNATURE_TRANSACTION);


        this.hash = Hex.toHexString(digestHASH);
        /////////////////////////////////////////////////// Клиент (0.2)
        // Создаем транзакцию типа: 'CONTRACT'
        ContractTransaction transaction = new ContractTransaction(
                Hex.toHexString(digestDATA),                                                // (JSON) SHA-256
                new TransactionSignature(Hex.toHexString(SIGNATURE), keyPair.readPublic()), // Class
                timestamp,                                                                  // Long
                Hex.toHexString(digestHASH)                                                 // (type + <random> + timestamp) SHA-256
        );
        Chain       contract = new Chain(TYPE_TRANSACTION, transaction);
        CONTRACT_TRANSACTION = gson.toJson(contract);

        if (CONTRACT_TRANSACTION!=null){
            this.CONTRACT_TRANSACTION = CONTRACT_TRANSACTION;
            LOGGER.info("Contract (TRANSACTION): " + this.CONTRACT_TRANSACTION);
        } else {
            throw new ExceptionInInitializerError("Can't create CONTRACT_TRANSACTION ?");
        }


//        System.out.println();
//        System.out.println();
//        System.out.println();
//        /////////////////////////////////////////////////// Клиент (сторона-2)
//        Signature signature2 = Signature.getInstance("RSA", "BC");
//        signature2.initVerify( keyPair.getPublic() );  // 6. Расшифровываем подпись-владельца на основе клиентского публичного ключа
//        signature2.update(digestDATA);
//
//        System.out.println( "digestDATA: " + Hex.toHexString(digestDATA) ); // System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
//        System.out.println( "PUBLIC-KEY: " + keyPair.getPublic() );
//        System.out.println( "Verify by Singature: " + signature2.verify( SIGNATURE ) );
    }

    public Response postTransaction()
            throws IOException
    {
        return postTransaction(URL_PUSH_TRANSACTION, CONTRACT_TRANSACTION);
    }

    public String getHash() {
        return hash;
    }

    public FileKeyGenerator getKeyPair() {
        return keyPair;
    }

    // Post the transaction.
    public Response postTransaction(String url, String jsonBody)
            throws IOException
    {
        OkHttpClient client = createHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody    body = RequestBody.create(mediaType, jsonBody);
        Request     request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return client.newCall(request).execute();
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

    private OkHttpClient createHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final X509TrustManager x509TrustManager = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            };
            final TrustManager[] trustAllCerts = new TrustManager[]{ x509TrustManager };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, x509TrustManager)
                    .hostnameVerifier(new HostnameVerifier() {
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    })
                    .build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
