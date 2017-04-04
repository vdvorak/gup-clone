//package ua.com.itproekt.gup.service.order.blockchain_test;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.google.gson.Gson;
//import ua.com.itproekt.gup.model.order.blockchain_test.transaction.MoneyTransferTransaction;
//import ua.com.itproekt.gup.service.order.blockchain_test.member.*;
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
//
//public class BlockChainAlgorithmTest {
//
//    Gson gson;
//    private String           _HASH; /* (type + <random> + timestamp) SHA-256 */
//    private String       SELLER_ID; /* String  */
//    private String        BUYER_ID; /* String  */
//    private String         BANK_ID; /* String  */
//    private long         TIMESTAMP; /* Long    */
//    private String ADDITIONAL_INFO; /* String  */
//    BlockChainAlgorithm bca;
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
//        bca             = new BlockChainAlgorithm();
//    }
//
//    @After
//    public void tearDown(){
//    }
//
//    @Test
//    public void testnMemberAccessTransactionAlgorithm()
//            throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException
//    {
//        bca.moneyTransfer(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))).confirm();
////        bca.moneyTransfer(new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
//        bca.action(new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))).reject();
//
//        TransactionService service = new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
//        BuyerTransactionService buyer = new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
//        SellerTransactionService seller = new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
////        bca.contract(service).
//        bca.contract(buyer).confirm();
//        bca.action(seller).reject();
//        bca.moneyTransfer(seller).reject();
//    }
//
//}
