package org.bitcoinj.crypto;


import java.security.*;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Map;

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
