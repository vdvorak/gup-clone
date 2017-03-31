//package ua.com.itproekt.gup.service.blockchain;
//
//
//import java.io.FileInputStream;
//import java.security.MessageDigest;
//
//
///**
// * https://www.mkyong.com/java/java-sha-hashing-example/
// */
//
//public class SHACheckSumExample
//{
//    public static void main(String[] args)throws Exception
//    {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
////        FileInputStream fis = new FileInputStream("c:\\loging.log");
//
//        byte[] dataBytes = "Mama".getBytes();
//
////        byte[] dataBytes = new byte[1024];
////        int nread = 0;
////        while ((nread = fis.read(dataBytes)) != -1) {
////            md.update(dataBytes, 0, nread);
////        };
//        md.update(dataBytes);
//
//        byte[] mdbytes = md.digest();
//
//        //convert the byte to hex format method 1
//        StringBuffer sb = new StringBuffer();
//        for (int i=0; i<mdbytes.length; i++) {
//            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
//        }
//
//        System.out.println("Hex format : " + sb.toString());
//
//        //convert the byte to hex format method 2
//        StringBuffer hexString = new StringBuffer();
//        for (int i=0; i<mdbytes.length; i++) {
//            hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
//        }
//
//        System.out.println("Hex format : " + hexString.toString());
//    }
//}
//
