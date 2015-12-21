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
public class InternalTransactionRepository {

    private BankSession session;

    public BankSession getSession() {
        return session;
    }

    public void setSession(BankSession session) {
        this.session = session;
    }

    public InternalTransactionRepository(BankSession session) {
        this.session = session;
    }

    public String getAllInternalTransactionsJson(){
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getAllInternalTransactionsJson")
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

    public String getAllPendingTransactionsJson(){
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getAllPendingTransactionsJson")
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

    public String getInternalTransactionJsonRecordById(Long id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getInternalTransactionJsonRecordById")
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

    public String getAllAccountantPendingTransactionsJson(String login) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getAllAccountantGetPendingTransactionsJson")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("login", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(login))))
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



    public String getAllSenderTransactionsJson(String id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getAllSenderTransactionsJson")
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

    public String getInternalTransactionsJsonByUserId(String id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getInternalTransactionsJsonByUserId")
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

    public String getAllRecipientTransactionsJson(String id) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/getAllRecipientTransactionsJson")
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

    public boolean isTransactionExist(String sender, String recipient) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/isTransactionExist")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("sender", Base64.encodeBytes(SecurityService.encrypt(sender)))
                    .setParameter("receiver", Base64.encodeBytes(SecurityService.encrypt(recipient)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return new Boolean(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void transferBetweenUsers(int code, String sender, String receiver, long amount, int transactionType, String status) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/transactionBetweenUsers")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("code", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(code))))
                    .setParameter("amount", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(amount))))
                    .setParameter("sender", Base64.encodeBytes(SecurityService.encrypt(sender)))
                    .setParameter("receiver", Base64.encodeBytes(SecurityService.encrypt(receiver)))
                    .setParameter("transType", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(transactionType))))
                    .setParameter("status", Base64.encodeBytes(SecurityService.encrypt(status)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void investInOrganization(int code, String sender, long amount, int transactionType, String status) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/investInOrganization")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("code", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(code))))
                    .setParameter("amount", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(amount))))
                    .setParameter("sender", Base64.encodeBytes(SecurityService.encrypt(sender)))
                    .setParameter("transType", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(transactionType))))
                    .setParameter("status", Base64.encodeBytes(SecurityService.encrypt(status)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accountantRequest(String accountantLogin, String userId, Long amount, String comment) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/accountantRequest")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("accountantLogin", Base64.encodeBytes(SecurityService.encrypt(accountantLogin)))
                    .setParameter("amount", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(amount))))
                    .setParameter("userId", Base64.encodeBytes(SecurityService.encrypt(userId)))
                    .setParameter("comment", Base64.encodeBytes(SecurityService.encrypt(comment)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adminConfirm(Long internalTransactionId, String adminLogin) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/adminConfirm")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("internalTransactionId", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(internalTransactionId))))
                    .setParameter("adminLogin", Base64.encodeBytes(SecurityService.encrypt(adminLogin)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adminReject(Long internalTransactionId, String adminLogin, String comment) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/adminReject")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("internalTransactionId", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(internalTransactionId))))
                    .setParameter("adminLogin", Base64.encodeBytes(SecurityService.encrypt(adminLogin)))
                    .setParameter("comment", Base64.encodeBytes(SecurityService.encrypt(comment)))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accountantCancelRequest(Long internalTransactionId) {
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/internalTransactionRest/accountantCancelRequest")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("internalTransactionId", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(internalTransactionId))))
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
