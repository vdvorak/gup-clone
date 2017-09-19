package ua.com.gup.service.order.blockchain_test;

import okhttp3.*;
import ua.com.gup.model.order.blockchain_test.Transaction;
import ua.com.gup.service.order.blockchain_test.member.Available;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


abstract public class TransactionService implements Available {

    public static final String URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction

    abstract public Transaction getTransaction();

    @Override
    public Response confirm()
                throws IOException {
        Transaction transaction = getTransaction();
        //TODO
        return postTransaction(URL_PUSH_TRANSACTION, transaction.getData());
    }

    @Override
    public Response reject()
        throws IOException {
            Transaction transaction = getTransaction();
            //TODO
            return postTransaction(URL_PUSH_TRANSACTION, transaction.getData());
    }

    // Post the transaction.
    private Response postTransaction(String url, String jsonBody)
            throws IOException
    {
        OkHttpClient client = createHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody    body = RequestBody.create(mediaType, jsonBody);
        Request     request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return null; //return client.newCall(request).execute(); //TODO ????????????????????????????????????????????????????????????????????
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
