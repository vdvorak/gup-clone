package ua.com.itproekt.gup.util;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class FileKeyGeneratorUtil {

    private KeyPair key;
    protected static final String FILE_PUBLIC_KEY = "id_rsa.pub";
//    protected static final String FILE_PUBLIC_KEY = "/opt/tomcats/tomcat_gup_service_test_offers/temp/id_rsa.pub";
    protected static final int           KEY_SIZE = 2048;
    protected final static Logger LOGGER = Logger.getLogger(FileKeyGeneratorUtil.class);

    public FileKeyGeneratorUtil()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(KEY_SIZE);
        key = generator.generateKeyPair();
    }

    public String getPublicKey()
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException {
        RSAPublicKey publicKey = (RSAPublicKey) key.getPublic();
        FileKeyGeneratorUtil.write(publicKey, "PUBLIC KEY", FILE_PUBLIC_KEY);
        FileKey filePublicKey = new FileKey();
        return filePublicKey.read(FILE_PUBLIC_KEY);
    }

    public PrivateKey getPrivateKey()
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException {
        return (RSAPrivateKey) key.getPrivate();
    }

    public static KeyPair generateRSAKey()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(KEY_SIZE);

        return generator.generateKeyPair();
    }

    public static void write(Key key, String description, String filename)
            throws FileNotFoundException, IOException {
        FileKey file = new FileKey(key, description);
        LOGGER.info("key="+key+"; filename="+filename+"; description=\""+description+"\";");
        file.write(filename);
    }

}
