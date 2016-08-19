package ua.com.itproekt.gup.bank_api;


public class BankSessionFactory {
    private String url;

    public BankSessionFactory(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BankSession getBankSession(){
        return new BankSession(this.url);
    }
}
