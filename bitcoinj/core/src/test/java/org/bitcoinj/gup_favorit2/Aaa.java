package org.bitcoinj.gup_favorit2;


import java.security.*;

/**
 * @see bhttp://www.programcreek.com/java-api-examples/java.security.KeyPairGenerator
 */
public class Aaa {

    public static void main(String[] args) throws Exception {
        ////////////////////////////////////////////////////////////
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

//        System.out.println("priv: " + priv);
//        System.out.println("pub: " + pub);

        ////////////////////////////////////////////////////////////

    }

//    public static KeyPairGenerator publicKeyGenInstance() {
//        try {
//            KeyPairGenerator result =
//                    KeyPairGenerator.getInstance(ALG_PUBLIC_KEY_GEN);
//            result.initialize(SIZE_PUBLIC_KEY);
//            return result;
//        } catch (NoSuchAlgorithmException e) {
//            throw new InternalError(e);
//        }
//    }

}
