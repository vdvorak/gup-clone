package ua.com.itproekt.gup.model.order.blockchain;

public interface Transaction {

    /**
     * @return
     */
    String getType();

    /**
     * @return
     */
    void setType(String type);

    /**
     * @return
     */
    long getTimestamp();

    /**
     * @return
     */
    String get_hash();

    /**
     * @return
     */
    TransactionSignature getSignature();

    /**
     * @return
     */
    Object[] getInputs();

    /**
     * @return
     */
    Object[] getOutputs();
}
