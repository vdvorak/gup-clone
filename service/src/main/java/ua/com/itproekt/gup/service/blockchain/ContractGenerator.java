package ua.com.itproekt.gup.service.blockchain;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import ua.com.itproekt.gup.model.order.blockchain.contract.Contract;
import ua.com.itproekt.gup.model.order.blockchain.contract.Transaction;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.model.order.blockchain.contract.TransactionSignature;
import ua.com.itproekt.gup.util.FileKeyGeneratorUtil;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;
import javax.net.ssl.*;


public class ContractGenerator {

    protected final static Logger LOGGER = Logger.getLogger(ContractGenerator.class);
    private FileKeyGeneratorUtil KEY_BUYER;
    private String jsonTransaction;
    private String hash;

    public ContractGenerator(String type, String ID_SELLER, String ID_BUYER, String additionalInfo)
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
        Security.addProvider(new BouncyCastleProvider());

        LOGGER.info("type="+type+"; ID_SELLER="+ID_SELLER+"; ID_BUYER="+ID_BUYER+"; additionalInfo=\""+additionalInfo+"\";");
        long timestamp = new Date().getTime();
        KEY_BUYER = new FileKeyGeneratorUtil();

        // Digest the mutation twice
        byte[]      byteType = type.getBytes("UTF-8");
        byte[] byteTimestamp = String.valueOf(timestamp).getBytes("UTF-8");
        byte[]    byteSeller = ID_SELLER.getBytes("UTF-8");
        byte[]     byteBuyer = ID_BUYER.getBytes("UTF-8");

        MessageDigest      msg = MessageDigest.getInstance("SHA-256");
        byte[]      digestType = msg.digest(byteType);
        byte[] digestTimestamp = msg.digest(byteTimestamp);
        byte[]    digestSeller = msg.digest(byteSeller);
        byte[]     digestBuyer = msg.digest(byteBuyer);

        ////////////////////////////////////////////////////////////////////////
        // Sign the mutation with the private key
        Signature signature = Signature.getInstance("RSA", "BC");
        signature.initSign( KEY_BUYER.getPrivateKey(), new FixedRand() );
        signature.update(digestType);
        signature.update(digestTimestamp);
        signature.update(digestSeller);
        signature.update(digestBuyer);
        final byte[]   sign = signature.sign();
//        System.out.println("SIGN     " + Hex.toHexString(sign));

        ////////////////////////////////////////////////////////////////////////
        byte[] digestRandom = msg.digest(hashGenerator().getBytes("UTF-8"));
        String       random = Hex.toHexString(digestRandom);
//        System.err.println( "random: " + random ); // da9b55cad0983e572bc24dab1be99caa907d6fabbe9a390ff14c66dedcb8dca0 = SHA-265

        String    strHash = type + random + timestamp; //  type + <SHA-265> + timestamp = SHA-265
        byte[] digestHash = msg.digest(strHash.getBytes("UTF-8"));
        hash = Hex.toHexString(digestHash);
//        System.err.println( "hash: " + hash ); // 2ed5d498ce265ebae7a176ea05f23b9f293a6fbe6c23ca7f3392f2803f7bad86 == type + <SHA-265> + timestamp = SHA-265
        LOGGER.info("hash=\""+hash+"\"");

        // Create the JSON representation of the transaction to post
        Transaction transaction = new Transaction(new TransactionSignature(Hex.toHexString(sign), KEY_BUYER.getPublicKey()),
                timestamp,
                new TransactionData(0,
                        new String[]{Hex.toHexString(digestSeller), Hex.toHexString(digestBuyer)},
                        additionalInfo),
                hash);

        Contract contract = new Contract(transaction, type);
        Gson gson = new Gson();
        jsonTransaction = gson.toJson(contract);
        LOGGER.info(jsonTransaction);
    }

    public String getPublicKey()
            throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, IOException {
        return KEY_BUYER.getPublicKey();
    }

    public String getTransaction(){
        return jsonTransaction;
    }

    public String getHashTransaction(){
        return hash;
    }

    public Response postTransaction(String URL)
            throws IOException {
        // Post the transaction.
        OkHttpClient client = createHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody    body = RequestBody.create(mediaType, jsonTransaction);
        Request     request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        return client.newCall(request).execute();
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
