package org.bitcoinj.gup_favorit_test;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class MainTransaction {

    protected final static Logger LOGGER = Logger.getLogger(MainTransaction.class);
    public static final int            KEY_SIZE = 2048;
    public static final String FILE_PRIVATE_KEY = "id_rsa";
    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";

    public static void main(String[] args)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        LOGGER.info("BouncyCastle provider added.");

        KeyPair    keyPair = generateRSAKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey   publicKey = (RSAPublicKey) keyPair.getPublic();

        writePemFile(privateKey, "PRIVATE KEY", FILE_PRIVATE_KEY);
        writePemFile(publicKey, "PUBLIC KEY", FILE_PUBLIC_KEY);

        PemFile filePublicKey = new PemFile();
        System.out.println( filePublicKey.read2(FILE_PUBLIC_KEY) );

        ////////////////////////////////////////////////////////////////////////
        System.out.println(hmacDigest("The quick brown fox jumps over the lazy dog", "key", "SHA-256"));
    }

    public static KeyPair generateRSAKeyPair()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(KEY_SIZE);

        KeyPair keyPair = generator.generateKeyPair();
//        LOGGER.info("RSA key pair generated.");
        return keyPair;
    }

    public static void writePemFile(Key key, String description, String filename)
            throws FileNotFoundException, IOException {
        PemFile file = new PemFile(key, description);
        file.write(filename);
//        LOGGER.info(String.format("%s successfully writen in file %s.", description, filename));
    }



    public static String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {}
        return digest;
    }

}
