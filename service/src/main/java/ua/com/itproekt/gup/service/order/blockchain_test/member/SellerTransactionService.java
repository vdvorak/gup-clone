package ua.com.itproekt.gup.service.order.blockchain_test.member;

import ua.com.itproekt.gup.model.order.blockchain_test.Transaction;
import ua.com.itproekt.gup.service.order.blockchain_test.TransactionService;


public class SellerTransactionService extends TransactionService {

    private Transaction transaction;

    private SellerTransactionService(){
    }

    public SellerTransactionService(Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    protected Transaction getTransaction() {
        return transaction;
    }
}
