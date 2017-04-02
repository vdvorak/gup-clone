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

    private MemberService member;

    private BlockChainAlgorithm(){
    }
    public BlockChainAlgorithm(MemberService member){
        this.member = member;
    }

    public static void main(String[] args)
            throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        BlockChainAlgorithm bca = new BlockChainAlgorithm(new MemberService(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))));

//        bca.getInterface("3").
    }

    public Unavailable getInterface(String option){
        switch (option) {
            case "1":
                return getConfirm();
            case "2":
                return getReject();
            case "3":
                return getAvailable();
            default:
                return getUnavailable();
        }

//        Unavailable test0 = member;
//        Confirm test1 = member;
//        Reject test2 = member;
//        Available test3 = member;
//
//        return test0;
    }

    private Unavailable getUnavailable() {
        return (Unavailable) member;
    }

    private Confirm getConfirm() {
        return (Confirm) member;
    }

    private Reject getReject() {
        return (Reject) member;
    }

    private Available getAvailable() {
        return (Available) member;
    }
}
