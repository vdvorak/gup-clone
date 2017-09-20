package ua.com.gup.bank_api.entity;

public class InternalTransaction {

    private long id;

    private Long dateTime;

    private long amount;

    private String status;

    private int type;

    private String senderId;

    private String recipient;

    private Integer code;

    public InternalTransaction(){

    }

    public InternalTransaction(Long dateTime, long amount, String status, int type, String senderId, String recipient, Integer code) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.senderId = senderId;
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

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
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
                ", senderId='" + senderId + '\'' +
                ", recipient='" + recipient + '\'' +
                ", code=" + code +
                '}';
    }
}
