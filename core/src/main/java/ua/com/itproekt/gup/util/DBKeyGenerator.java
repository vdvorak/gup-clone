package ua.com.itproekt.gup.util;

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


public class DBKeyGenerator {

    protected final static Logger        LOGGER = Logger.getLogger(FileKeyGenerator.class);
    public static final int   LENGTH_PUBLIC_KEY = 2048;
    public static final String FILE_PRIVATE_KEY = "id_rsa";
    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";
    public static final String DESC_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String  DESC_PUBLIC_KEY = "RSA PUBLIC KEY";


}
