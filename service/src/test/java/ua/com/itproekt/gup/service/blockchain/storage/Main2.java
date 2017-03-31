//package ua.com.itproekt.gup.service.blockchain.storage;
//
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
//
//public class Main2 {
//
//    protected final static Logger     LOGGER = Logger.getLogger(Main2.class);
//    public final static String RESOURCES_DIR = "D:\\IdeaProjects\\GUP\\gup\\"; //"D:\\IdeaProjects\\GUP\\__demo__\\";
//
//    public static void main(String[] args)
//            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException
//    {
//        Security.addProvider(new BouncyCastleProvider());
//        LOGGER.info("BouncyCastle provider added.");
//
//        KeyFactory factory = KeyFactory.getInstance("RSA", "BC"); //KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
//        try {
//            PrivateKey priv = generatePrivateKey(factory, RESOURCES_DIR + "id_rsa");
//            LOGGER.info(String.format("Instantiated private key: %s", priv));
//
//            PublicKey   pub = generatePublicKey(factory, RESOURCES_DIR + "id_rsa.pub");
//            LOGGER.info(String.format("Instantiated public key: %s", pub));
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static PrivateKey generatePrivateKey(KeyFactory factory, String filename)
//            throws InvalidKeySpecException, FileNotFoundException, IOException
//    {
//        PemFile2                pemFile = new PemFile2(filename);
//        byte[]                  content = pemFile.getPemObject().getContent();
//        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
//        return factory.generatePrivate(privKeySpec);
//    }
//
//    private static PublicKey generatePublicKey(KeyFactory factory, String filename)
//            throws InvalidKeySpecException, FileNotFoundException, IOException
//    {
//        PemFile2              pemFile = new PemFile2(filename);
////        byte[]                content = pemFile.getPemObject().getContent();
//        try {
//            byte[]                content = pemFile.getPemObject().getContent();
//            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
//            return factory.generatePublic(pubKeySpec);
//        } catch (NullPointerException e){}
//
//        return null;
//    }
//
//}
//
//
//class PemFile2 {
//    private PemObject pemObject;
//
//    public PemFile2(String filename)
//            throws FileNotFoundException, IOException
//    {
//        PemReader pemReader = new PemReader( new InputStreamReader(new FileInputStream(filename)) );
//        try {
//            this.pemObject = pemReader.readPemObject();
//        } finally {
//            pemReader.close();
//        }
//    }
//
//    public PemObject getPemObject() {
//        return pemObject;
//    }
//}
