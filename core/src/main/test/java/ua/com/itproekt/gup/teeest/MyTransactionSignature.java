package ua.com.itproekt.gup.teeest;


public class MyTransactionSignature {

    private String sign;
    private String key;

    private MyTransactionSignature(){
    }
    public MyTransactionSignature(String key){
        this.key = key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "MySignature{" +
                "sign='" + sign + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
