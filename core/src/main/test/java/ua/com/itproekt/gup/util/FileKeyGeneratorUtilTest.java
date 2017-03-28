package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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


public class FileKeyGeneratorUtilTest {

    public final String FILE_PRIVATE_KEY = "id_rsa";
    public final String  FILE_PUBLIC_KEY = "id_rsa.pub";

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCreateKeys()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPair           rsaKey = FileKeyGeneratorUtil.generateRSAKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) rsaKey.getPrivate();
        RSAPublicKey   publicKey = (RSAPublicKey) rsaKey.getPublic();

        FileKeyGeneratorUtil.write(privateKey, "PRIVATE KEY", FILE_PRIVATE_KEY);
        FileKeyGeneratorUtil.write(publicKey, "PUBLIC KEY", FILE_PUBLIC_KEY);

        FileKey filePublicKey = new FileKey();
        System.out.println( filePublicKey.read(FILE_PUBLIC_KEY) );
    }

}
