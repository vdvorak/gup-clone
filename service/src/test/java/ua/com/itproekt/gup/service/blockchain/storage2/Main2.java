//package ua.com.itproekt.gup.service.blockchain.storage2;
//
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.util.encoders.Hex;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.security.Key;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.Security;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//
//
///**
// * @see https://www.txedo.com/blog/java-generate-rsa-keys-write-pem-file/
// */
//
//public class Main2 {
//
//    public static final int     KEY_SIZE = 2048; // 1024
//
//    public static void main(String[] args)
//            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
//        Security.addProvider(new BouncyCastleProvider());
//
//        KeyPair    keyPair = generateRSAKeyPair();
//        RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
//        RSAPublicKey   pub = (RSAPublicKey) keyPair.getPublic();
//
//        writePemFile(priv, "PRIVATE KEY", "id_rsa"); //writePemFile(priv, "RSA PRIVATE KEY", "id_rsa");
//        writePemFile(pub, "PUBLIC KEY", "id_rsa.pub"); //writePemFile(pub, "RSA PUBLIC KEY", "id_rsa.pub");
//
//        /////////////////////////////////
//        String content = new String(Files.readAllBytes(Paths.get("id_rsa.pub")));
//        System.err.println( content );
//        System.out.println( );
//
//        PemFile        pemFile = new PemFile();
//        final byte[] publicKey = pemFile.read1("id_rsa.pub").getContent();
//        System.out.println("PUBLIC   " + Hex.toHexString(publicKey));
//        System.out.println();
//        System.out.println( pemFile.read2("id_rsa.pub") );
//
//        //////////////////////////////////////////////////////////////////////
//        KeyPair    keyPair2 = generateRSAKeyPair();
//        RSAPrivateKey priv2 = (RSAPrivateKey) keyPair.getPrivate();
//        RSAPublicKey   pub2 = (RSAPublicKey) keyPair.getPublic();
//
//        System.out.println("//////////////////////////////////////");
//        System.out.println();
//
//    }
//
//    public static KeyPair generateRSAKeyPair()
//            throws NoSuchAlgorithmException, NoSuchProviderException {
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
//        generator.initialize(KEY_SIZE);
//
//        KeyPair keyPair = generator.generateKeyPair();
//        return keyPair;
//    }
//
//    public static void writePemFile(Key key, String description, String filename)
//            throws FileNotFoundException, IOException {
//        PemFile pemFile = new PemFile(key, description);
//        pemFile.write(filename);
//    }
//
//}
//
