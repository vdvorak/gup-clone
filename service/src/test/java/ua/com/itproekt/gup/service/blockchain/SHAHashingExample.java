package ua.com.itproekt.gup.service.blockchain;

import java.security.MessageDigest;

public class SHAHashingExample {

    public static void main(String[] args)throws Exception {
        String      data = "Bla-Bla-Bla";   // Is String

        /////////////////////////////////////////////////////////////////////////////////////////////
        byte[]  byteData = data.getBytes(); // To byte[]

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(byteData);
        byte[] digestData = md.digest();    // To SHA-256

        // convert the byte to hex format method 1 (To Hash):
        StringBuffer hashData1 = new StringBuffer();
        for (int i=0; i<digestData.length; i++) {
            hashData1.append(Integer.toString((digestData[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("(method 1) Hex format : " + hashData1.toString());


        /////////////////////////////////////////////////////////////////////////////////////////////
        //convert the byte to hex format method 2 (To Hash):
        StringBuffer hashData2 = new StringBuffer();
        for (int i=0; i<digestData.length; i++) {
            String hex = Integer.toHexString(0xff & digestData[i]);
            if(hex.length()==1)
                hashData2.append('0');
            hashData2.append(hex);
        }
        System.out.println("(method 2) Hex format : " + hashData2.toString());
    }
}
