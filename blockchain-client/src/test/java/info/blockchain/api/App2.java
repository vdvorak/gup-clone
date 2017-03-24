//package info.blockchain.api;
//
//
//import info.blockchain.api.blockexplorer.Block;
//import info.blockchain.api.blockexplorer.Transaction;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//public class App2 {
//
//    static NetworkParameters params = MainNetParams.get();
//    static WalletAppKit kit = new WalletAppKit(params, new java.io.File("."), "chain");
//
//    /* store_TX() gets Transactions from blocks and stores them in a file */
//    static protected void store_TX() throws BlockStoreException, FileNotFoundException, UnsupportedEncodingException{
//
//        File txf = new File("TX.txt");
//        PrintWriter hwriter = new PrintWriter("TX.txt", "UTF-8");
//
//        BlockChain chain = kit.chain();
//        BlockStore block_store = chain.getBlockStore();
//
//        StoredBlock stored_block = block_store.getChainHead();
//        // if stored_block.prev() returns null then break otherwise get block transactions
//        while (stored_block!=null){
//
//            Block block = stored_block.getHeader();
//            List<Transaction> tx_list = block.getTransactions();
//            if (tx_list != null && tx_list.size() > 0){
//                hwriter.println(block.getHashAsString());
//            }
//
//            stored_block = stored_block.getPrev(block_store);
//        }
//        hwriter.close();
//    }
//
//    public static void main(String[] args){
//
//        BriefLogFormatter.init();
//
//        synchronized(kit.startAndWait()){
//            try {
//                store_TX();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (BlockStoreException e) {
//                e.printStackTrace();
//            }
//        }
//
//    } //end main
//
//}
