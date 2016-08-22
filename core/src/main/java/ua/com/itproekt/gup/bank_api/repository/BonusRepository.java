package ua.com.itproekt.gup.bank_api.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.postgresql.util.Base64;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.bank_api.SecurityService;

public class BonusRepository {
    private BankSession session;


    public BonusRepository(BankSession session) {
        this.session = session;
    }

    public String getAllPendingTransactions(String userId, String byType) {
        try {
            HttpResponse<String> jsonResponse = Unirest.post(session.getUrl() + "/bonusRest/getAllPendingTransactions")
                    .field("password", generateEncodedPassword())
                    .field("userId", generateEncodedParameter(userId))
                    .field("byType", generateEncodedParameter(byType))
                    .asString();
            return jsonResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addBonusByUserId(String userId, String inviteCode) {
        try {
            HttpResponse<String> jsonResponse = Unirest.post(session.getUrl() + "/bonusRest/addBonusByUserId")
                    .field("password", generateEncodedPassword())
                    .field("userId", generateEncodedParameter(userId))
                    .field("inviteCode", generateEncodedParameter(inviteCode))
                    .asString();
//            System.err.println("#Result of rest: " + jsonResponse.getBody());
            return jsonResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "INTERNAL REST ERROR";
    }

    public String getBonusByUserId(String userId) {
        try {
            HttpResponse<String> jsonResponse = Unirest.post(session.getUrl() + "/bonusRest/getBonusByUserId")
                    .field("password", generateEncodedPassword())
                    .field("userId", generateEncodedParameter(userId))
                    .asString();
//            System.err.println("#Result of rest: " + jsonResponse.getBody());
            return jsonResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "INTERNAL REST ERROR";
    }

    public String buyByBonusAccount(String userId, int transType, int cost, int offerId) {
        try {
            HttpResponse<String> jsonResponse = Unirest.post(session.getUrl() + "/bonusRest/buyByBonusAccount")
                    .field("password", generateEncodedPassword())
                    .field("userId", generateEncodedParameter(userId))
                    .field("transType", generateEncodedParameter(String.valueOf(transType)))
                    .field("cost", generateEncodedParameter(String.valueOf(cost)))
                    .field("offerId", generateEncodedParameter(String.valueOf(offerId)))
                    .asString();
//            System.err.println("#Result of rest: " + jsonResponse.getBody());
            return jsonResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "INTERNAL REST ERROR";
    }









    private String generateEncodedPassword() {
        return Base64.encodeBytes(SecurityService.encrypt(String.valueOf(Math.random())));
    }


    private String generateEncodedParameter(String parameter) {
        return Base64.encodeBytes(SecurityService.encrypt(String.valueOf(parameter)));
    }

}
