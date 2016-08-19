package ua.com.itproekt.gup.bank_api.repository;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.postgresql.util.Base64;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.bank_api.SecurityService;

import java.net.URI;

/**
 * Created by RAYANT on 22.10.2015.
 */
public class ExternalTransactionRepository {

    private BankSession session;

    public BankSession getSession() {
        return session;
    }

    public void setSession(BankSession session) {
        this.session = session;
    }

    public ExternalTransactionRepository(BankSession session) {
        this.session = session;
    }

    public String getAllExternalTransactionsJson(){
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/externalTransactionRest/getAllExternalTransactionsJson")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getExternalTransactionJsonRecordById(Long id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/externalTransactionRest/getExternalTransactionJsonRecordById")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("id", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(id))))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getExternalTransactionsJsonByUserId(String id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/externalTransactionRest/getExternalTransactionsByUserId")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("id", Base64.encodeBytes(SecurityService.encrypt(id)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
