//package ua.com.itproekt.gup.service.blockchain;
//
//import okhttp3.Response;
//import ua.com.itproekt.gup.model.order.blockchain.Chain;
//import ua.com.itproekt.gup.model.order.blockchain.Transaction;
//import ua.com.itproekt.gup.util.FileKeyGenerator;
//
//import java.io.IOException;
//
//public class ChainService {
//
//    private TransactionService transactionService;
//
//    public ChainService(TransactionService transactionService){
//        this.transactionService = transactionService;
//    }
//
//    public Response postTransaction()
//            throws IOException
//    {
//        return transactionService.postTransaction();
//    }
//
//    public String getHash() {
//        return transactionService.getHash();
//    }
//
//    public FileKeyGenerator getKeyPair() {
//        return transactionService.getKeyPair();
//    }
//
//    public Transaction getTransaction(){
//        return transactionService.getTransaction();
//    }
//
//}
