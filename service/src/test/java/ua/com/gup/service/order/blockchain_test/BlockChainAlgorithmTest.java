package ua.com.itproekt.gup.service.order.blockchain_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import ua.com.itproekt.gup.model.order.blockchain_test.transaction.MoneyTransferTransaction;
import ua.com.itproekt.gup.service.order.blockchain_test.member.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;



public class BlockChainAlgorithmTest {

    Gson gson;
    BlockChainAlgorithm bca;
    private String             _HASH; /* (type + <random> + timestamp) SHA-256 */
    private String       PUBLIC_HASH; /* (type + <random> + timestamp) SHA-256 */
    private String   SIGNATURE_STORE; /* String  */
    private String         SELLER_ID; /* String  */
    private String          BUYER_ID; /* String  */
    private String           BANK_ID; /* String  */
    private long           TIMESTAMP; /* Long    */
    private String   ADDITIONAL_INFO; /* String  */
    private String        PRODUCT_ID; /* String  */
    private long               PRICE; /* Long    */
    private String         USER_CARD; /* String  */
    private long              AMOUNT; /* Long    */
    private long BANK_TRANSACTION_ID; /* Long    */
    private String         ACTION_ID; /* String  */

    @Before
    public void setUp() {
        gson = new Gson();
        bca                 = new BlockChainAlgorithm();
        _HASH               = "????????????????????????????";
        PUBLIC_HASH         = "0?o?k?m?j?y?6?t?f?c?d?x?s?q?";
        SIGNATURE_STORE     = "1234567890lkjhgfdsaqwertyuiop";
        SELLER_ID           = "000000";
        BUYER_ID            = "111111";
        BANK_ID             = "222222";
        TIMESTAMP           = new Date().getTime();
        ADDITIONAL_INFO     = "kofe-yakobs-monarkh-400-hr-tsena-ot-132-hrn-kasik-braziliia-id";
        PRODUCT_ID          = "PRODUCT9555";
        PRICE               = 500;
        USER_CARD           = "0123456789";
        AMOUNT              = 10000;
        BANK_TRANSACTION_ID = 127;
        ACTION_ID           = "CONTRACT";
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testnMemberAccessTransactionAlgorithm()
            throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException
    {
        bca.moneyTransfer(new BuyerTransactionService(new MoneyTransferTransaction(USER_CARD, AMOUNT, PUBLIC_HASH, SIGNATURE_STORE, BANK_TRANSACTION_ID))).confirm();
//        bca.moneyTransfer(new SellerTransactionService(new MoneyTransferTransaction(USER_CARD, AMOUNT, PUBLIC_HASH, SIGNATURE_STORE, BANK_TRANSACTION_ID)));
        bca.action(new SellerTransactionService(new MoneyTransferTransaction(USER_CARD, AMOUNT, PUBLIC_HASH, SIGNATURE_STORE, BANK_TRANSACTION_ID))).reject();

        TransactionService service = new SellerTransactionService(new MoneyTransferTransaction(USER_CARD, AMOUNT, PUBLIC_HASH, SIGNATURE_STORE, BANK_TRANSACTION_ID));
        BuyerTransactionService buyer = new BuyerTransactionService(new MoneyTransferTransaction(USER_CARD, AMOUNT, PUBLIC_HASH, SIGNATURE_STORE, BANK_TRANSACTION_ID));
        SellerTransactionService seller = new SellerTransactionService(new MoneyTransferTransaction(USER_CARD, AMOUNT, PUBLIC_HASH, SIGNATURE_STORE, BANK_TRANSACTION_ID));
//        bca.contract(service).
        bca.contract(buyer).confirm();
        bca.action(seller).reject();
        bca.moneyTransfer(seller).reject();
    }

}
