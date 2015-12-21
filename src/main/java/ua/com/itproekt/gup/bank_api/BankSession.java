package ua.com.itproekt.gup.bank_api;


import ua.com.itproekt.gup.bank_api.entity.BankUser;
import ua.com.itproekt.gup.bank_api.liqpay.LiqPay;
import ua.com.itproekt.gup.bank_api.repository.BalanceRepository;
import ua.com.itproekt.gup.bank_api.repository.ExternalTransactionRepository;
import ua.com.itproekt.gup.bank_api.repository.InternalTransactionRepository;
import ua.com.itproekt.gup.bank_api.repository.UserRepository;
import ua.com.itproekt.gup.bank_api.services.BankService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RAYANT on 20.10.2015.
 */
public class BankSession {

    private final String URL = "bank-tkani.rhcloud.com";
    private BalanceRepository balanceRepository = new BalanceRepository(this);
    private ExternalTransactionRepository externalTransactionRepository = new ExternalTransactionRepository(this);
    private InternalTransactionRepository internalTransactionRepository = new InternalTransactionRepository(this);
    private UserRepository userRepository = new UserRepository(this);


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

    public String liqPayRenderHtmlForm(String id, Long amount) throws UnsupportedEncodingException {
        HashMap params = new HashMap();
        params.put("version", "3");
        params.put("amount", amount);
        params.put("currency", "UAH");
        params.put("description", new String("Пополнение баланса".getBytes("UTF-8"),"cp1251") );
        params.put("order_id",BankService.getRandomPassword()+id);
        params.put("server_url", "http://bank-tkani.rhcloud.com/callback");
        params.put("sandbox", "1");
        params.put("public_key", "i74044182839");
        LiqPay liqpay = new LiqPay("i74044182839", "psMQcCR32o4TZRZTKI0Yoe4UDNyFHNFHf76Pyedr");
        String html = liqpay.cnb_form(params);
        return html;
    }

    public Map<String, String> liqPayGenerateParamForHtmlForm(String id, Long amount){
        HashMap params = new HashMap();
        params.put("version", "3");
        params.put("amount", amount);
        params.put("currency", "UAH");
        try {
            params.put("description", new String("Пополнение баланса".getBytes("UTF-8"),"cp1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("order_id", BankService.getRandomPassword()+id);
        params.put("server_url", "http://bank-tkani.rhcloud.com/callback");
        params.put("sandbox", "1");
        params.put("public_key", "i74044182839");
        return new LiqPay("i74044182839", "psMQcCR32o4TZRZTKI0Yoe4UDNyFHNFHf76Pyedr").generateData(params);
    }

    public void accountantRequest(String accountantLogin, String userId, Long amount, String comment){
        internalTransactionRepository.accountantRequest(accountantLogin, userId, amount, comment);
    }

    public void adminConfirm(Long internalTransactionId, String adminLogin){
        internalTransactionRepository.adminConfirm(internalTransactionId, adminLogin);
    }

    public void createBalanceRecord(String user, Integer typeEntity) {
        balanceRepository.createBalanceRecord(user, typeEntity);
    }

    public String getAllPendingTransactionsJson(){
       return internalTransactionRepository.getAllPendingTransactionsJson();
    }

    public String getAllAccountantPendingTransactionsJson(String login){
        return internalTransactionRepository.getAllAccountantPendingTransactionsJson(login);
    }

    public void adminReject(Long internalTransactionId, String adminLogin, String comment){
        internalTransactionRepository.adminReject(internalTransactionId, adminLogin, comment);
    }

    public void accountantCancelRequest(Long internalTransactionId){
        internalTransactionRepository.accountantCancelRequest(internalTransactionId);
    }

}
