//package ua.com.itproekt.gup.service.blockchain.storage;
//
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.security.Key;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.spec.X509EncodedKeySpec;
//
///**
// * http://codereview.stackexchange.com/questions/117944/bouncycastle-implementation-with-x509certificate-signing-keystore-generation-a
// */
//public class Me1 {
//
//    public static void main(String[] args) {
//
//        try {
//            generate("publicKeyMe1");
//            getKeyFromFile("publicKeyMe1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void generate(String fileName) throws Exception {
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//        kpg.initialize(2048);
//        KeyPair kpair = kpg.genKeyPair();
//
//        byte[] publicKeyBytes = kpair.getPublic().getEncoded();
//
//        FileOutputStream fos = new FileOutputStream("publicKeyMe1");
//        fos.write(publicKeyBytes);
//        fos.close();
//    }
//
//    public static Key getKeyFromFile(String fileName) throws Exception{
//        Key pk = null;
//        File f = new File(fileName);
//        FileInputStream fis = new FileInputStream(f);
//        DataInputStream dis = new DataInputStream(fis);
//        byte[] keyBytes = new byte[(int)f.length()];
//        dis.readFully(keyBytes);
//        dis.close();
//
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        pk = kf.generatePublic(spec);
//        return pk;
//    }
//
//}
