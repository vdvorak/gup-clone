package ua.com.itproekt.gup.model.order.blockchain;


public class TransactionSignature {

    private String sign;      /* (String) Hex   */
    private String publicKey; /* RSA Public Key */

    /**
     *
     * @param sign      (String) Hex
     * @param publicKey RSA Public Key
     */
    public TransactionSignature(String sign, String publicKey){
        this.sign = sign;
        this.publicKey = publicKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "TransactionSignature{" +
                "sign='" + sign + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }
}
