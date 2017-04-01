package ua.com.itproekt.gup.service.blockchain;

import okhttp3.Response;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionService;
import ua.com.itproekt.gup.util.FileKeyGenerator;

import java.io.IOException;

public class ChainService {

    private TransactionService transactionService;

    public ChainService(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public Response postTransaction()
            throws IOException
    {
        return transactionService.postTransaction();
    }

    public String getHash() {
        return transactionService.getHash();
    }

    public FileKeyGenerator getKeyPair() {
        return transactionService.getKeyPair();
    }

}
