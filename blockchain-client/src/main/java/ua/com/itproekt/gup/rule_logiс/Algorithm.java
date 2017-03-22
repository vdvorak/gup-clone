package ua.com.itproekt.gup.rule_logiс;

import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.Transaction;

import java.io.IOException;


public interface Algorithm {

    /**
     * возвратить контракт и его цепочку действий
     */
    Transaction getContract(String id) throws APIException, IOException;

    /**
     * пропихнуть транзакцию
     */
    void pushTransaction(String data);

}
