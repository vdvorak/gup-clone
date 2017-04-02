package ua.com.itproekt.gup.service.order.blockchain_test.member;

import ua.com.itproekt.gup.model.order.blockchain_test.Transaction;
import ua.com.itproekt.gup.service.order.blockchain_test.TransactionService;


public class BuyerTransactionService extends TransactionService {

    private Transaction transaction;

    private BuyerTransactionService(){
    }

    public BuyerTransactionService(Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    protected Transaction getTransaction() {
        return transaction;
    }
}
