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
 * Created by RAYANT on 23.10.2015.
 */
public class UserRepository {

    private BankSession session;

    public BankSession getSession() {
        return session;
    }

    public void setSession(BankSession session) {
        this.session = session;
    }

    public UserRepository(BankSession session) {

        this.session = session;
    }

    public void saveUser(String login, String userPassword, String role) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/userRest/saveUser")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("login", Base64.encodeBytes(SecurityService.encrypt(login)))
                    .setParameter("userPassword", Base64.encodeBytes(SecurityService.encrypt(userPassword)))
                    .setParameter("role", Base64.encodeBytes(SecurityService.encrypt(role)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Long id, String login, String password, String role, String email, String firstName, String lastName, String phone) throws NullPointerException {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/userRest/updateUser")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("id", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(id))))
                    .setParameter("login", Base64.encodeBytes(SecurityService.encrypt(login)))
                    .setParameter("userPassword", Base64.encodeBytes(SecurityService.encrypt(password)))
                    .setParameter("role", Base64.encodeBytes(SecurityService.encrypt(role)))
                    .setParameter("email", Base64.encodeBytes(SecurityService.encrypt(email)))
                    .setParameter("firstName", Base64.encodeBytes(SecurityService.encrypt(firstName)))
                    .setParameter("lastName", Base64.encodeBytes(SecurityService.encrypt(lastName)))
                    .setParameter("phone", Base64.encodeBytes(SecurityService.encrypt(phone)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String login) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/userRest/deleteUser")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("login", Base64.encodeBytes(SecurityService.encrypt(login)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAllUsersJson() {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/userRest/getAllUsersJson")
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

    public String getUserJson(String login) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if (session.getUrl().contains("http")) {
                host = session.getUrl().substring(7);
            } else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/userRest/getUserJson")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("login", Base64.encodeBytes(SecurityService.encrypt(login)))
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
