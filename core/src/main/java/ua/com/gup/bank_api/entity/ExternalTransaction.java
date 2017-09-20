package ua.com.gup.bank_api.entity;

import java.util.Date;


public class ExternalTransaction {


    private long id;

    private Date dateTime;

    private long amount;

    private String idUser;

    private long idBank;

    private String idBankTransaction;

    private String status;

    public ExternalTransaction() {
    }

    public ExternalTransaction(Date dateTime, long amount, String idUser, long idBank, String idBankTransaction, String status) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.idUser = idUser;
        this.idBank = idBank;
        this.idBankTransaction = idBankTransaction;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public long getIdBank() {
        return idBank;
    }

    public void setIdBank(long idBank) {
        this.idBank = idBank;
    }

    public String getIdBankTransaction() {
        return idBankTransaction;
    }

    public void setIdBankTransaction(String idBankTransaction) {
        this.idBankTransaction = idBankTransaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExternalTransaction{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", amount=" + amount +
                ", idUser='" + idUser + '\'' +
                ", idBank=" + idBank +
                ", idBankTransaction='" + idBankTransaction + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
