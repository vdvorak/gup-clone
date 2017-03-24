package info.blockchain.api;


import info.blockchain.api.blockexplorer.*;
import info.blockchain.api.pushtx.*;

import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
//        PushTx.pushTx( "0100000001fd468e431cf5797b108e4d22724e1e055b3ecec59af4ef17b063afd36d3c5cf6010000008c4930460221009918eee8be186035be8ca573b7a4ef7bc672c59430785e5390cc375329a2099702210085b86387e3e15d68c847a1bdf786ed0fdbc87ab3b7c224f3c5490ac19ff4e756014104fe2cfcf0733e559cbf28d7b1489a673c0d7d6de8470d7ff3b272e7221afb051b777b5f879dd6a8908f459f950650319f0e83a5cf1d7c1dfadf6458f09a84ba80ffffffff01185d2033000000001976a9144be9a6a5f6fb75765145d9c54f1a4929e407d2ec88ac00000000");




        // instantiate a block explorer
        BlockExplorer blockExplorer = new BlockExplorer();

        // get a transaction by hash and list the value of all its inputs
        Transaction tx = blockExplorer.getTransaction("df67414652722d38b43dcbcac6927c97626a65bd4e76a2e2787e22948a7c5c47");
        for (Input i : tx.getInputs())
        {
            System.out.println(i.getPreviousOutput().getValue());
        }

        // get a block by hash and read the number of transactions in the block
        Block block = blockExplorer.getBlock("0000000000000000050fe18c9b961fc7c275f02630309226b15625276c714bf1");
        int numberOfTxsInBlock = block.getTransactions().size();

        // get an address and read its final balance
        Address address = blockExplorer.getAddress("1EjmmDULiZT2GCbJSeXRbjbJVvAPYkSDBw");
        long finalBalance = address.getFinalBalance();

        // get a list of currently unconfirmed transactions and print the relay IP address for each
        List<Transaction> unconfirmedTxs = blockExplorer.getUnconfirmedTransactions();
        for (Transaction unconfTx : unconfirmedTxs)
            System.out.println(tx.getRelayedBy());

        // calculate the balanace of an address by fetching a list of all its unspent outputs
        List<UnspentOutput> outs = blockExplorer.getUnspentOutputs("1EjmmDULiZT2GCbJSeXRbjbJVvAPYkSDBw");
        long totalUnspentValue = 0;
        for (UnspentOutput out : outs)
            totalUnspentValue += out.getValue();

        // get inventory data for a recent transaction (valid up to one hour)
//        InventoryData inv = blockExplorer.getInventoryData("f23ee3525f78df032b47c3c9be6cd0d930f38c32674e861c1e41c6558b32ee97");

        // get the latest block on the main chain and read its height
        LatestBlock latestBlock = blockExplorer.getLatestBlock();
        long latestBlockHeight = latestBlock.getHeight();

        // use the previous block height to get a list of blocks at that height
        // and detect a potential chain fork
        List<Block> blocksAtHeight = blockExplorer.getBlocksAtHeight(latestBlockHeight);
        if (blocksAtHeight.size() > 1)
            System.out.println("The chain has forked!");
        else
            System.out.println("The chain is still in one piece :)");

        // get a list of all blocks that were mined today since 00:00 UTC
        List<SimpleBlock> todaysBlocks = blockExplorer.getBlocks();
        System.out.println(todaysBlocks.size());
    }
}
