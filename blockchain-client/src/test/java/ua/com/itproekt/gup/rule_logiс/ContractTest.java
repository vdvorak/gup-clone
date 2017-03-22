package ua.com.itproekt.gup.rule_logiс;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ContractTest {

    Status seller;
    Status buyer;

    @Before
    public void setUp() {
        seller  = new SellerStatus();
        buyer   = new BuyerStatus();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContractStatus() {
        Contract contract = new Contract();

        contract.setState( seller );
        contract.doAction();
        contract.setState( buyer );
        contract.doAction();
    }

}
