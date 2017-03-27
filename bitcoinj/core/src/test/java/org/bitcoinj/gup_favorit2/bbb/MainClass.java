//package org.bitcoinj.gup_favorit2.bbb;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.SecureRandom;
//import java.security.Security;
//import java.security.Signature;
//
//
///**
// * @see http://www.java2s.com/Code/Java/Security/SignatureSignAndVerify.htm
// */
//
//public class MainClass {
//
//    public static void main(String args[])
//            throws Exception {
//        Security.addProvider( new org.bouncycastle.jce.provider.BouncyCastleProvider() );
//
//        byte[] data = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74 };
//
//        KeyPair        keyPair1 = generateKeyPair(999);
//        byte[] digitalSignature = signData(data, keyPair1.getPrivate());
//
//        boolean verified1 = verifySig(data, keyPair1.getPublic(), digitalSignature);
//        System.out.println(verified1) ;
//
//        KeyPair  keyPair2 = generateKeyPair(888);
//        boolean verified2 = verifySig(data, keyPair2.getPublic(), digitalSignature);
//        System.out.println( verified2 );
//    }
//
//    public static byte[] signData(byte[] data, PrivateKey key)
//            throws Exception {
//        Signature signer = Signature.getInstance("SHA1withDSA");
//        signer.initSign(key);
//        signer.update(data);
//
//        return signer.sign();
//    }
//
//    public static boolean verifySig(byte[] data, PublicKey key, byte[] sig)
//            throws Exception {
//        Signature signer = Signature.getInstance("SHA1withDSA");
//        signer.initVerify(key);
//        signer.update(data);
//
//        return signer.verify(sig);
//    }
//
//    public static KeyPair generateKeyPair(long seed)
//            throws Exception {
//        Security.addProvider( new org.bouncycastle.jce.provider.BouncyCastleProvider() );
//        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("DSA");
//        SecureRandom rng = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        rng.setSeed(seed);
//        keyGenerator.initialize(1024, rng);
//
//        return keyGenerator.generateKeyPair();
//    }
//
//}
