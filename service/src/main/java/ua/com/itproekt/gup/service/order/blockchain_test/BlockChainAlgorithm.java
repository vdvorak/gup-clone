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

    public BlockChainAlgorithm(BuyerTransactionService buyer, String option)
            throws IOException {
        switch (option) {
            case "moneyTransfer-confirm":
                moneyTransfer(buyer).confirm();
                break;
            case "moneyTransfer-reject":
//                moneyTransfer(buyer).reject();
                break;
            case "contract-confirm":
                contract(buyer).confirm();
                break;
            case "contract-reject":
//                contract(buyer).reject();
                break;
            case "action-confirm":
                action(buyer).confirm();
                break;
            case "action-reject":
                action(buyer).reject();
                break;
        }
    }

    public BlockChainAlgorithm(SellerTransactionService seller, String option)
            throws IOException {
        switch (option) {
            case "moneyTransfer-confirm":
//                moneyTransfer(seller).confirm();
                break;
            case "moneyTransfer-reject":
                moneyTransfer(seller).reject();
                break;
            case "contract-confirm":
//                contract(seller).confirm();
                break;
            case "contract-reject":
//                contract(seller).reject();
                break;
            case "action-confirm":
                action(seller).confirm();
                break;
            case "action-reject":
                action(seller).reject();
                break;
        }
    }

    public BlockChainAlgorithm(TransactionService service, String option)
            throws IOException {
        switch (option) {
            case "moneyTransfer-confirm":
//                moneyTransfer(service).confirm();
                break;
            case "moneyTransfer-reject":
//                moneyTransfer(service).reject();
                break;
            case "contract-confirm":
//                contract(service).confirm();
                break;
            case "contract-reject":
//                contract(service).reject();
                break;
            case "action-confirm":
//                action(service).confirm();
                break;
            case "action-reject":
//                action(service).reject();
                break;
        }
    }

    public static Confirm moneyTransfer(BuyerTransactionService buyer){
        return buyer;
    }
    public static Reject moneyTransfer(SellerTransactionService seller){
        return seller;
    }
    public static Unavailable moneyTransfer(TransactionService service){
        return service;
    }
    public static Confirm contract(BuyerTransactionService buyer){
        return buyer;
    }
    public static Unavailable contract(SellerTransactionService seller){
        return seller;
    }
    public static Unavailable contract(TransactionService service){
        return service;
    }
    public static Available action(BuyerTransactionService buyer){
        return buyer;
    }
    public static Available action(SellerTransactionService seller){
        return seller;
    }
    public static Unavailable action(TransactionService service){
        return service;
    }

//    public static void main(String[] args)
//            throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
//
//        moneyTransfer(new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))).confirm();
////        moneyTransfer(new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO)));
//        action(new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO))).reject();
//
//        TransactionService service = new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
//        BuyerTransactionService buyer = new BuyerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
//        SellerTransactionService seller = new SellerTransactionService(new MoneyTransferTransaction(BANK_ID, TIMESTAMP, ADDITIONAL_INFO));
////        contract(service).
//        contract(buyer).confirm();
//        action(seller).reject();
//        moneyTransfer(seller).reject();
//    }

}
