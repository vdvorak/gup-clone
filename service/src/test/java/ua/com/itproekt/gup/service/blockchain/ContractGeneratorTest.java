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
//        FILE_PUBLIC_KEY = "id_rsa.pub";
        FILE_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiX6KfrTp0Nl83SYfhfIL\n" +
                "yo5IsH++yj7/U6yPsGhF2JMLIlQMI2zg0Ap1p3NvfVynhuP/gYYFeuhUJly4lhFl\n" +
                "MohoJefiXuO1GmKjfkJ6lyEbjRQS2ZlGZSryoS85VZJBvL+RhFnirpMpD3DvfhT5\n" +
                "F6SyX3Re8N93KOZ/ad6ntdavOvHMAc2sBIcxqnBD3szEjuyvG+lPwaw4eS/YTNgB\n" +
                "b1wtxJgLFaPOMPW/3dEkO1LFLeBse5dveI9LICxB2xkhii1xKDRrN/jn/dfasDUO\n" +
                "xWI+RinWOagR8Tx5Gd53+QO+Aah7MSr4s3mrAzADqQCzEqDdD47djcnzzimhzA8d\n" +
                "fwIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";

        generator = new ContractGenerator(REST_URL);
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
//        generator.contractPost("CONTRACT", USER_1, USER_2, FILE_PUBLIC_KEY, "I have sell you product");

//        try {
//            HttpserviceImpl httpservice = new HttpserviceImpl("http://apilayer.net/api/live?access_key=000000000000000000000000");
//            JSONObject resp = httpservice.getResponse(HttpMethods.GET);
//            System.err.println( resp );
//        } catch (JSONException | ServiceException e){
//
//        }
    }

}
