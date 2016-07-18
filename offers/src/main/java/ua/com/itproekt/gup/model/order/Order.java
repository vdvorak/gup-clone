package ua.com.itproekt.gup.model.order;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;

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

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public String getOfferId() {
        return offerId;
    }

    public Order setOfferId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public Order setBuyerId(String buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getSellerId() {
        return sellerId;
    }

    public Order setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Order setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public Order setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
        return this;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public Order setSentDate(Date sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public Order setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public Order setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
        return this;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public Order setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
        return this;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public Order setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public boolean isSafeOrder() {
        return safeOrder;
    }

    public Order setSafeOrder(boolean safeOrder) {
        this.safeOrder = safeOrder;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Order setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public Order setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
        return this;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public Order setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
        return this;
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
