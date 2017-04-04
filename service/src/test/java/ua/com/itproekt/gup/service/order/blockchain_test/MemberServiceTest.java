//package ua.com.itproekt.gup.service.order.blockchain_test;
//
//import com.google.gson.Gson;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import ua.com.itproekt.gup.model.order.blockchain_test.transaction.ActionTransaction;
//import ua.com.itproekt.gup.model.order.blockchain_test.transaction.ContractTransaction;
//import ua.com.itproekt.gup.model.order.blockchain_test.transaction.MoneyTransferTransaction;
//import ua.com.itproekt.gup.service.order.blockchain_test.member.BuyerTransactionService;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.SignatureException;
//import java.security.spec.InvalidKeySpecException;
//import java.util.Date;
//
//
//public class MemberServiceTest {
//
//    Gson gson;
//    private String           _HASH; /* (type + <random> + timestamp) SHA-256 */
//    private String       SELLER_ID; /* String  */
//    private String        BUYER_ID; /* String  */
//    private String         BANK_ID; /* String  */
//    private long         TIMESTAMP; /* Long    */
//    private String ADDITIONAL_INFO; /* String  */
//
//    @Before
//    public void setUp() {
//        gson = new Gson();
//        _HASH           = "????????????????????????????";
//        SELLER_ID       = "000000";
//        BUYER_ID        = "111111";
//        BANK_ID         = "222222";
//        TIMESTAMP       = new Date().getTime();
//        ADDITIONAL_INFO = "kofe-yakobs-monarkh-400-hr-tsena-ot-132-hrn-kasik-braziliia-id";
//    }
//
//    @After
//    public void tearDown(){
//    }
//
//    @Test
//    public void testMoneyTransferTransaction() {
//        /**
//         * Транзакция типа MONEY_TRANSFER
//         * ******************************
//         * 0. Это самая первая транзакция которая выполняется покупатель выбрал объявление и нажал - купить
//         * 1. вытягиваем информацию из банка о доступном балансе у покупателя (BUYER)
//         * 2. зарегистрированный номер карты банковской карты у этого покупателя
//         * 3. вытягиваю сумму-стоимость объявления для заказа
//         * 4. если проверка прошла успешно - тогда формируем (новую) самую первую транзакци типа MONEY_TRANSFER
//         */
//        try {
//            MemberService bankService = new MemberService(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
//            System.err.println("----------------------");
//            System.err.println(gson.toJson(bankService.getTransaction()));
//            System.err.println("----------------------| moneyTransferResponse");
//            okhttp3.Response moneyTransferResponse = bankService.confirm();
//            System.err.println(moneyTransferResponse.body().string());
//
//        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
//        }
//    }
//
//    @Test
//    public void testContractTransaction() {
//        /**
//         * Транзакция типа CONTRACT
//         * ************************
//         * Предполагается что банк успешно подтвердил наличие баланса денег у покупателя (BUYER) и уже снял сумму-стоимости со счета у покупателя (BUYER):
//         * 0. Контракт создается на того юзера кому предназначается сумма-денежного перевода (SELLER)
//         * 1. это значит что уже была выполнена транзакция типа MONEY_TRANSFER и мы получили _hash (id) этой транзакции
//         * 2. вытягиваю клиентов (BUYER|SELLER) которые будут участниками этого контракта
//         * 3. вытягиваю какое-нибудь описание объявления для заказа
//         * 4. устанавливаю время согласно текущей локализации
//         */
//        try {
//            MemberService bankService = new MemberService(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
//            okhttp3.Response moneyTransferResponse = bankService.confirm(); // check status transaction from Buyer...
//            //TODO  get _HASH MoneyTransferTransaction
//
//            MemberService buyerService = new MemberService(new BuyerTransactionService(new ContractTransaction(_HASH, new String[]{SELLER_ID,BUYER_ID}, TIMESTAMP, ADDITIONAL_INFO)));
//            okhttp3.Response contractResponse = buyerService.confirm();
//            System.err.println("----------------------| contractResponse");
//            System.err.println(contractResponse.body().string());
//
//        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
//        }
//    }
//
//    @Test
//    public void testActionTransaction() {
//        /**
//         * Транзакция типа Action
//         * **********************
//         * Предполагается что на этом этапе контракт (транзакция типа CONTRACT) уже была создана:
//         * 1. В течении всего процесса продажи - продавец должен подтвердить оплату
//         * 2. по разным причинам этот контракт, пока он открыт, можно еще отменить (и это может сделать и продавец и покупатель)
//         * 3. Существуют договор контракта по которому обе стороны должны его соблюдать (если какая-то сторона нарушает этот договор - тогда контракт может быть безопастно  отменен)
//         */
//        try {
//            MemberService bankService = new MemberService(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
//            okhttp3.Response moneyTransferResponse = bankService.confirm();
//            //TODO  get _HASH MoneyTransferTransaction
//
//            MemberService buyerService = new MemberService(new BuyerTransactionService(new ContractTransaction(_HASH, new String[]{SELLER_ID,BUYER_ID}, TIMESTAMP, ADDITIONAL_INFO)));
//            okhttp3.Response contractResponse = buyerService.confirm();
//            //TODO  get _HASH ContractTransaction
//
//            MemberService sellerService = new MemberService(new BuyerTransactionService(new ActionTransaction(_HASH, BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
//            okhttp3.Response actionResponse = sellerService.confirm();
//            System.err.println("----------------------| actionResponse");
//            System.err.println(actionResponse.body().string());
//
//        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
//        }
//    }
//}
