package ua.com.itproekt.gup.service.blockchain.money_transfer;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.model.order.blockchain.Transaction;
import ua.com.itproekt.gup.model.order.blockchain.TransactionSignature;
import ua.com.itproekt.gup.model.order.blockchain.money_transfer.MoneyTransferTransaction;
import ua.com.itproekt.gup.model.order.blockchain.money_transfer.TransactionOutput;
import ua.com.itproekt.gup.service.blockchain.TransactionService;
import ua.com.itproekt.gup.util.FileKeyGenerator;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Random;

import ua.com.itproekt.gup.model.order.blockchain.Chain;
import ua.com.itproekt.gup.model.order.blockchain.money_transfer.TransactionData;


public class MoneyTransferTransactionService implements TransactionService {

    protected final static Logger            LOGGER = Logger.getLogger(MoneyTransferTransactionService.class);
    public static final String URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction
    public static final String     TYPE_TRANSACTION = "MONEY_TRANSFER"; // тип транзакции
    private String       MONEY_TRANSFER_TRANSACTION;
    private String                             hash;
    private FileKeyGenerator                keyPair;
    private Transaction                 transaction;

    public final static String USER_CARD_DETAILS = "587ca08e4c8e89327948309e"; // ID_SELLER карта продавца
    public final static long              AMOUNT = 1000000;                    // (ID_SELLER) сумма на балансе продавца
    public final static long BANK_TRANSACTION_ID = 1;

