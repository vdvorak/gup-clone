package ua.com.itproekt.gup.controller.test.services;

import ua.com.itproekt.gup.bank_api.BankSession;

import java.io.UnsupportedEncodingException;


public class BankTestService {

   private static BankSession session = new BankSession();

    public static void main(String[] args) throws UnsupportedEncodingException {

    }



    private static String getAllUsers(){
        return session.getAllUsersJson();
    }

    private static String getAllBalances(){
        return  session.getAllBalancesJson();
    }
}
