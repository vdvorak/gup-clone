package ua.com.itproekt.gup.bank_api;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.bank_api.entity.BankUser;
import ua.com.itproekt.gup.bank_api.entity.FinanceInfo;
import ua.com.itproekt.gup.bank_api.entity.InternalTransaction;
import ua.com.itproekt.gup.bank_api.liqpay.LiqPay;
import ua.com.itproekt.gup.bank_api.repository.*;
import ua.com.itproekt.gup.bank_api.services.BankService;
import ua.com.itproekt.gup.bank_api.services.Pair;
import ua.com.itproekt.gup.util.LogUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankSession {
    private static final Logger LOG = Logger.getLogger(BankSession.class);


    /*
     * The URL of the bank server
     */
    private final String URL = "http://62.80.167.164:8087/";
//    private final String URL = "http://localhost:8081/";



    private BalanceRepository balanceRepository = new BalanceRepository(this);
    private ExternalTransactionRepository externalTransactionRepository = new ExternalTransactionRepository(this);
    private InternalTransactionRepository internalTransactionRepository = new InternalTransactionRepository(this);
    private UserRepository userRepository = new UserRepository(this);
    private BonusRepository bonusRepository = new BonusRepository(this);
    private FinanceInfoRepository financeInfoRepository = new FinanceInfoRepository(this);


    public String getUrl() {
        return URL;
    }

    public String getAllBalancesJson() {
        return balanceRepository.getAllBalancesJson();
    }

    public Integer getUserBalance(String id) {
        return balanceRepository.getUserBalance(id);
    }

    public String getUserBalanceJsonRecordById(String id) {
        return balanceRepository.getUserBalanceJsonRecordById(id);
    }

    public void editBalanceAmountByUserId(long amount, String sender, String receiver, int transType, String status) {
        balanceRepository.editBalanceAmountByUserId(amount, sender, receiver, transType, status);
    }

    public void editBalanceTypeEntityOfUser(String user, Integer typeEntity) {
        balanceRepository.editBalanceTypeEntityOfUser(user, typeEntity);
    }

    public String getAllExternalTransactionsJson() {
        return externalTransactionRepository.getAllExternalTransactionsJson();
    }

    public String getExternalTransactionJsonRecordById(Long id) {
        return externalTransactionRepository.getExternalTransactionJsonRecordById(id);
    }

    public String getExternalTransactionsByUserId(String id) {
        return externalTransactionRepository.getExternalTransactionsJsonByUserId(id);
    }

    public String getAllInternalTransactionsJson() {
        return internalTransactionRepository.getAllInternalTransactionsJson();
    }

    public String getInternalTransactionJsonRecordById(Long id) {
        return internalTransactionRepository.getInternalTransactionJsonRecordById(id);
    }

    public String getAllSenderInternalTransactionsJson(String id) {
        return internalTransactionRepository.getAllSenderTransactionsJson(id);
    }

    public String getInternalTransactionsJsonByUserId(String id) {
        return internalTransactionRepository.getInternalTransactionsJsonByUserId(id);
    }

    public List<InternalTransaction> getAllRecipientInternalTransactionsJson(String id) {
        Gson gson = new Gson();
        String jsonInternalTransactions = internalTransactionRepository.getAllRecipientTransactionsJson(id);
        return gson.fromJson(jsonInternalTransactions, new TypeToken<List<InternalTransaction>>() {
        }.getType());
    }

    public boolean isInternalTransactionExist(String sender, String recipient) {
        return internalTransactionRepository.isTransactionExist(sender, recipient);
    }

    public void transferBetweenUsers(int code, String sender, String receiver, long amount, int transactionType, String status) {
        internalTransactionRepository.transferBetweenUsers(code, sender, receiver, amount, transactionType, status);
    }

    public void investInOrganization(int code, String sender, long amount, int transactionType, String status) {
        internalTransactionRepository.investInOrganization(code, sender, amount, transactionType, status);
    }

    public void saveUser(String login, String userPassword, String role) {
        userRepository.saveUser(login, userPassword, role);
    }

    public void updateUser(BankUser bankUser) throws NullPointerException {
        Long id = bankUser.getId();
        String login = bankUser.getLogin();
        String password = bankUser.getPassword();
        String role = bankUser.getRole();
        String email = bankUser.getEmail();
        String firstName = bankUser.getFirstName();
        String lastName = bankUser.getLastName();
        String phone = bankUser.getPhone();
        userRepository.updateUser(id, login, password, role, email, firstName, lastName, phone);
    }

    public void deleteUser(String login) {
        userRepository.deleteUser(login);
    }

    public String getAllUsersJson() {
        return userRepository.getAllUsersJson();
    }

    public BankUser getUserByLogin(String login) {
        return BankService.getUserFromJsonString(userRepository.getUserJson(login));
    }


    /**
     * Here we generate two attributes for the client-side: data and signature as pair of two strings.
     * More details for parameters you can
     * read here @see <a href="https://www.liqpay.com/en/doc/checkout">https://www.liqpay.com/en/doc/checkout</a>
     *
     * @param id - the user ID which make request to fill up his account balance.
     * @param amount - the amount of the fill up request.
     * @return - the pair of the data and signature attributes.
     */
    public Map<String, String> liqPayGenerateParamForHtmlForm(String id, Long amount) {
        HashMap params = new HashMap();
        params.put("version", "3");
        params.put("amount", amount);
        params.put("currency", "UAH");
        try {
            params.put("description", new String("Пополнение баланса".getBytes("UTF-8"), "cp1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("order_id", BankService.getRandomPassword() + id);

        /**
         * Here we put url on which bank will send request (callback).
         */
        params.put("server_url", URL + "/callback");

        params.put("public_key", "i74044182839");

        params.put("sandbox", "1");
        return new LiqPay("i74044182839", "psMQcCR32o4TZRZTKI0Yoe4UDNyFHNFHf76Pyedr").generateData(params);
    }

    public void accountantRequest(String accountantLogin, String userId, Long amount, String comment) {
        internalTransactionRepository.accountantRequest(accountantLogin, userId, amount, comment);
    }

    public void adminConfirm(Long internalTransactionId, String adminLogin) {
        internalTransactionRepository.adminConfirm(internalTransactionId, adminLogin);
    }

    public void createBalanceRecord(String userId, Integer typeEntity) {
        balanceRepository.createBalanceRecord(userId, typeEntity);
    }

    public String getAllPendingTransactionsJson() {
        return internalTransactionRepository.getAllPendingTransactionsJson();
    }

    public String getAllAccountantPendingTransactionsJson(String login) {
        return internalTransactionRepository.getAllAccountantPendingTransactionsJson(login);
    }

    public void adminReject(Long internalTransactionId, String adminLogin, String comment) {
        internalTransactionRepository.adminReject(internalTransactionId, adminLogin, comment);
    }

    public void accountantCancelRequest(Long internalTransactionId) {
        internalTransactionRepository.accountantCancelRequest(internalTransactionId);
    }


    public String getFinanceInfo(String userId){
        return financeInfoRepository.getFinancialInfo(userId);
    }

    public String getBonusByUserId(String userId) {
        return bonusRepository.getBonusByUserId(userId);
    }


    public String getAllPendingTransactions(String userId, String byType) {
        return bonusRepository.getAllPendingTransactions(userId, byType);
    }


    public String addBonusByUserId(String userId, String inviteCode) {
        return bonusRepository.addBonusByUserId(userId, inviteCode);
    }


    public String buyByBonusAccount(String userId, int transType, int cost, String offerId) {
        return bonusRepository.buyByBonusAccount(userId, transType, cost, offerId);
    }


    public List<Pair<String, Long>> projectPayback(String projectId) {
        String jsonResponse = internalTransactionRepository.projectPayback(projectId);
        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(jsonResponse);
        } catch (ParseException e) {
            LOG.error("EXCEPTION IN projectPayback method - could not parse" + LogUtil.getExceptionStackTrace(e));
        }

        JSONArray response = (JSONArray) obj;
        ArrayList<Pair<String, Long>> result = new ArrayList<>();
        for (Object pairBeforeParse : response) {
            JSONObject jsonObj = (JSONObject) pairBeforeParse;
            String key = (String) jsonObj.get("key");
            Long value = (Long) jsonObj.get("value");
            Pair<String, Long> pair = new Pair<>(key, value);
            result.add(pair);
        }
        return result;
    }

}
