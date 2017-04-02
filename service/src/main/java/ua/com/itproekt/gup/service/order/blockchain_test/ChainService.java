package ua.com.itproekt.gup.service.order.blockchain_test;


import okhttp3.Response;

import java.io.IOException;

public class ChainService {
    private TransactionService transaction;

    private ChainService() {
    }
    public ChainService(TransactionService transaction) {
        this.transaction = transaction;
    }

    public TransactionService getTransaction() {
        return transaction;
    }

    public Response confirm() throws IOException {
        return transaction.confirm();
    }

    public Response reject() throws IOException {
        return transaction.reject();
    }
}
