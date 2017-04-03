package ua.com.itproekt.gup.service.order.blockchain_test;

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


public class BlockChainAlgorithm {

    static Gson              gson = new Gson();
    static String           _HASH = "????????????????????????????";                                  /* (type + <random> + timestamp) SHA-256 */
    static String       SELLER_ID = "000000";                                                         /* String  */
    static String        BUYER_ID = "111111";                                                         /* String  */
    static String         BANK_ID = "222222";                                                         /* String  */
    static long         TIMESTAMP = new Date().getTime();                                             /* String  */
    static String ADDITIONAL_INFO = "kofe-yakobs-monarkh-400-hr-tsena-ot-132-hrn-kasik-braziliia-id"; /* String  */

    private static MemberService member;

    private BlockChainAlgorithm(){
    }
    public BlockChainAlgorithm(MemberService member){
        this.member = member;
    }


    public static Confirm moneyTransfer(BuyerTransactionService buyerService){
        System.err.println("1");
        return buyerService;
    }
    public static Unavailable moneyTransfer(SellerTransactionService sellerService){
        System.err.println("2");
        return sellerService;
    }
    public static Unavailable moneyTransfer(TransactionService service){
        System.err.println("x");
        return service;
    }
    public static Confirm contract(BuyerTransactionService buyerService){
        System.err.println("3");
        return buyerService;
    }
    public static Unavailable contract(SellerTransactionService sellerService){
        System.err.println("4");
        return sellerService;
    }
    public static Unavailable contract(TransactionService service){
        System.err.println("xx");
        return service;
    }
    public static Available action(BuyerTransactionService buyerService){
        System.err.println("5");
        return buyerService;
    }
    public static Available action(SellerTransactionService sellerService){
        System.err.println("6");
        return sellerService;
    }
    public static Unavailable action(TransactionService service){
        System.err.println("xxx");
        return service;
    }

    public static void main(String[] args)
            throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {

        moneyTransfer(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))).confirm();
//        moneyTransfer(new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
        action(new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))).reject();

        TransactionService service = new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
        BuyerTransactionService buyer = new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
        SellerTransactionService seller = new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
//        contract(service).
        contract(buyer).confirm();
        action(seller).reject();
    }

}
