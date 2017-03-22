package ua.com.itproekt.gup.rule_logiс;


import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.Transaction;

import java.io.IOException;

public class APIAlgorithm {

    private Algorithm algorithm;

    public APIAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Transaction getContract(String id)
            throws APIException, IOException {
        return algorithm.getContract(id);
    }

    public void pushTransaction(String data) {
        algorithm.pushTransaction(data);
    }

}
