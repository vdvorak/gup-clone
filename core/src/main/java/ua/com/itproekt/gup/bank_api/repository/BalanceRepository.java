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
 * Created by RAYANT on 20.10.2015.
 */
public class BalanceRepository {

    private BankSession session;

    public BankSession getSession() {
        return session;
    }

    public void setSession(BankSession session) {
        this.session = session;
    }

    public BalanceRepository(BankSession session) {

        this.session = session;
    }

    public String getAllBalancesJson() {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/balanceRest/getAllBalances")
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

    public Integer getUserBalance(String id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/balanceRest/getUserBalance")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("id", Base64.encodeBytes(SecurityService.encrypt(id)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                if(result.equals("")) result = "0";
                return Integer.parseInt(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserBalanceJsonRecordById(String id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/balanceRest/getJsonBalanceRecordByUserId")
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

    public void editBalanceTypeEntityOfUser(String user, Integer typeEntity) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/balanceRest/editBalanceTypeEntityOfUser")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("user", Base64.encodeBytes(SecurityService.encrypt(user)))
                    .setParameter("typeEntity", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(typeEntity))))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editBalanceAmountByUserId(long amount, String sender, String receiver, int transType, String status) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/balanceRest/editBalanceAmountByUserId")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("amount", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(amount))))
                    .setParameter("sender", Base64.encodeBytes(SecurityService.encrypt(sender)))
                    .setParameter("receiver", Base64.encodeBytes(SecurityService.encrypt(receiver)))
                    .setParameter("transType", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(transType))))
                    .setParameter("status", Base64.encodeBytes(SecurityService.encrypt(status)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createBalanceRecord(String user, Integer typeEntity) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/balanceRest/createBalanceRecord")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("user", Base64.encodeBytes(SecurityService.encrypt(user)))
                    .setParameter("typeEntity", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(typeEntity))))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
