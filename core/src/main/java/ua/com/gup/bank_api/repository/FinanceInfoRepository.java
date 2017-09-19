package ua.com.gup.bank_api.repository;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.postgresql.util.Base64;
import ua.com.gup.bank_api.SecurityService;
import ua.com.gup.bank_api.BankSession;

import java.net.URI;

public class FinanceInfoRepository {
    private BankSession session;

    public BankSession getSession() {
        return session;
    }

    public void setSession(BankSession session) {
        this.session = session;
    }

    public FinanceInfoRepository(BankSession session) {

        this.session = session;
    }


    public String getFinancialInfo(String userId){
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            String host;
            if(session.getUrl().contains("http")){
                host = session.getUrl().substring(7);
            }else host = session.getUrl();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath("/financeInfo/getFinanceInfo")
                    .setParameter("password", Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random()))))
                    .setParameter("id", Base64.encodeBytes(SecurityService.encrypt(userId)))
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
