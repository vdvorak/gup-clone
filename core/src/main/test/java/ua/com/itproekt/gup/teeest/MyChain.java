package ua.com.itproekt.gup.teeest;


public class MyChain {
    private MyTransaction transaction;

    private MyChain() {
    }
    public MyChain(MyTransaction transaction) {
        this.transaction = transaction;
    }

    public MyTransaction getTransaction() {
        return transaction;
    }
}
