package ua.com.itproekt.gup.model.order.blockchain_test.storageOK;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
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


public class WriteKeyTest {

    public static final int     KEY_SIZE = 2048;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testReaderKey()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPair    keyPair = generateRSAKeyPair();
        RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey   pub = (RSAPublicKey) keyPair.getPublic();

        writePemFile(priv, "PRIVATE KEY", "id_rsa");
        writePemFile(pub, "PUBLIC KEY", "id_rsa.pub");

        //////////////////////////////////////////////////////////////////////
        String content = new String(Files.readAllBytes(Paths.get("id_rsa.pub")));
        System.err.println(content);

//        PemReader pemReader = new PemReader( new InputStreamReader(new FileInputStream("id_rsa.pub")) );

//        String strPub2 = FileUtils.readFileToString(new File("id_rsa.pub"), "UTF-8");
        String strPub2 = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkkk5q+junsiy1J2pmKAA\n" +
                "wUvDgfgQfjeQZOIN5KXvyj2IxFaFje6LYaGdubrSGX8zyCodUZP7zgnqFaBpUojm\n" +
                "C8gvzY86WzaFfKS2uBh2+l8QLiDoz8EYXc3wucR93ZEY+aqK/cpxsTuy60dhwJ5c\n" +
                "vFlYh0UuWe7iBvw+I+jJsCAN5XsJ2sfbhtMvDMbhgs/f4DB+00y++QEWvY1lVGRG\n" +
                "7LWhHx8nbohtZl+lVIVAUXJOicjisV5Z253ixJQKr9elkshnFynIAmlAtPLZycWC\n" +
                "3fwFT0Xt1hZ7eMSn6HXf71B1Cc5u4VsytV/P9zDnwMKPBugaSriMzf8Sblgl+d53\n" +
                "1QIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        PemReader pemReader = new PemReader( new InputStreamReader(new ByteArrayInputStream(strPub2.getBytes())) );

        PemObject pemObject = pemReader.readPemObject();
        byte[]   publicKey2 = pemObject.getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicKey2);
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        PublicKey publicKey3 = factory.generatePublic(pubKeySpec);
        System.err.println(publicKey3);

        //////////////////////////////////////////////////////////////////////
        System.out.println("//////////////////////////////////////////////////");

        KeyPair    keyPair2 = generateRSAKeyPair();
        RSAPrivateKey priv2 = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey   pub2 = (RSAPublicKey) keyPair.getPublic();

        System.out.println(priv2);
        System.out.println(pub2);
    }

    public KeyPair generateRSAKeyPair()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(KEY_SIZE);

        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }

    public void writePemFile(Key key, String description, String filename)
            throws FileNotFoundException, IOException {
        PemFile pemFile = new PemFile(key, description);
        pemFile.write(filename);
    }

}

