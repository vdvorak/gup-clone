package ua.com.itproekt.gup.rule_logiс;

import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.BlockExplorer;
import info.blockchain.api.blockexplorer.Transaction;

import java.io.IOException;


public class ActionAlgorithm implements Algorithm {

    public Transaction getContract(String id)
            throws APIException, IOException {
        //TODO: тело алгоритма

        BlockExplorer block = new BlockExplorer();
        Transaction tx = block.getTransaction(id);
        return tx;
    }

    public void pushTransaction(String data) {
        //TODO: тело алгоритма
    }

}
