package ua.com.gup.bank_api.entity;

import java.util.Date;

public class InviteCodes {

    private long id;

    private String code;

    private Amount amount;

    private String userId;

    private Date createDate;

    private Date activateDate;

    public InviteCodes(){}

    public InviteCodes(long id, String code, Amount amount, String userId, Date createDate, Date activateDate){
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.userId = userId;
        this.createDate = createDate;
        this.activateDate = activateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    @Override
    public String toString() {
        return "InviteCodes{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", amount=" + amount +
                ", userId='" + userId + '\'' +
                ", createDate=" + createDate +
                ", activateDate=" + activateDate +
                '}';
    }
}
