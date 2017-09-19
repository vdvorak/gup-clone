package ua.com.itproekt.gup.model.order.blockchain_test;


public class TransactionSignature {

    private String sign;      /* (String) Hex   */
    private String publicKey; /* RSA Public Key */

    /**
     * @param publicKey RSA Public Key
     */
    public TransactionSignature(String publicKey){
        this.publicKey = publicKey;
    }
    private TransactionSignature(){
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
        return "Signature{" +
                "sign='" + sign + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }
}
