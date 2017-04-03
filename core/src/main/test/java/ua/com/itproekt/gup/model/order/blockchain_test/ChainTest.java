package ua.com.itproekt.gup.model.order.blockchain_test;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.model.order.blockchain_test.transaction.ActionTransaction;
import ua.com.itproekt.gup.model.order.blockchain_test.transaction.ContractTransaction;
import ua.com.itproekt.gup.model.order.blockchain_test.transaction.MoneyTransferTransaction;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;


public class ChainTest {

    Gson gson;
    private String           _HASH; /* (type + <random> + timestamp) SHA-256 */
    private String       SELLER_ID; /* String  */
    private String        BUYER_ID; /* String  */
    private String         BANK_ID; /* String  */
    private long         TIMESTAMP; /* Long    */
    private String ADDITIONAL_INFO; /* String  */

    @Before
    public void setUp() {
        gson = new Gson();
        _HASH           = "????????????????????????????";
        SELLER_ID       = "000000";
        BUYER_ID        = "111111";
        BANK_ID         = "222222";
        TIMESTAMP       = new Date().getTime();
        ADDITIONAL_INFO = "kofe-yakobs-monarkh-400-hr-tsena-ot-132-hrn-kasik-braziliia-id";
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testMoneyTransferTransaction() {
        /**
         * Транзакция типа MONEY_TRANSFER
         * ******************************
         * 0. Это самая первая транзакция которая выполняется покупатель выбрал объявление и нажал - купить
         * 1. вытягиваем информацию из банка о доступном балансе у покупателя (BUYER)
         * 2. зарегистрированный номер карты банковской карты у этого покупателя
         * 3. вытягиваю сумму-стоимость объявления для заказа
         * 4. если проверка прошла успешно - тогда формируем (новую) самую первую транзакци типа MONEY_TRANSFER
         */
        try {
            Chain moneyTransfer = new Chain(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
            System.out.println( gson.toJson(moneyTransfer) ); // System.out.println( moneyTransfer.getTransaction() );
        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
        }
    }

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
////            Chain contract = new Chain(new ContractTransaction(null, new String[]{SELLER_ID,BUYER_ID}, TIMESTAMP, ADDITIONAL_INFO));
//            Chain contract = new Chain(new ContractTransaction(_HASH, new String[]{SELLER_ID,BUYER_ID}, TIMESTAMP, ADDITIONAL_INFO));
//            System.out.println( gson.toJson(contract) ); // System.out.println( contract.getTransaction() );
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
//            Chain action = new Chain(new ActionTransaction(_HASH, BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
//            System.out.println( gson.toJson(action) ); // System.out.println( action.getTransaction() );
//        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
//        }
//    }
}
