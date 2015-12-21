package ua.com.itproekt.gup.controller.test.services;

import ua.com.itproekt.gup.bank_api.BankSession;

import java.io.UnsupportedEncodingException;

/**
 * Created by RAYANT on 26.11.2015.
 */
public class BankTestService {

   private static BankSession session = new BankSession();

    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.err.println(getAllUsers());

//        System.err.println(session.liqPayGenerateParamForHtmlForm("test", 100L));
    }

    private static String getAllUsers(){
        return session.getAllUsersJson();
    }

    private static String getAllBalances(){
        return  session.getAllBalancesJson();
    }
}