    public MoneyTransferTransactionService(String ID_SELLER)
            throws NullPointerException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException
    {
        Security.addProvider(new BouncyCastleProvider());

        FileKeyGenerator  keyPairSeller1 = new FileKeyGenerator(); // ID_SELLER... ???
        TransactionOutput  sellerOutput1 = new TransactionOutput(AMOUNT, keyPairSeller1.readPublic(), 0);
        TransactionOutput[]      OUTPUTS = {sellerOutput1};
        keyPair                          = new FileKeyGenerator();   // 1. Создаем ключи магазина для шифрования (generate RSA-Key...)   ???   вытягиваем ключ из файла...

        byte[] SIGNATURE;
        /////////////////////////////////////////////////// Клиент (0.1)
        // (сторона-1)
        Signature signature = Signature.getInstance("RSA", "BC");
        signature.initSign( keyPair.getPrivate() );                  // 4. Создаем подпись-магазина на основе приватного ключа магазина
        SIGNATURE = signature.sign();                                // 5. Подписываем эти данные-магазина...


        Gson gson = new Gson();
        /////////////////////////////////////////////////// Профиль (0.2)
        // При покупке вытягиваю информацию-покупателя об юзере
        long       timestamp = new Date().getTime();
        String          TEST = gson.toJson(new TransactionData(USER_CARD_DETAILS, AMOUNT, keyPair.readPublic(), Hex.toHexString(SIGNATURE), BANK_TRANSACTION_ID)); // Есть пользовательские данные которые нужно зашифровать, передать и проверить...
        String       strHash = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265
        String strHashInput1 = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265
        String strHashInput2 = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265
        String strHashInput3 = TYPE_TRANSACTION + hashGenerator() + timestamp; // (type + <random> + timestamp) = SHA-265

        LOGGER.info(timestamp);
        LOGGER.info(TEST);
        LOGGER.info(strHash);
        LOGGER.info(strHashInput1 + " | " + strHashInput2 + " | " + strHashInput3);


        byte[] DATA_TRANSACTION, HASH_TRANSACTION, HASH_INPUT1, HASH_INPUT2, HASH_INPUT3;
        String SIGNATURE_TRANSACTION, MONEY_TRANSFER_TRANSACTION;
        String[] HASH_INPUTS;
        /////////////////////////////////////////////////// Клиент (0.1)
        // (сторона-1)
        // Делаем запрос в банк - узнаем счет-покупателя и баланс на этом счете покупателя
        // Дальше, если покупатель платеже-способен - тогда шифруем данные клиента
        MessageDigest msg = MessageDigest.getInstance("SHA-256"); // 2. Указываем тип шифрования ( формат: SHA-256 )
        DATA_TRANSACTION  = TEST.getBytes("UTF8");                // 3. Переводим пользовательские данные в байт-код
        byte[] digestDATA = msg.digest(DATA_TRANSACTION);
        HASH_TRANSACTION  = strHash.getBytes("UTF-8");
        byte[] digestHASH = msg.digest(HASH_TRANSACTION);
        HASH_INPUT1  = strHashInput1.getBytes("UTF-8");
        byte[] digestHASH_INPUT1 = msg.digest(HASH_INPUT1);
        HASH_INPUT2  = strHashInput2.getBytes("UTF-8");
        byte[] digestHASH_INPUT2 = msg.digest(HASH_INPUT2);
        HASH_INPUT3  = strHashInput3.getBytes("UTF-8");
        byte[] digestHASH_INPUT3 = msg.digest(HASH_INPUT3);
        HASH_INPUTS = new String[] {Hex.toHexString(digestHASH_INPUT1), Hex.toHexString(digestHASH_INPUT2), Hex.toHexString(digestHASH_INPUT3)};
        LOGGER.info("(String) type:            " + TYPE_TRANSACTION);
        LOGGER.info("  (Long) timestamp:       " + timestamp);
        LOGGER.info("(digest) DATA:            " + Hex.toHexString(digestDATA)); // System.out.println( "Data: " + new BASE64Encoder().encode( DATA ) );
        LOGGER.info("(digest) hash:            " + Hex.toHexString(digestHASH));
        LOGGER.info("(RSA) STORE PRIVATE-KEY:  " + keyPair.getPrivate());
        LOGGER.info("(RSA) SELLER PRIVATE-KEY: " + keyPairSeller1.getPrivate());
        LOGGER.info("INPUTS:                   " + HASH_INPUTS);
        SIGNATURE_TRANSACTION = gson.toJson(new TransactionSignature(
                Hex.toHexString(SIGNATURE), // (String) Hex
                keyPair.readPublic())       // RSA Public Key
        );
        LOGGER.info("Singature (TRANSACTION): " + SIGNATURE_TRANSACTION);


        this.hash = Hex.toHexString(digestHASH);
        /////////////////////////////////////////////////// Клиент (0.2)
        // Создаем транзакцию типа: 'MONEY_TRANSFER'
        MoneyTransferTransaction transaction = new MoneyTransferTransaction(
                Hex.toHexString(digestDATA),                                                // (JSON) SHA-256
                new TransactionSignature(Hex.toHexString(SIGNATURE), keyPair.readPublic()), // Class
                timestamp,                                                                  // Long
                Hex.toHexString(digestHASH),                                                // (type + <random> + timestamp) SHA-256
                HASH_INPUTS,                                                                // {(type + <random> + timestamp) SHA-256, (type + <random> + timestamp) SHA-256, ...}
                OUTPUTS                                                                     // {Class, Class, ...}
        );
        this.transaction           = transaction;
        Chain        moneyTransfer = new Chain(TYPE_TRANSACTION, transaction);
        MONEY_TRANSFER_TRANSACTION = gson.toJson(moneyTransfer);

        if (MONEY_TRANSFER_TRANSACTION!=null){
            this.MONEY_TRANSFER_TRANSACTION = MONEY_TRANSFER_TRANSACTION;
            LOGGER.info("MONEY_TRANSFER (TRANSACTION): " + this.MONEY_TRANSFER_TRANSACTION);
        } else {
            throw new ExceptionInInitializerError("Can't create MONEY_TRANSFER_TRANSACTION ?");
        }
    }

    @Override
    public Response postTransaction()
            throws IOException {
        return postTransaction(URL_PUSH_TRANSACTION, MONEY_TRANSFER_TRANSACTION);
    }

    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public FileKeyGenerator getKeyPair() {
        return keyPair;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
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
