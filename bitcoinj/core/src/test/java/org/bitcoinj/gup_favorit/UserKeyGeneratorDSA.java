package org.bitcoinj.gup_favorit;


import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class UserKeyGeneratorDSA {

    public static void main(String[] args)
            throws NoSuchAlgorithmException, Exception {
        //////////////////////////////////////////////////////
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("publicKey //////////////////////////////////////////////////////");

        System.out.println( publicKey );

        System.out.println("privateKey //////////////////////////////////////////////////////");

        System.out.println( privateKey.getAlgorithm() );


        System.out.println("//////////////////////////////////////////////////////");

//        byte[] publicBytes = Base64.decodeBase64(publicK);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded()); //X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        System.out.println( pubKey );
    }

}
