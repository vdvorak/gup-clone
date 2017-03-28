package ua.com.itproekt.gup.service.blockchain;

import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.service.blockchain.contract.Contract;
import ua.com.itproekt.gup.service.blockchain.contract.Transaction;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionDataSignature;
import ua.com.itproekt.gup.util.FileKeyGeneratorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;


public class ContractGenerator {

    private String REST_URL;

    public ContractGenerator(String REST_URL){
        this.REST_URL = REST_URL;
    }

    public Response contractPost(String type, String USER_1, String USER_2, String FILE_PUBLIC_KEY, String additionalInfo)
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
        Security.addProvider(new BouncyCastleProvider());

        // Digest the mutation twice
        byte[] member1 = USER_1.getBytes("UTF-8");
        byte[] member2 = USER_2.getBytes("UTF-8");

        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[]    digest1 = msg.digest(member1);
        byte[]    digest2 = msg.digest(member2);

        ////////////////////////////////////////////////////////////////////////
        KeyPair           key = FileKeyGeneratorUtil.generateRSAKey();
        PrivateKey privateKey = key.getPrivate();

        String publicKey = new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
//        System.out.println("PUBLIC   " + publicKey);

        // Sign the mutation with the private key
        Signature signature = Signature.getInstance("RSA", "BC");
        signature.initSign( privateKey, new FixedRand() );
        signature.update(digest1);
        signature.update(digest2);
        final byte[]   sign = signature.sign();
//        System.out.println("SIGN     " + Hex.toHexString(sign));

        ////////////////////////////////////////////////////////////////////////
        System.out.println();

        // Create the JSON representation of the transaction to post
        Transaction transaction = new Transaction(new TransactionDataSignature(Hex.toHexString(sign), publicKey),
                new Date().getTime(),
                new TransactionData(0,
                        new String[]{Hex.toHexString(digest1), Hex.toHexString(digest2)},
                        additionalInfo),
                "431a8a1af9cb37a5c3979e7e4b64ebf570809be4246a8c4a5f6a5e97ad0df36e");
        Contract contract = new Contract(transaction, type);

        Gson gson = new Gson();
        String jsonContract = gson.toJson(contract);

        System.out.println( jsonContract );


        // Post the transaction.
        OkHttpClient client = createHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody    body = RequestBody.create(mediaType, jsonContract);
        Request     request = new Request.Builder()
                .url(REST_URL)
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
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

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
