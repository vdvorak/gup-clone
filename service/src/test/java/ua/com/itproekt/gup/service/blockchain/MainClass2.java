package ua.com.itproekt.gup.service.blockchain;

import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;


/**
* https://android.googlesource.com/platform/external/bouncycastle/+/2768c2948c0b1931bff087e43a8db8059c183b56/bcpkix/src/main/java/org/bouncycastle/operator/DefaultDigestAlgorithmIdentifierFinder.java
* http://stackoverflow.com/questions/521101/using-sha1-and-rsa-with-java-security-signature-vs-messagedigest-and-cipher#
*/

public class MainClass2 {

    public static void main(String[] args) {
        try {
            /////////////////////////////////////////////////////////////////////////
            boolean useBouncyCastleProvider = false;

            Provider provider = null;
            if (useBouncyCastleProvider) {
                provider = new BouncyCastleProvider();
                Security.addProvider(provider);
            }


            ///////////////////////////////////////////////////////////////////////// 0
            String plainText = "This is a plain text!!";


            /////////////////////////////////////////////////////////////////////////
            // Keys
            KeyPairGenerator keyPairGenerator = null;
            if (null != provider)
                keyPairGenerator = KeyPairGenerator.getInstance("RSA", provider);
            else
                keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair KEYS = keyPairGenerator.generateKeyPair();

            System.out.println( KEYS.getPublic() );
            System.out.println( Hex.toHexString(KEYS.getPublic().getEncoded()) );


            /////////////////////////////////////////////////////////////////////////
            // Signature
            Signature signatureProvider = null;
            if (null != provider)
                signatureProvider = Signature.getInstance("SHA256WithRSA", provider);
            else
                signatureProvider = Signature.getInstance("SHA256WithRSA");
            signatureProvider.initSign(KEYS.getPrivate());

            signatureProvider.update(plainText.getBytes());
            byte[] SIGNATURE = signatureProvider.sign();

            System.out.println("Signature Output : ");
            System.out.println("\t" + new String(Base64.encode(SIGNATURE)));


            /////////////////////////////////////////////////////////////////////////
            // Message Digest
            String hashingAlgorithm = "SHA-256";
            MessageDigest messageDigestProvider = null;
            if (null != provider)
                messageDigestProvider = MessageDigest.getInstance(hashingAlgorithm, provider);
            else
                messageDigestProvider = MessageDigest.getInstance(hashingAlgorithm);
            messageDigestProvider.update(plainText.getBytes());

            byte[] hash = messageDigestProvider.digest();

            DefaultDigestAlgorithmIdentifierFinder hashAlgorithmFinder = new DefaultDigestAlgorithmIdentifierFinder();
            AlgorithmIdentifier hashingAlgorithmIdentifier = hashAlgorithmFinder.find(hashingAlgorithm);

            DigestInfo digestInfo = new DigestInfo(hashingAlgorithmIdentifier, hash);
            byte[] hashToEncrypt = digestInfo.getEncoded();

            System.out.println( KEYS.getPrivate() );


            /////////////////////////////////////////////////////////////////////////
            // Crypto
            Cipher encCipher = null;
            if (null != provider)
                encCipher = Cipher.getInstance("RSA/NONE/PKCS1Padding", provider);
            else
                encCipher = Cipher.getInstance("RSA");
            encCipher.init(Cipher.ENCRYPT_MODE, KEYS.getPrivate());

            byte[] ENCRYPTED = encCipher.doFinal(hashToEncrypt);

            System.out.println("Hash and Encryption Output : ");
            System.out.println("\t" + new String(Base64.encode(ENCRYPTED)));
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

}
