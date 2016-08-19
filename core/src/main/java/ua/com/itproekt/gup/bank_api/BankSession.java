package ua.com.itproekt.gup.bank_api;

import ua.com.itproekt.gup.bank_api.entity.InternalTransaction;
import ua.com.itproekt.gup.bank_api.repository.*;
import ua.com.itproekt.gup.bank_api.service.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;


public class BankSession {

    private String url = "localhost:8081";
    private BalanceRepository balanceRepository = new BalanceRepository(this);
    private ExternalTransactionRepository externalTransactionRepository = new ExternalTransactionRepository(this);
    private InternalTransactionRepository internalTransactionRepository = new InternalTransactionRepository(this);
    private UserRepository userRepository = new UserRepository(this);
    private BonusRepository bonusRepository = new BonusRepository(this);

    public BankSession(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getAllRecipientInternalTransactionsJson(String id) {
        return internalTransactionRepository.getAllRecipientTransactionsJson(id);
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

    public void updateUser(Long id, String login, String password, String role, String email, String firstName, String lastName, String phone) {
        userRepository.updateUser(id, login, password, role, email, firstName, lastName, phone);
    }

    public void deleteUser(String login) {
        userRepository.deleteUser(login);
    }

    public String getAllUsersJson() {
        return userRepository.getAllUsersJson();
    }

    public String getUserJson(String login) {
        return userRepository.getUserJson(login);
    }

    public void accountantRequest(String accountantLogin, String userId, Long amount, String comment) {
        internalTransactionRepository.accountantRequest(accountantLogin, userId, amount, comment);
    }

    public void adminConfirm(Long internalTransactionId, String adminLogin) {
        internalTransactionRepository.adminConfirm(internalTransactionId, adminLogin);
    }

    public void createBalanceRecord(String user, Integer typeEntity) {
        balanceRepository.createBalanceRecord(user, typeEntity);
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


    public String getBonusByUserId(String userId) {
        return bonusRepository.getBonusByUserId(userId);
    }

    public String getAllPendingTransactions(String userId, String byType) {
        return bonusRepository.getAllPendingTransactions(userId, byType);
    }


    public String addBonusByUserId(String userId, String inviteCode) {
        return bonusRepository.addBonusByUserId(userId, inviteCode);
    }

    public String buyByBonusAccount(String userId, int transType, int cost, int offerId) {
        return bonusRepository.buyByBonusAccount(userId, transType, cost, offerId);
    }


    public List<Pair<String, Long>> projectPayback(String projectId) throws ParseException {
        String jsonResponse = internalTransactionRepository.projectPayback(projectId);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonResponse);
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
