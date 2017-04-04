package ua.com.itproekt.gup.util;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

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

public class RSAKeyGenerator2 {

    public static final int   LENGTH_PUBLIC_KEY = 2048;
    public static final String FILE_PRIVATE_KEY = "id_rsa";
    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";
    public static final String DESC_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String  DESC_PUBLIC_KEY = "RSA PUBLIC KEY";

    private KeyPair keyPair;
    private String publicKey;
    private String privateKey;

    public RSAKeyGenerator2()
            throws NoSuchProviderException, NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        keyPair = generateRSAKeyPair();
    }

    public String getPublicHash()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        if (publicKey!=null){
            MessageDigest   msg = MessageDigest.getInstance("SHA-256"); // 1. Указываем тип шифрования ( формат: SHA-256 )
            byte[]   bytePublic = publicKey.getBytes("UTF-8");          // 2. Переводим данные в байт-код
            byte[] digestPublic = msg.digest(bytePublic);               // 3. шифруем ( формат: SHA-256 )
            return Hex.toHexString(digestPublic);
        }
        return null;
    }

    public String getPublicKey()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        PemWriter writer = new PemWriter(new OutputStreamWriter(new FileOutputStream(FILE_PUBLIC_KEY)));
        PemObject obj = new PemObject(DESC_PUBLIC_KEY, keyPair.getPublic().getEncoded());
        writer.writeObject(obj);
        writer.close();

        publicKey = new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
        if (new File(FILE_PUBLIC_KEY).delete()) return publicKey;
        return "Delete operation is failed";
    }

    public String getPrivateKey()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        PemWriter writer = new PemWriter(new OutputStreamWriter(new FileOutputStream(FILE_PRIVATE_KEY)));
        PemObject obj = new PemObject(DESC_PRIVATE_KEY, keyPair.getPrivate().getEncoded());
        writer.writeObject(obj);
        writer.close();

        privateKey = new String(Files.readAllBytes(Paths.get(FILE_PRIVATE_KEY)));
        if (new File(FILE_PRIVATE_KEY).delete()) return privateKey;
        return "Delete operation is failed";
    }


    private KeyPair generateRSAKeyPair()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(LENGTH_PUBLIC_KEY);

        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }
}
