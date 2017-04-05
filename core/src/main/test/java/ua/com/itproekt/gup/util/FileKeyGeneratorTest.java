package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.util.RSAKeyGenerator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;


public class FileKeyGeneratorTest {


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test FileKey Generator
     */
    @Test
    public void testGeneratorFileKey() {
        try {
            RSAKeyGenerator fileKeyGenerator = new RSAKeyGenerator();

            PrivateKey privateKey = fileKeyGenerator.getPrivate();
            System.out.println(String.format("Instantiated private key: %s", privateKey));
            PublicKey publicKey = fileKeyGenerator.getPublic();
            System.out.println(String.format("Instantiated public key: %s", publicKey));
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

}
