package ua.com.itproekt.gup.bank_api.entity;

public class InternalTransaction {

    private long id;

    private Long dateTime;

    private long amount;

    private String status;

    private int type;

    private String sender;

    private String recipient;

    private Integer code;

public InternalTransaction(){

}

    public InternalTransaction(Long dateTime, long amount, String status, int type, String sender, String recipient, Integer code) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.sender = sender;
        this.recipient = recipient;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InternalTransaction{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", type=" + type +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", code=" + code +
                '}';
    }
}
