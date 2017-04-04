package ua.com.itproekt.gup.model.order.blockchain_test;

import ua.com.itproekt.gup.util.RSAKeyGenerator2;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RSAKeyGenerator2Test {

    RSAKeyGenerator2 generator2;

    @Before
    public void setUp()
            throws NoSuchProviderException, NoSuchAlgorithmException {
        generator2 = new RSAKeyGenerator2();
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testGeneratePublicKey() {
        try {
            System.out.println( generator2.getPublicKey() );
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e){}
    }

    @Test
    public void testGeneratePublicHash() {
        try {
            System.out.println( generator2.getPublicHash( generator2.getPublicKey() ) );
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e){}
    }

    @Test
    public void testGeneratePrivateKey() {
        try {
            System.out.println( generator2.getPrivateKey() );
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e){}
    }

    @Test
    public void testObjGeneratePublicKey() {
        try {
            System.out.println( generator2.getPublicKey(generator2.getPublicKey()) );
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e){}
    }

    @Test
    public void testObjGeneratePrivateKey() {
        try {
            System.out.println( generator2.getPrivateKey(generator2.getPrivateKey()) );
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e){}
    }

}
