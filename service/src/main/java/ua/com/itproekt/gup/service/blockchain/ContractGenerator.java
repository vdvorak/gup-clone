package ua.com.itproekt.gup.service.blockchain;

import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

//import org.springframework.http.*;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.client.RestTemplate;

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
import java.util.Random;
import javax.net.ssl.*;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import java.net.URI;


public class ContractGenerator {

    protected final static Logger LOGGER = Logger.getLogger(ContractGenerator.class);
    private String REST_URL;
//    private RestTemplate restTemplate;

    public ContractGenerator(String REST_URL){
        this.REST_URL = REST_URL;
    }

    public Response contractPost(String type, String USER_1, String USER_2, String FILE_PUBLIC_KEY, String additionalInfo)
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
        LOGGER.info("type="+type+"; USER_1="+USER_1+"; USER_2="+USER_2+"; additionalInfo=\""+additionalInfo+"\";");
        Security.addProvider(new BouncyCastleProvider());

        long timestamp = new Date().getTime();

        // Digest the mutation twice
        byte[] byteType = type.getBytes("UTF-8");
        byte[] byteTimestamp = String.valueOf(timestamp).getBytes("UTF-8");
        byte[] member1 = USER_1.getBytes("UTF-8");
        byte[] member2 = USER_2.getBytes("UTF-8");

        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[]    digestType = msg.digest(byteType);
        byte[]    digestTimestamp = msg.digest(byteTimestamp);
        byte[]    digest1 = msg.digest(member1);
        byte[]    digest2 = msg.digest(member2);

        ////////////////////////////////////////////////////////////////////////
        KeyPair           key = FileKeyGeneratorUtil.generateRSAKey();
        PrivateKey privateKey = key.getPrivate();

        String publicKey = FILE_PUBLIC_KEY; //String publicKey = new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
//        System.out.println("PUBLIC   " + publicKey);

        // Sign the mutation with the private key
        Signature signature = Signature.getInstance("RSA", "BC");
        signature.initSign( privateKey, new FixedRand() );
        signature.update(digestType);
        signature.update(digestTimestamp);
        signature.update(digest1);
        signature.update(digest2);
        final byte[]   sign = signature.sign();
//        System.out.println("SIGN     " + Hex.toHexString(sign));

        ////////////////////////////////////////////////////////////////////////
        byte[] digestRandom = msg.digest(hashGenerator().getBytes("UTF-8"));
        String       random = Hex.toHexString(digestRandom);
//        System.err.println( "random: " + random ); // da9b55cad0983e572bc24dab1be99caa907d6fabbe9a390ff14c66dedcb8dca0 = SHA-265

        String    strHash = type + random + timestamp; //  type + <SHA-265> + timestamp = SHA-265
        byte[] digestHash = msg.digest(strHash.getBytes("UTF-8"));
        String       hash = Hex.toHexString(digestHash);
//        System.err.println( "hash: " + hash ); // 2ed5d498ce265ebae7a176ea05f23b9f293a6fbe6c23ca7f3392f2803f7bad86 == type + <SHA-265> + timestamp = SHA-265
        LOGGER.info("hash=\""+hash+"\"");

        // Create the JSON representation of the transaction to post
        Transaction transaction = new Transaction(new TransactionDataSignature(Hex.toHexString(sign), publicKey),
                timestamp,
                new TransactionData(0,
                        new String[]{Hex.toHexString(digest1), Hex.toHexString(digest2)},
                        additionalInfo),
                hash);

        Contract contract = new Contract(transaction, type);
        Gson gson = new Gson();
        String jsonContract = gson.toJson(contract);
        LOGGER.info(jsonContract);



