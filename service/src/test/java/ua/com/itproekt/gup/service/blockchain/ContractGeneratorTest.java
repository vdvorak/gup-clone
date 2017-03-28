package ua.com.itproekt.gup.service.blockchain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import ua.com.itproekt.gup.service.blockchain.contract.Contract;
import ua.com.itproekt.gup.service.blockchain.contract.Transaction;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionData;
import ua.com.itproekt.gup.service.blockchain.contract.TransactionDataSignature;
import ua.com.itproekt.gup.util.FileKeyGeneratorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;


public class ContractGeneratorTest {

    public String USER_1, USER_2;
    public String        REST_URL;
    public String FILE_PUBLIC_KEY;

    private ContractGenerator generator;

    @Before
    public void setUp() {
        REST_URL = "http://gup.com.ua:3000/bc/push-transaction";
        USER_1 = "587ca08e4c8e89327948309e";
        USER_2 = "58cae20e4c8e9634fe40e852";
        FILE_PUBLIC_KEY = "id_rsa.pub";

        generator = new ContractGenerator("http://gup.com.ua:3000/bc/push-transaction");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test(s) Contract Post
     */
    @Test
    public void testContractPost()
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException {
        // Show the response.
        Response response = generator.contractPost("CONTRACT", USER_1, USER_2, FILE_PUBLIC_KEY, "I have sell you product");
        System.out.println("code: " + response.code());
        System.out.println("body: " + response.body().string());
    }

}
