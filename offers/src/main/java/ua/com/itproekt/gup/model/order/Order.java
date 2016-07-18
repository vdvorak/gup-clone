package ua.com.itproekt.gup.model.order;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "orders")
public class Order {
    @Id
    String id;
    String offerId;
    String buyerId;
    String sellerId;
    Date startDate;
    Date acceptDate;
    Date sentDate;
    Date receivedDate;
    Date completeDate;
    OrderAddress orderAddress;
    String trackNumber;
    OrderStatus orderStatus;
    boolean safeOrder;
    OrderType orderType;
    String buyerComment;
    String sellerComment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isSafeOrder() {
        return safeOrder;
    }

    public void setSafeOrder(boolean safeOrder) {
        this.safeOrder = safeOrder;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", offerId='" + offerId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", startDate=" + startDate +
                ", acceptDate=" + acceptDate +
                ", sentDate=" + sentDate +
                ", receivedDate=" + receivedDate +
                ", completeDate=" + completeDate +
                ", orderAddress=" + orderAddress +
                ", trackNumber='" + trackNumber + '\'' +
                ", orderStatus=" + orderStatus +
                ", safeOrder=" + safeOrder +
                ", orderType=" + orderType +
                ", buyerComment='" + buyerComment + '\'' +
                ", sellerComment='" + sellerComment + '\'' +
                '}';
    }
}
