package ua.com.gup.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.apache.log4j.Logger;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
import java.io.*;


public class RSAKeyGenerator {

    protected final static Logger        LOGGER = Logger.getLogger(RSAKeyGenerator.class);
    public static final int   LENGTH_PUBLIC_KEY = 2048;
    public static final String FILE_PRIVATE_KEY = "id_rsa";
    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";
    public static final String DESC_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String  DESC_PUBLIC_KEY = "RSA PUBLIC KEY";

    public RSAKeyGenerator()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException
    {
        Security.addProvider(new BouncyCastleProvider());

        KeyPair pair = keyPairGenerator();
        write(pair.getPrivate(), DESC_PRIVATE_KEY, FILE_PRIVATE_KEY);
        write(pair.getPublic(), DESC_PUBLIC_KEY, FILE_PUBLIC_KEY);

        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        LOGGER.info(String.format("read from 'id_rsa' file: %s", readPrivate(factory, FILE_PRIVATE_KEY).equals(pair.getPrivate())));
        LOGGER.info(String.format("read from 'id_rsa.pub' file: %s", readPublic(factory, FILE_PUBLIC_KEY).equals(pair.getPublic())));
    }

    public PrivateKey getPrivate()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException
    {
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        return readPrivate(factory, FILE_PRIVATE_KEY);
    }

    public PublicKey getPublic()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException
    {
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        return readPublic(factory, FILE_PUBLIC_KEY);
    }

    public String readPrivate()
            throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(FILE_PRIVATE_KEY)));
    }

    public String readPublic()
            throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
    }

    private KeyPair keyPairGenerator()
            throws NoSuchAlgorithmException, NoSuchProviderException
    {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(LENGTH_PUBLIC_KEY);
        KeyPair keyPair = generator.generateKeyPair();

        LOGGER.info(String.format("generate RSA-Key, length: %s", LENGTH_PUBLIC_KEY));
        return keyPair;
    }

    private void write(Key key, String description, String filename)
            throws FileNotFoundException, IOException
    {
        PemFile file = new PemFile(key, description);
        file.write(filename);
        LOGGER.info(String.format("write to file: %s", filename));
    }

    private PrivateKey readPrivate(KeyFactory factory, String filename)
            throws InvalidKeySpecException, FileNotFoundException, IOException
    {
        PemFile                       file = new PemFile(filename);
        byte[]                     content = file.getPemObject().getContent();
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privateKeySpec);
    }

    private PublicKey readPublic(KeyFactory factory, String filename)
            throws InvalidKeySpecException, FileNotFoundException, IOException
    {
        PemFile                      file = new PemFile(filename);
        try {
            byte[]                content = file.getPemObject().getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return factory.generatePublic(pubKeySpec);
        } catch (NullPointerException e){}

        return null;
    }


    class PemFile {

        private PemObject pemObject;

        public PemFile(String filename)
                throws FileNotFoundException, IOException
        {
            PemReader pemReader = new PemReader( new InputStreamReader(new FileInputStream(filename)) );
            try {
                this.pemObject = pemReader.readPemObject();
            } finally {
                pemReader.close();
            }
        }

        public PemFile(Key key, String description) {
            pemObject = new PemObject(description, key.getEncoded());
        }

        public PemObject getPemObject() {
            return pemObject;
        }

        public void write(String filename)
                throws FileNotFoundException, IOException {
            PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            try {
                pemWriter.writeObject(pemObject);
            } finally {
                pemWriter.close();
            }
        }
    }
}

