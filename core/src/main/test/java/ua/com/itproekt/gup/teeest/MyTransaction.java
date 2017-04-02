package ua.com.itproekt.gup.teeest;


import java.util.Random;

abstract public class MyTransaction {

    abstract public String getType();
    abstract public long getTimestamp();
    abstract public MyTransactionSignature getSignature();

    public String get_hash(){
        return getType() + hashGenerator() + getTimestamp();
    }

    public MyTransactionSignature getSignature(String sign){
        MyTransactionSignature signature = getSignature();
        signature.setSign(sign + signature.getKey());
        return signature;
    }

    private String hashGenerator(){
        char[]     chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random    random = new Random();
        for (int i=0; i<10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "MyTransaction{" +
                "type='" + getType() + '\'' +
                ", timestamp=" + getTimestamp() +
                ", _hash=" + get_hash() +
                ", signature=" + getSignature("xxxxxxxxxxxxxxxxxx") +
                '}';
    }
}
