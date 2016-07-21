package ua.com.itproekt.gup.model.order;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String offerId;
    private Integer price;
    private String buyerId;
    private String sellerId;
    private Long startDate;
    private Long acceptDate;
    private Long sentDate;
    private Long receivedDate;
    private Long completeDate;
    private OrderAddress orderAddress;
    private String trackNumber;
    private OrderStatus orderStatus;
    private boolean safeOrder;
    private OrderType orderType;
    private List<OrderComment> orderComments;

    public Order setCreatedDateEqualsToCurrentDate() {
        this.startDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

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

    public Long getStartDate() {
        return startDate;
    }

    public Order setStartDate(Long startDate) {
        this.startDate = startDate;
        return this;
    }

    public Long getAcceptDate() {
        return acceptDate;
    }

    public Order setAcceptDate(Long acceptDate) {
        this.acceptDate = acceptDate;
        return this;
    }

    public Long getSentDate() {
        return sentDate;
    }

    public Order setSentDate(Long sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public Long getReceivedDate() {
        return receivedDate;
    }

    public Order setReceivedDate(Long receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public Long getCompleteDate() {
        return completeDate;
    }

    public Order setCompleteDate(Long completeDate) {
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

    public List<OrderComment> getOrderComments() {
        return orderComments;
    }

    public Order setOrderComments(List<OrderComment> orderComments) {
        this.orderComments = orderComments;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Order setPrice(Integer price) {
        this.price = price;
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
                ", orderComments=" + orderComments +
                '}';
    }
}
