package ua.com.itproekt.gup.service.blockchain.contract;


public class TransactionSignature {

    private String sign;
    private String publicKey;

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
        return "TransactionDataSignature{" +
                "sign='" + sign + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }
}
