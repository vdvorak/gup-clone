//package ua.com.itproekt.gup.model.order.blockchain_test.storageOK;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.security.KeyFactory;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.Security;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
//import org.apache.log4j.Logger;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.util.io.pem.PemObject;
//import org.bouncycastle.util.io.pem.PemReader;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.testng.Assert.fail;
//
//
//public class ReaderKeyTest {
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown(){
//    }
//
//    @Test
//    public void testReaderKey()
//            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException
//    {
//        Security.addProvider(new BouncyCastleProvider());
//        System.err.println("BouncyCastle provider added.");
//
//        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
//        try {
//            PrivateKey priv = generatePrivateKey(factory, "id_rsa");
//            System.err.println(String.format("Instantiated private key: %s", priv));
//
//            PublicKey   pub = generatePublicKey(factory, "id_rsa.pub");
//            System.err.println(String.format("Instantiated public key: %s", pub));
//        } catch (InvalidKeySpecException e) {
//            fail("Exception not thrown");
//        }
//    }
//
//    private PrivateKey generatePrivateKey(KeyFactory factory, String filename)
//            throws InvalidKeySpecException, FileNotFoundException, IOException
//    {
//        PemFile2                pemFile = new PemFile2(filename);
//        byte[]                  content = pemFile.getPemObject().getContent();
//        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
//        return factory.generatePrivate(privKeySpec);
//    }
//
//    private PublicKey generatePublicKey(KeyFactory factory, String filename)
//            throws InvalidKeySpecException, FileNotFoundException, IOException
//    {
//        PemFile2              pemFile = new PemFile2(filename);
//        try {
//            byte[]                content = pemFile.getPemObject().getContent();
//            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
//            return factory.generatePublic(pubKeySpec);
//        } catch (NullPointerException e){}
//
//        return null;
//    }
//
//    class PemFile2 {
//        private PemObject pemObject;
//
//        public PemFile2(String filename)
//                throws FileNotFoundException, IOException
//        {
//            PemReader pemReader = new PemReader( new InputStreamReader(new FileInputStream(filename)) );
//            try {
//                this.pemObject = pemReader.readPemObject();
//            } finally {
//                pemReader.close();
//            }
//        }
//
//        public PemObject getPemObject() {
//            return pemObject;
//        }
//    }
//}
