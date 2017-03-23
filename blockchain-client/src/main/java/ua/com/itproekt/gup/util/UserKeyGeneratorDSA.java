package ua.com.itproekt.gup.util;


import java.security.*;
import info.blockchain.api.blockexplorer.*;
import info.blockchain.api.blockexplorer.Address;
import info.blockchain.api.exchangerates.Currency;
import info.blockchain.api.exchangerates.ExchangeRates;
import info.blockchain.api.statistics.Statistics;
import info.blockchain.api.statistics.StatisticsResponse;
import info.blockchain.api.wallet.*;

import info.blockchain.api.receive.*;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Map;

public class UserKeyGeneratorDSA {

    public static void main(String[] args)
            throws NoSuchAlgorithmException, Exception {
        //////////////////////////////////////////////////////
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey   publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println( publicKey );
        System.out.println();
        System.out.println( privateKey );



//        byte[] publicBytes = Base64.decodeBase64(publicK);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey pubKey = keyFactory.generatePublic(keySpec);



        //////////////////////////////////////////////////////
//        BlockExplorer blockExplorer = new BlockExplorer();
//
//        // get a transaction by hash and list the value of all its inputs
//        Transaction tx = blockExplorer.getTransaction("df67414652722d38b43dcbcac6927c97626a65bd4e76a2e2787e22948a7c5c47");
//        for (Input i : tx.getInputs()) {
//            System.out.println("Transaction: " + i.getPreviousOutput().getValue());
//        }

    }

}
