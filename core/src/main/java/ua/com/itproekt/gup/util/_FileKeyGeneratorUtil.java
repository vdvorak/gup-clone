//package ua.com.itproekt.gup.util;
//
//import org.apache.log4j.Logger;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.util.encoders.Hex;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.security.*;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//
//
//public class FileKeyGeneratorUtil {
//
//    private KeyPair key;
//    private final String FILE_PUBLIC_KEY = "id_rsa.pub";
//
//    public FileKeyGeneratorUtil()
//            throws NoSuchAlgorithmException, NoSuchProviderException {
//        Security.addProvider(new BouncyCastleProvider());
//
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
//        generator.initialize(KEY_SIZE);
//        key = generator.generateKeyPair();
//    }
//
//    public String getPublicKey()
//            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException {
//        RSAPublicKey publicKey = (RSAPublicKey) key.getPublic();
//        FileKeyGeneratorUtil.write(publicKey, "PUBLIC KEY", FILE_PUBLIC_KEY);
//        FileKey filePublicKey = new FileKey();
//        return filePublicKey.read(FILE_PUBLIC_KEY);
//    }
//
//    public PrivateKey getPrivateKey()
//            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException {
//        return (RSAPrivateKey) key.getPrivate();
//    }
//
//    protected final static Logger LOGGER = Logger.getLogger(FileKeyGeneratorUtil.class);
//    public static final int     KEY_SIZE = 2048;
//
//    public static KeyPair generateRSAKey()
//            throws NoSuchAlgorithmException, NoSuchProviderException {
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
//        generator.initialize(KEY_SIZE);
//
//        return generator.generateKeyPair();
//    }
//
//    public static void write(Key key, String description, String filename)
//            throws FileNotFoundException, IOException {
//        FileKey file = new FileKey(key, description);
//        LOGGER.info("key="+key+"; filename="+filename+"; description=\""+description+"\";");
//        file.write(filename);
//    }
//
//    public static String hmacDigest(String msg, String key, String algo) {
//        String digest = null;
//        try {
//            SecretKeySpec secretKey = new SecretKeySpec((key).getBytes("UTF-8"), algo);
//            Mac             macAlgo = Mac.getInstance(algo);
//            macAlgo.init(secretKey);
//
//            byte[]          byteMsg = macAlgo.doFinal(msg.getBytes("ASCII"));
//            StringBuffer       hash = new StringBuffer();
//            for (int i=0; i<byteMsg.length; i++) {
//                String hex = Integer.toHexString(0xFF & byteMsg[i]);
//                if (hex.length() == 1) {
//                    hash.append('0');
//                }
//                hash.append(hex);
//            }
//            digest = hash.toString();
//        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {}
//        return digest;
//    }
//
//}
