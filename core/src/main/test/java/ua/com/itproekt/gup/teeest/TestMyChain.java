package ua.com.itproekt.gup.teeest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.teeest.my_transaction.MyContractTransaction;
import ua.com.itproekt.gup.teeest.my_transaction.MyMoneyTransferTransaction;


public class TestMyChain {

    private String BUYER_ID;
    private String BANK_ID;

    @Before
    public void setUp() {
        BUYER_ID = "111111";
        BANK_ID = "222222";
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testMyTransaction(){
        MyChain contract = new MyChain(new MyContractTransaction(BUYER_ID, 123456789));
        System.out.println( contract.getTransaction() );

        MyChain moneyTransfer = new MyChain(new MyMoneyTransferTransaction(BANK_ID, 123456789));
        System.out.println( moneyTransfer.getTransaction() );
    }

}
