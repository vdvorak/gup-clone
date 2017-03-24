package org.bitcoinj.gup_favorit;

import org.bitcoin.protocols.payments.Protos;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.params.UnitTestParams;
import org.bitcoinj.protocols.payments.PaymentProtocol;
import org.bitcoinj.protocols.payments.PaymentSession;
import org.bitcoinj.script.ScriptBuilder;

import java.security.*;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.bitcoin.protocols.payments.Protos.Payment;
import org.bitcoinj.core.*;
import org.bitcoinj.protocols.payments.PaymentProtocol.Output;
import org.bitcoinj.testing.FakeTxBuilder;
import java.util.LinkedList;


public class Main {

    private static final NetworkParameters NETWORK_PARAMS = UnitTestParams.get();
    private static final Coin AMOUNT = Coin.SATOSHI;
    private static final Address TO_ADDRESS = new ECKey().toAddress(NETWORK_PARAMS);
    private static final String MEMO = "memo";
    private static final String PAYMENT_URL = "https://example.com";
    private static final byte[] MERCHANT_DATA = { 0, 1, 2 };

    public static void main(String[] args) {
        try {
            System.out.println( generatorPubKey("RSA",2048) );
            System.out.println( generatorPrivKey() );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static PublicKey generatorPubKey(String algorithm, int size)
            throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(size);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair.getPublic();
    }

    public static String generatorPrivKey() {
        ECKey key = new ECKey();
        return key.getPublicKeyAsHex();
    }

    private Protos.PaymentRequest minimalPaymentRequest() {
        Protos.PaymentDetails.Builder paymentDetails = Protos.PaymentDetails.newBuilder();
        paymentDetails.setTime(System.currentTimeMillis());
        Protos.PaymentRequest.Builder paymentRequest = Protos.PaymentRequest.newBuilder();
        paymentRequest.setSerializedPaymentDetails(paymentDetails.build().toByteString());
        return paymentRequest.build();
    }

    public void testPaymentRequest() throws Exception {
        Protos.PaymentRequest paymentRequest = PaymentProtocol.createPaymentRequest(TestNet3Params.get(), AMOUNT, TO_ADDRESS, MEMO,
                PAYMENT_URL, MERCHANT_DATA).build();
        byte[] paymentRequestBytes = paymentRequest.toByteArray();

        PaymentSession parsedPaymentRequest = PaymentProtocol.parsePaymentRequest(Protos.PaymentRequest
                .parseFrom(paymentRequestBytes));
        final List<Output> parsedOutputs = parsedPaymentRequest.getOutputs();
        assertEquals(1, parsedOutputs.size());
        assertEquals(AMOUNT, parsedOutputs.get(0).amount);
        assertArrayEquals(ScriptBuilder.createOutputScript(TO_ADDRESS).getProgram(), parsedOutputs.get(0).scriptData);
        assertEquals(MEMO, parsedPaymentRequest.getMemo());
        assertEquals(PAYMENT_URL, parsedPaymentRequest.getPaymentUrl());
        assertArrayEquals(MERCHANT_DATA, parsedPaymentRequest.getMerchantData());
    }

    public void testPaymentMessage() throws Exception {
        List<Transaction> transactions = new LinkedList<>();
        transactions.add(FakeTxBuilder.createFakeTx(NETWORK_PARAMS, AMOUNT, TO_ADDRESS));
        Coin refundAmount = Coin.SATOSHI;
        Address refundAddress = new ECKey().toAddress(NETWORK_PARAMS);
        Payment payment = PaymentProtocol.createPaymentMessage(transactions, refundAmount, refundAddress, MEMO,
                MERCHANT_DATA);
        byte[] paymentBytes = payment.toByteArray();

        Protos.Payment parsedPayment = Protos.Payment.parseFrom(paymentBytes);
        List<Transaction> parsedTransactions = PaymentProtocol.parseTransactionsFromPaymentMessage(NETWORK_PARAMS,
                parsedPayment);
        assertEquals(transactions, parsedTransactions);
        assertEquals(1, parsedPayment.getRefundToCount());
        assertEquals(MEMO, parsedPayment.getMemo());
        assertArrayEquals(MERCHANT_DATA, parsedPayment.getMerchantData().toByteArray());
    }

}
