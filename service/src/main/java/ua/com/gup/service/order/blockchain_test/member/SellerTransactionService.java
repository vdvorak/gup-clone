package ua.com.gup.service.order.blockchain_test.member;

import ua.com.gup.model.order.blockchain_test.Transaction;
import ua.com.gup.service.order.blockchain_test.TransactionService;


public class SellerTransactionService extends TransactionService {

    private Transaction transaction;

    private SellerTransactionService(){
    }

    public SellerTransactionService(Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}
