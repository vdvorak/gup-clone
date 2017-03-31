package ua.com.itproekt.gup.service.blockchain;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.*;

import org.apache.commons.codec.binary.Base64;

/**
 * https://gist.github.com/avilches/750151
 */

public class Sha {

    public static void main(String[] args) {
        String TEST = "Bla-Bla-Bla";


        //            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] byteHex = decoder.decodeBuffer(TEST);
        byte[] byteHex = Base64.decodeBase64(TEST);
        String strHash = Base64.encodeBase64String(byteHex);
        byte[] byteHash = Base64.encodeBase64(byteHex);
        System.out.println(byteHash);

        ///////////////////////////////////////////////////////////////////
        System.out.println("///////////////////////////////////////////////////////////////////");

        try {
            System.out.println( hash256(TEST) );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            System.out.println( hash256_2(TEST) );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            System.out.println( decoder.decodeBuffer(TEST) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String hash256_2(String data)
            throws NoSuchAlgorithmException
    {
        BASE64Decoder decoder = new BASE64Decoder();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try {
            System.err.println(decoder.decodeBuffer(data));
            md.update( decoder.decodeBuffer(data) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytesToHex(md.digest());
    }

    public static String hash256(String data)
            throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        System.err.println(data.getBytes());
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes)
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

}