        // Post the transaction.
        okhttp3.OkHttpClient client = createHttpClient();
        MediaType         mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody    body = okhttp3.RequestBody.create(mediaType, jsonContract);
        okhttp3.Request     request = new okhttp3.Request.Builder()
                .url(REST_URL)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

////    public Response contractPost(String type, String USER_1, String USER_2, String FILE_PUBLIC_KEY, String additionalInfo)
//    public String contractPost(String type, String USER_1, String USER_2, String FILE_PUBLIC_KEY, String additionalInfo)
//            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
//        LOGGER.info("type="+type+"; USER_1="+USER_1+"; USER_2="+USER_2+"; additionalInfo=\""+additionalInfo+"\";");
//        Security.addProvider(new BouncyCastleProvider());
//
//        long timestamp = new Date().getTime();
//
//        // Digest the mutation twice
//        byte[] byteType = type.getBytes("UTF-8");
//        byte[] byteTimestamp = String.valueOf(timestamp).getBytes("UTF-8");
//        byte[] member1 = USER_1.getBytes("UTF-8");
//        byte[] member2 = USER_2.getBytes("UTF-8");
//
//        MessageDigest msg = MessageDigest.getInstance("SHA-256");
//        byte[]    digestType = msg.digest(byteType);
//        byte[]    digestTimestamp = msg.digest(byteTimestamp);
//        byte[]    digest1 = msg.digest(member1);
//        byte[]    digest2 = msg.digest(member2);
//
//        ////////////////////////////////////////////////////////////////////////
//        KeyPair           key = FileKeyGeneratorUtil.generateRSAKey();
//        PrivateKey privateKey = key.getPrivate();
//
//        String publicKey = new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
////        System.out.println("PUBLIC   " + publicKey);
//
//        // Sign the mutation with the private key
//        Signature signature = Signature.getInstance("RSA", "BC");
//        signature.initSign( privateKey, new FixedRand() );
//        signature.update(digestType);
//        signature.update(digestTimestamp);
//        signature.update(digest1);
//        signature.update(digest2);
//        final byte[]   sign = signature.sign();
////        System.out.println("SIGN     " + Hex.toHexString(sign));
//
//        ////////////////////////////////////////////////////////////////////////
//        byte[] digestRandom = msg.digest(hashGenerator().getBytes("UTF-8"));
//        String       random = Hex.toHexString(digestRandom);
////        System.err.println( "random: " + random ); // da9b55cad0983e572bc24dab1be99caa907d6fabbe9a390ff14c66dedcb8dca0 = SHA-265
//
//        String    strHash = type + random + timestamp; //  type + <SHA-265> + timestamp = SHA-265
//        byte[] digestHash = msg.digest(strHash.getBytes("UTF-8"));
//        String       hash = Hex.toHexString(digestHash);
////        System.err.println( "hash: " + hash ); // 2ed5d498ce265ebae7a176ea05f23b9f293a6fbe6c23ca7f3392f2803f7bad86 == type + <SHA-265> + timestamp = SHA-265
//        LOGGER.info("hash=\""+hash+"\"");
//
//        // Create the JSON representation of the transaction to post
//        Transaction transaction = new Transaction(new TransactionDataSignature(Hex.toHexString(sign), publicKey),
//                timestamp,
//                new TransactionData(0,
//                        new String[]{Hex.toHexString(digest1), Hex.toHexString(digest2)},
//                        additionalInfo),
//                hash);
//
//        Contract contract = new Contract(transaction, type);
//        Gson gson = new Gson();
//        String jsonContract = gson.toJson(contract);
//        LOGGER.info(jsonContract);
//
//
//
//        // Post the transaction.
////        okhttp3.OkHttpClient client = createHttpClient();
////        MediaType mediaType = MediaType.parse("application/json");
////        okhttp3.RequestBody    body = okhttp3.RequestBody.create(mediaType, jsonContract);
////        okhttp3.Request     request = new okhttp3.Request.Builder()
////                .url(REST_URL)
////                .post(body)
////                .build();
////
////        return client.newCall(request).execute();
//
////        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory( HttpClients.createDefault() );
////        restTemplate = new RestTemplate( requestFactory );
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        restTemplate.exchange(REST_URL, HttpMethod.POST, new HttpEntity<>(jsonContract, headers), String.class);
//
//        createHttpClient2(jsonContract);
//
//        return "ok";
//    }
//
//    public String createHttpClient2(String jsonContract) {
//        DefaultHttpClient client = new DefaultHttpClient();
//        try {
//            URI uri = new URIBuilder()
//                    .setScheme("http")
//                    .setHost("gup.com.ua:3000/bc/push-transaction")
////                    .setParameter("jsonContract", jsonContract)
//                    .build();
//            HttpPost httpPost = new HttpPost(uri);
//            HttpResponse response = client.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                String result = EntityUtils.toString(entity);
//                EntityUtils.consume(entity);
//                return result;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    private okhttp3.OkHttpClient createHttpClient() {
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
            okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
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
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i=0; i<10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
