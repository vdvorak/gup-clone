package ua.com.itproekt.gup.service.blockchain;

import ua.com.itproekt.gup.model.order.blockchain.Chain;
import ua.com.itproekt.gup.model.order.blockchain.Transaction;


public class BlockChainAlgorithm {

    private Chain chain;

    public BlockChainAlgorithm(Chain chain){
        this.chain = chain;
    }

    public Transaction reject(){
        Transaction transaction = chain.getTransaction();
        // TODO

        return transaction;
    }

    public Transaction confirm(){
        Transaction transaction = chain.getTransaction();
        // TODO

        return transaction;
    }

}
