package ua.com.itproekt.gup.service.blockchain.storage;

/**
 * http://snipplr.com/view/18368/
 */

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Adam {

    public static void main(String args[]) {
        Adam adam = new Adam();

        try {
            String path = "D:\\IdeaProjects\\GUP\\gup"; //String path = "C:\\Documents and Settings\\george\\My Documents\\workspaces\\gsoc09\\playground\\tmp";

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); // DSA

//            KeyFactory   keyFactory = KeyFactory.getInstance("RSA", "BC"); // ECDsA, BC
//            ECParameterSpec   ecParameterSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
//            ECDomainParameters ecDomainParams = new ECDomainParameters(
//                ecParameterSpec.getCurve(),
//                ecParameterSpec.getG(),
//                ecParameterSpec.getN(),
//                ecParameterSpec.getH(),
//                ecParameterSpec.getSeed());
//            ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec( new BigInteger(Base64.decode("XfC4pPna8BsQrcrC6FuFXgnqSgVHjfUrxW")), ecParameterSpec );
//            PrivateKey             privateKey = keyFactory.generatePrivate(ecPrivateKeySpec);

            keyGen.initialize(2048); // 1024
            KeyPair generatedKeyPair = keyGen.genKeyPair();

            System.out.println("Generated Key Pair");
            adam.dumpKeyPair(generatedKeyPair);
            adam.SaveKeyPair(path, generatedKeyPair);

            KeyPair loadedKeyPair = adam.LoadKeyPair(path, "RSA"); // DSA
            System.out.println("Loaded Key Pair");
            adam.dumpKeyPair(loadedKeyPair);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void dumpKeyPair(KeyPair keyPair) {
        PublicKey pub = keyPair.getPublic();
        System.out.println("Public Key: " + getHexString(pub.getEncoded()));

        PrivateKey priv = keyPair.getPrivate();
        System.out.println("Private Key: " + getHexString(priv.getEncoded()));
    }

    private String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public void SaveKeyPair(String path, KeyPair keyPair) throws IOException {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey   publicKey = keyPair.getPublic();

        // Store Public Key.
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        FileOutputStream                  fos = new FileOutputStream(path + "/public.key");
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();
        ////////////////////////////////////////////////////////////////
        /*
         * id_rsa.pub
         * **********
         * 2048
         *
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDQhsKe+bd+zluPJJQk6pRhom/1PxPPmFKuSc4AneaypsMUeJNbgLzrky0sGV6El+N1w6wwaj2iqBZxrpjfw7qPBb1QMPK1nwOic5HVsjoCmjwuABmLUI17uFg4eyPw35Ry4zEzu0WGrimofLFttuhgxXLzojprJ+QrB2SNIOAQiY28uWPz5tikt/EB79SCue3W+H9D7F5XJNEVHyHV+1yeU/ef/dKknsgnxvQyLvJj5/Q+uOJhEK3rgM0R8RaVU4/iISeDsE1tja4MJx5M1xBjA39Q2UjrN7QnhPhCJxMm9jTOiMRk36VEbRraL4uibIkReTdPujO6qhEJoRXFxwgk5N1runO0pfx0JzLl0v5vGA4i5w+3/McgsCihmnJPK9BsROeR1FGD7mdyj8fX5HwTZnUsE1OtcVjdpyRUecWuuLXTXdTnvqRAyHFu7Ci16AjJVwXhTOvVJNNbeX3RfyAPbfwcg5Z9jwMs01oLc/ZsHhiS+dYWMgs6ug250Bfup4owfv398U5bFP8r/gPW59Ijhxktvz96GO3zGybZboE9SSUwA/UoU2wS7Unv87bWGWwuYLYNsZV+eE0/6irBannlQ2lDtygBjZ+Fbu1cVpwzK/3h2X0Q6V7yRxASYcCTETFvYhmSCUvQicxaKMOYkMcPyVa/zk0uDkxXUg69Hs8uLQ== gup@work-server.com
         */
        String                      strPublic = "rsa " + getHexString(publicKey.getEncoded()) + " gup@work-server.com"; // PemObject pemObjPublic = new PemObject("RSA PUBLIC KEY", publicKey.getEncoded());
        PemWriter                        fos2 = new PemWriter(new OutputStreamWriter(new FileOutputStream(path + "/id_rsa.pub")));
        fos2.write(strPublic); //fos2.writeObject(pemObjPublic);
        fos2.close();

        // Store Private Key.
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        fos                                     = new FileOutputStream(path + "/private.key");
        fos.write(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
        ////////////////////////////////////////////////////////////////
        /*
         * id_rsa
         *
-----BEGIN RSA PRIVATE KEY-----
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
-----END RSA PRIVATE KEY-----
         */
        PemObject               pemObjPrivate = new PemObject("RSA PRIVATE KEY", publicKey.getEncoded());
        fos2                                  = new PemWriter(new OutputStreamWriter(new FileOutputStream(path + "/id_rsa")));
        fos2.writeObject(pemObjPrivate);
        fos2.close();
    }

    public KeyPair LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        // Read Public Key.
        File      filePublicKey = new File(path + "/public.key");
        FileInputStream     fis = new FileInputStream(path + "/public.key");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();

        // Read Private Key.
        File      filePrivateKey = new File(path + "/private.key");
        fis                      = new FileInputStream(path + "/private.key");
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();
        ////////////////////////////////////////////////////////////////

//        PemWriter                        fos2 = new PemWriter(new OutputStreamWriter(new FileOutputStream(path + "/id_rsa.pub")));
//        fos2.writeObject(pemObjPublic);
//        fos2.close();
//
//        PemObject               pemObjPrivate = new PemObject("RSA PRIVATE KEY", publicKey.getEncoded());
//        fos2                                  = new PemWriter(new OutputStreamWriter(new FileOutputStream(path + "/id_rsa")));
//        fos2.writeObject(pemObjPrivate);
//        fos2.close();

//        File      filePrivateKey = new File(path + "/private.key");
//        fis                      = new FileInputStream(path + "/private.key");
//        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
//        fis.read(encodedPrivateKey);
//        fis.close();

////        File      filePrivateKey2 = new File(path + "/id_rsa");
//        Reader filePrivateKey2 = new Reader();
////        PemReader fis2 = new PemReader();




        // Generate KeyPair.
        KeyFactory            keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        PublicKey              publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        PrivateKey              privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

}
