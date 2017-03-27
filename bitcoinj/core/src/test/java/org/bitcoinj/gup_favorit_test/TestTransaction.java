package org.bitcoinj.gup_favorit_test;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;


public class TestTransaction {

    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";

    public static void main(final String[] args)
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
        Security.addProvider(new BouncyCastleProvider());

        // Digest the mutation twice.
//        byte[]       members           = new byte[]{
//                10, 8, 47, 13, -126, -118, 96, -15, 87, 39,
//                18, 22, 10, 3, 107, 101, 121, 18, 6, 10,
//                4, 100, 97, 116, 97, 26, 7, 118, 101, 114,
//                115, 105, 111, 110, 26, 8, 109, 101, 116, 97,
//                100, 97, 116, 97};
        byte[] member1 = "587ca08e4c8e89327948309e".getBytes("UTF-8");
        byte[] member2 = "58cae20e4c8e9634fe40e852".getBytes("UTF-8");

        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[] digest1 = msg.digest(member1);
        byte[] digest2 = msg.digest(member2);

        ////////////////////////////////////////////////////////////////////////
        KeyPair       keyPair = MainTransaction.generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        String publicKey = new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
//        System.out.println("PUBLIC   " + publicKey);

        // Sign the mutation with the private key
        Signature signature = Signature.getInstance("RSA", "BC");
        signature.initSign( privateKey, new FixedRand() );
        signature.update(digest1);
        signature.update(digest2);
        final byte[]   sign = signature.sign();
//        System.out.println("SIGN     " + Hex.toHexString(sign));

        ////////////////////////////////////////////////////////////////////////
        System.out.println();

        // Create the JSON representation of the transaction to post
        String format = "{\n"+
                "    \"transaction\": {\n" +
                "        \"type\": \"%s\",\n" +
                "        \"data\": {\n" +
                "            \"logicRef\": \"%s\",\n" +
                "            \"signature\": {\n" +
                "                \"sign\": \"%s\"\n" +
                "                \"publicKey\": \"%s\"\n" +
                "            },\n" +
                "            \"members\": \n" +
                "            [\n" +
                "                \"%s\",\n" +
                "                \"%s\"\n" +
                "            ],\n" +
                "            \"additionalInfo\": {\n" +
                "                \"message\": \"%s\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"timestamp\": %s,\n" +
                "        \"_hash\": \"%s\"\n" +
                "    }\n" +
                "}";
        System.out.println( String.format(format, "CONTRACT", 0, Hex.toHexString(sign), publicKey, Hex.toHexString(digest1), Hex.toHexString(digest2), "I have sell you product", new Date().getTime(), "431a8a1af9cb37a5c3979e7e4b64ebf570809be4246a8c4a5f6a5e97ad0df36e") ); // json
    }
}


/**
 * This class represents a SecureRandom which produces the same value.
 */
class FixedRand extends SecureRandom {
    MessageDigest sha;
    byte[] state;

    FixedRand() {
        try {
            sha = MessageDigest.getInstance("SHA-256");
            state = sha.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("can't find SHA-256!");
        }
    }
    public void nextBytes(
            byte[] bytes) {
        int off = 0;
        sha.update(state);
        while (off < bytes.length) {
            state = sha.digest();
            if (bytes.length - off > state.length) {
                System.arraycopy(state, 0, bytes, off, state.length);
            } else {
                System.arraycopy(state, 0, bytes, off, bytes.length - off);
            }
            off += state.length;
            sha.update(state);
        }
    }
}
