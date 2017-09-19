package ua.com.gup.service.order.blockchain_test;

import ua.com.gup.service.order.blockchain_test.member.*;

import java.io.IOException;


public class BlockChainAlgorithm {

    BlockChainAlgorithm(){}
    public BlockChainAlgorithm(MemberService member, String option) throws IOException {
        switch (option) {
            case "moneyTransfer-confirm":
                moneyTransfer(member).confirm();
                break;
            case "moneyTransfer-reject":
                moneyTransfer(member).reject();
                break;
            case "contract-confirm":
                contract(member).confirm();
                break;
            case "contract-reject":
                contract(member).reject();
                break;
            case "action-confirm":
                action(member).confirm();
                break;
            case "action-reject":
                action(member).reject();
                break;
        }
    }
    public BlockChainAlgorithm(BuyerTransactionService buyer, String option) throws IOException {
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
    public BlockChainAlgorithm(SellerTransactionService seller, String option) throws IOException {
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
    public BlockChainAlgorithm(TransactionService service, String option) throws IOException {
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

    public   Available moneyTransfer(MemberService member){
        return member;
    }
    public     Confirm moneyTransfer(BuyerTransactionService buyer){
        return buyer;
    }
    public      Reject moneyTransfer(SellerTransactionService seller){
        return seller;
    }
    public Unavailable moneyTransfer(TransactionService service){
        return service;
    }
    public   Available contract(MemberService member){
        return member;
    }
    public     Confirm contract(BuyerTransactionService buyer){
        return buyer;
    }
    public Unavailable contract(SellerTransactionService seller){
        return seller;
    }
    public Unavailable contract(TransactionService service){
        return service;
    }
    public   Available action(MemberService member){
        return member;
    }
    public   Available action(BuyerTransactionService buyer){
        return buyer;
    }
    public   Available action(SellerTransactionService seller){
        return seller;
    }
    public Unavailable action(TransactionService service){
        return service;
    }

}
