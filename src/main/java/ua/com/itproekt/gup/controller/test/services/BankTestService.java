package ua.com.itproekt.gup.controller.test.services;

import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.bank_api.entity.InternalTransaction;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class BankTestService {

   private static BankSession session = new BankSession();

    public static void main(String[] args) throws UnsupportedEncodingException {


//        List<InternalTransaction> internalTransactions = session.getAllRecipientInternalTransactionsJson("56d7f812c2be2c21691d7d4a");
//        System.err.println("Test: " + internalTransactions.get(5).getAmount());
    }

    private static String getAllUsers(){
        return session.getAllUsersJson();
    }

    private static String getAllBalances(){
        return  session.getAllBalancesJson();
    }
}
