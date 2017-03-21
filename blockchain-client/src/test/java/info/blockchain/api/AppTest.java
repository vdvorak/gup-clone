package info.blockchain.api;

import info.blockchain.api.blockexplorer.*;
import info.blockchain.api.blockexplorer.Address;
import info.blockchain.api.exchangerates.Currency;
import info.blockchain.api.exchangerates.ExchangeRates;
import info.blockchain.api.statistics.Statistics;
import info.blockchain.api.statistics.StatisticsResponse;
import info.blockchain.api.wallet.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import info.blockchain.api.receive.*;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest (String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite () {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp () {
        assertTrue(true);
    }

//    public static void main(String[] args) throws Exception
//    {
//        ReceiveResponse response = Receive.receive(
//                "xpub661MyMwAqRbcFtXgS5sYJABqqG9YLmC4Q1Rdap9gSE8NqtwybGhePY2gZ29ESFjqJoCu1Rupje8YtGqsefD265TMg7usUDFdp6W1EGMcet8",
//                URLEncoder.encode("https://your.url.com?secret=foo", "UTF-8"),
//                "YOUR_API_CODE");
//
//        System.out.println(String.format("The receiving address is %s. "
//                        + "The address index is %d",
//                response.getReceivingAddress(),
//                response.getIndex()));
//    }

    public static void main(String[] args)
            throws Exception {
        // instantiate a block explorer
        BlockExplorer blockExplorer = new BlockExplorer();

        // get a transaction by hash and list the value of all its inputs
        Transaction tx = blockExplorer.getTransaction("df67414652722d38b43dcbcac6927c97626a65bd4e76a2e2787e22948a7c5c47");
        for (Input i : tx.getInputs()) {
            System.out.println("Transaction: " + i.getPreviousOutput().getValue());
        }

        System.out.println();

        // get a block by hash and read the number of transactions in the block
        Block block = blockExplorer.getBlock("0000000000000000050fe18c9b961fc7c275f02630309226b15625276c714bf1");
        int numberOfTxsInBlock = block.getTransactions().size();
        System.out.println("Block-size = " + numberOfTxsInBlock);

        System.out.println();

        // get an address and read its final balance
        Address address = blockExplorer.getAddress("1EjmmDULiZT2GCbJSeXRbjbJVvAPYkSDBw");
        long finalBalance = address.getFinalBalance();
        System.out.println("Balance = " + finalBalance);

        System.out.println();

        // get a list of currently unconfirmed transactions and print the relay IP address for each
        List<Transaction> unconfirmedTxs = blockExplorer.getUnconfirmedTransactions();
        for (Transaction unconfTx : unconfirmedTxs){
            System.out.println("Unconfirmed Transaction: " + unconfTx.getHash());
        }

        System.out.println();

        // calculate the balanace of an address by fetching a list of all its unspent outputs
        List<UnspentOutput> outs = blockExplorer.getUnspentOutputs("1EjmmDULiZT2GCbJSeXRbjbJVvAPYkSDBw");
        long totalUnspentValue = 0;
        for (UnspentOutput out : outs){
            totalUnspentValue += out.getValue();
        }
        System.out.println("Total Unspent = " + totalUnspentValue);

        System.out.println();

//        // get inventory data for a recent transaction (valid up to one hour)
//        InventoryData inv = blockExplorer.getInventoryData("f23ee3525f78df032b47c3c9be6cd0d930f38c32674e861c1e41c6558b32ee97");
//        System.out.println("InventoryData: " + inv);

        System.out.println();

        // get the latest block on the main chain and read its height
        LatestBlock latestBlock = blockExplorer.getLatestBlock();
        long latestBlockHeight = latestBlock.getHeight();
        System.out.println("LatestBlock: " + latestBlockHeight);

        System.out.println();

        // use the previous block height to get a list of blocks at that height and detect a potential chain fork
        List<Block> blocksAtHeight = blockExplorer.getBlocksAtHeight(latestBlockHeight);
        if (blocksAtHeight.size() > 1)
            System.out.println("The chain has forked!");
        else
            System.out.println("The chain is still in one piece :)");

        System.out.println();

        // get a list of all blocks that were mined today since 00:00 UTC
        List<SimpleBlock> todaysBlocks = blockExplorer.getBlocks();
        System.out.println( "Todays Blocks: " + todaysBlocks.size() );

        System.out.println();

        // get the ticker map
        Map<String, Currency> ticker = ExchangeRates.getTicker();
        BigDecimal BTCUSDsell = ticker.get("USD").getSell();
        System.out.println("BTCUSDsell = " + BTCUSDsell);

        // convert 5362 EUR to BTC
        BigDecimal EURToBTC = ExchangeRates.toBTC("EUR", new BigDecimal(53620));
        System.out.println("EURToBTC = " + EURToBTC);

        System.out.println();

        StatisticsResponse stats = Statistics.get();
        System.out.println(String.format("The current difficulty is %s. "
                        + "The next retarget will happen in %s hours.",
                stats.getDifficulty(),
                (stats.getNextRetarget() - stats.getTotalBlocks()) * stats.getMinutesBetweenBlocks() / 60));

        System.out.println();

//        Wallet wallet = new Wallet(
//                "http://localhost:3000/",
//                "YOU_API_CODE",
//                "YOUR_GUID",
//                "YOUR_SUPER_SECURE_PASSWORD");
//
//        // get an address from your wallet and include only transactions with up to 3
//        // confirmations in the balance
//        info.blockchain.api.wallet.Address addr = wallet.getAddress("1JzSZFs2DQke2B3S4pBxaNaMzzVZaG4Cqh", 3);
//        System.out.println(String.format("The balance is %s", addr.getBalance()));
//
//        // send 0.2 bitcoins with a custom fee of 0.01 BTC and a note
//        // public notes require a minimum transaction size of 0.005 BTC
//        PaymentResponse payment = wallet.send("1dice6YgEVBf88erBFra9BHf6ZMoyvG88", 20000000L, null, 1000000L, "Amazon payment");
//        System.out.println(String.format("The payment TX hash is %s", payment.getTxHash()));
//
//        long totalBalance = wallet.getBalance();
//        System.out.println(String.format("The total wallet balance is %s", totalBalance));
//
//        // list all addresses and their balances (with 0 confirmations)
//        List<info.blockchain.api.wallet.Address> addresses = wallet.listAddresses(0);
//        for (info.blockchain.api.wallet.Address a : addresses) {
//            System.out.println(String.format("The address %s has a balance of %s satoshi",
//                    a.getAddress(), a.getBalance()));
//        }
//
//        // archive an old address
//        wallet.archiveAddress("1JzSZFs2DQke2B3S4pBxaNaMzzVZaG4Cqh");
//
//        // create a new address and attach a label to it
//        info.blockchain.api.wallet.Address newAddr = wallet.newAddress("test label 123");
//        System.out.println(String.format("The new address is %s", newAddr.getAddress()));
    }

}
