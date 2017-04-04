package ua.com.itproekt.gup.model.profiles;


import java.math.BigInteger;

public class BankCard {

    private long number;     // 1234567812345678l;
    private CardType type;   // VISA, EuroCard, MasterCard, American_Express
    private String expiries; // "12/16";
    private int cvv;         // 475;
    private String owner;    // "Vitaliy Pupkin";

    private BankCard(){
    }
    public BankCard(long number, CardType type, String expiries, int cvv, String owner){
        this.number = number;
        this.type = type;
        this.expiries = expiries;
        this.cvv = cvv;
        this.owner = owner;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getExpiries() {
        return expiries;
    }

    public void setExpiries(String expiries) {
        this.expiries = expiries;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "number=" + number +
                ", type=" + type +
                ", expiries='" + expiries + '\'' +
                ", cvv=" + cvv +
                ", owner='" + owner + '\'' +
                '}';
    }
}
