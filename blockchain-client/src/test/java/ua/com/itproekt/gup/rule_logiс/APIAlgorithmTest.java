package ua.com.itproekt.gup.rule_logiс;

import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @see https://docs.google.com/document/d/1r7IBQIHmvrmCxfyGb4lQv1MOiqV-mvwxEHA3jh91JdI/edit?ts=58d00fd6#
 */

public class APIAlgorithmTest {

    private APIAlgorithm algorithm;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContractAlgorithm() {
        algorithm = new APIAlgorithm( new ContractAlgorithm() );

        try {
            Transaction actual = algorithm.getContract("df67414652722d38b43dcbcac6927c97626a65bd4e76a2e2787e22948a7c5c47");

            System.out.println( actual );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

}
