package ua.com.itproekt.gup.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferInfo extends Offer {
    private Offer offer;
    private boolean isOnline;
    private String userName;
    private int averageOrderPoint;
    private List<OfferInfo> relevantOffersList;
    private int feedbackCount;
    private int ordersCount;
    private List<OrderFeedback> orderFeedbackList;
    private List<Order> orderList;


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OfferInfo> getRelevantOffersList() {
        return relevantOffersList;
    }

    public void setRelevantOffersList(List<OfferInfo> relevantOffersList) {
        this.relevantOffersList = relevantOffersList;
    }

    public List<OrderFeedback> getOrderFeedbackList() {
        return orderFeedbackList;
    }

    public void setOrderFeedbackList(List<OrderFeedback> orderFeedbackList) {
        this.orderFeedbackList = orderFeedbackList;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public OfferInfo setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
        return this;
    }

    public int getAverageOrderPoint() {
        return averageOrderPoint;
    }

    public OfferInfo setAverageOrderPoint(int averageOrderPoint) {
        this.averageOrderPoint = averageOrderPoint;
        return this;
    }

    public int getFeedbackCount() {
        return feedbackCount;
    }

    public OfferInfo setFeedbackCount(int feedbackCount) {
        this.feedbackCount = feedbackCount;
        return this;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public OfferInfo setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        return this;
    }

    @Override
    public String toString() {
        return "OfferInfo{" +
                "offer=" + offer +
                ", isOnline=" + isOnline +
                ", userName='" + userName + '\'' +
                ", averageOrderPoint=" + averageOrderPoint +
                ", relevantOffersList=" + relevantOffersList +
                ", feedbackCount=" + feedbackCount +
                ", ordersCount=" + ordersCount +
                ", orderFeedbackList=" + orderFeedbackList +
                ", orderList=" + orderList +
                '}';
    }
}
