package ua.com.gup.util;

import org.apache.log4j.Logger;


public class RSAKeyUtil {

    protected final static Logger        LOGGER = Logger.getLogger(RSAKeyGenerator.class);
    public static final int   LENGTH_PUBLIC_KEY = 2048;
    public static final String FILE_PRIVATE_KEY = "id_rsa";
    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";
    public static final String DESC_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String  DESC_PUBLIC_KEY = "RSA PUBLIC KEY";


}
