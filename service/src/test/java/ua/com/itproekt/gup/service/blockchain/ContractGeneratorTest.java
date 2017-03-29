package ua.com.itproekt.gup.service.blockchain;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.itproekt.gup.model.xchangerate.service.HttpMethods;
import ua.com.itproekt.gup.model.xchangerate.service.HttpserviceImpl;
import ua.com.itproekt.gup.model.xchangerate.service.ServiceException;
import ua.com.itproekt.gup.service.blockchain.contract.Contract;
import ua.com.itproekt.gup.service.blockchain.contract.Transaction;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionDataSignature;
import ua.com.itproekt.gup.util.FileKey;
import ua.com.itproekt.gup.util.FileKeyGeneratorUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import javax.net.ssl.*;


public class ContractGeneratorTest {

    public final String FILE_PRIVATE_KEY = "id_rsa";
    public final String  FILE_PUBLIC_KEY = "id_rsa.pub";
    public String             PUBLIC_KEY; // публичный ключ покупателя
    public String   URL_PUSH_TRANSACTION; // BlockChain-Service: push-transaction
    public String       TYPE_TRANSACTION; // тип транзакции
    public String    ID_SELLER, ID_BUYER; // Seller (продавец: User-0); Buyer (покупатель: User-1) - иннициатор;

    private ContractGenerator generator;

    @Before
    public void setUp() {
        URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction
        ID_SELLER = "587ca08e4c8e89327948309e";                              // Seller  (продавец: User-0)
        ID_BUYER = "58cae20e4c8e9634fe40e852";                               // Buyer (покупатель: User-1) - иннициатор
        PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +                        // публичный ключ покупателя
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiX6KfrTp0Nl83SYfhfIL\n" +
                "yo5IsH++yj7/U6yPsGhF2JMLIlQMI2zg0Ap1p3NvfVynhuP/gYYFeuhUJly4lhFl\n" +
                "MohoJefiXuO1GmKjfkJ6lyEbjRQS2ZlGZSryoS85VZJBvL+RhFnirpMpD3DvfhT5\n" +
                "F6SyX3Re8N93KOZ/ad6ntdavOvHMAc2sBIcxqnBD3szEjuyvG+lPwaw4eS/YTNgB\n" +
                "b1wtxJgLFaPOMPW/3dEkO1LFLeBse5dveI9LICxB2xkhii1xKDRrN/jn/dfasDUO\n" +
                "xWI+RinWOagR8Tx5Gd53+QO+Aah7MSr4s3mrAzADqQCzEqDdD47djcnzzimhzA8d\n" +
                "fwIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";

        generator = new ContractGenerator(URL_PUSH_TRANSACTION);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateKeys()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPair           rsaKey = FileKeyGeneratorUtil.generateRSAKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) rsaKey.getPrivate();
        RSAPublicKey   publicKey = (RSAPublicKey) rsaKey.getPublic();

//        FileKeyGeneratorUtil.write(privateKey, "PRIVATE KEY", FILE_PRIVATE_KEY);
        FileKeyGeneratorUtil.write(publicKey, "PUBLIC KEY", FILE_PUBLIC_KEY);

        FileKey filePublicKey = new FileKey();
        System.out.println( filePublicKey.read(FILE_PUBLIC_KEY) );
    }

    /**
     * Test(s) Contract Post
     */
    @Test
    public void testContractPost()
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
        // Show the response.
        TYPE_TRANSACTION = "CONTRACT"; // тип транзакции

        Response response = generator.contractPost(TYPE_TRANSACTION, ID_SELLER, ID_BUYER, PUBLIC_KEY, "ul-drahomanova-dlia-odnoho-muzhchiny-ili-pary-bez-detei-h7");

        System.out.println("code: " + response.code());
        System.out.println("body: " + response.body().string());
    }

}
