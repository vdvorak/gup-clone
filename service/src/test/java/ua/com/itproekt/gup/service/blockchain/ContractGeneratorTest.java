package ua.com.itproekt.gup.service.blockchain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.util.FileKeyGeneratorUtil;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;


public class ContractGeneratorTest {

    public String     URL_PUSH_TRANSACTION; // BlockChain-Service: push-transaction
    public String         TYPE_TRANSACTION; // тип транзакции
    public String      ID_SELLER, ID_BUYER; // Seller (продавец: User-0); Buyer (покупатель: User-1) - иннициатор;
    private FileKeyGeneratorUtil KEY_BUYER; // ключ покупателя
    private ContractGenerator    generator;

    @Before
    public void setUp() {
        try {
            URL_PUSH_TRANSACTION = "http://gup.com.ua:3000/bc/push-transaction"; // BlockChain-Service: push-transaction
            TYPE_TRANSACTION     = "CONTRACT";                                   // тип транзакции
            ID_SELLER            = "587ca08e4c8e89327948309e";                   // Seller  (продавец: User-0)
            ID_BUYER             = "58cae20e4c8e9634fe40e852";                   // Buyer (покупатель: User-1) - иннициатор
            KEY_BUYER            = new FileKeyGeneratorUtil();                   // ключ покупателя
            generator            = new ContractGenerator(URL_PUSH_TRANSACTION);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test(s) Contract Post
     */
    @Test
    public void testContractPost() {
        try {
            okhttp3.Response response = generator.contractPost(TYPE_TRANSACTION, ID_SELLER, ID_BUYER, KEY_BUYER.getPrivateKey(), KEY_BUYER.getPublicKey(), "ul-drahomanova-dlia-odnoho-muzhchiny-ili-pary-bez-detei-h7");
            System.out.println("code: " + response.code());
            System.out.println("body: " + response.body().string());
        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
            System.err.println(e.getMessage());
        }
    }

}
