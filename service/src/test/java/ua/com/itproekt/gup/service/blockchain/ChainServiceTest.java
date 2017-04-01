package ua.com.itproekt.gup.service.blockchain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.service.blockchain.TransactionService;
import ua.com.itproekt.gup.service.blockchain.contract.ContractTransactionService;
import ua.com.itproekt.gup.util.FileKeyGeneratorUtil;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;


public class ChainServiceTest {

    public String  ID_SELLER, ID_BUYER; // Seller (продавец: User-0); Buyer (покупатель: User-1) - иннициатор;
    private TransactionService service;

    @Before
    public void setUp() {
        ID_SELLER = "587ca08e4c8e89327948309e"; // Seller  (продавец: User-0)
        ID_BUYER  = "58cae20e4c8e9634fe40e852"; // Buyer (покупатель: User-1) - иннициатор
    }

    @After
    public void tearDown() {
    }

    /**
     * Test Contract-Transaction Service
     */
    @Test
    public void testContractTransactionService() {
        try {
            service = new ContractTransactionService(new String[] {ID_SELLER, ID_BUYER}, "ul-drahomanova-dlia-odnoho-muzhchiny-ili-pary-bez-detei-h7");

            Response response = service.postTransaction();
            System.err.println("code: " + response.code());
            System.err.println("body: " + response.body().string());
        } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
            System.err.println(e.getMessage());
        }
    }

}
