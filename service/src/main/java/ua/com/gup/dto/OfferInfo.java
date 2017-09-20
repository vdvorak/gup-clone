package ua.com.gup.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.gup.domain.Offer;
import ua.com.gup.model.order.Order;
import ua.com.gup.model.order.OrderFeedback;

import java.util.List;


/**
 * Wrapper class for Offer.
 *
 * @author Kobylyatskyy Alexander
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferInfo extends Offer {
    private Offer offer;
    private boolean online;
    private String userName;
    private int averageOrderPoint;
    private List<OfferInfo> relevantOffers;
    private int feedbackCount;
    private int orderCount;
    private List<OrderFeedback> feedbackOrders;
    private List<Order> orders;

    private boolean forAdmin; // is this offer will show for administrator


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OfferInfo> getRelevantOffers() {
        return relevantOffers;
    }

    public void setRelevantOffers(List<OfferInfo> relevantOffers) {
        this.relevantOffers = relevantOffers;
    }

    public List<OrderFeedback> getFeedbackOrders() {
        return feedbackOrders;
    }

    public void setFeedbackOrders(List<OrderFeedback> feedbackOrders) {
        this.feedbackOrders = feedbackOrders;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public OfferInfo setOrderCount(int orderCount) {
        this.orderCount = orderCount;
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

    public List<Order> getOrders() {
        return orders;
    }

    public OfferInfo setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public boolean isForAdmin() {
        return forAdmin;
    }

    public void setForAdmin(boolean forAdmin) {
        this.forAdmin = forAdmin;
    }

    @Override
    public String toString() {
        return "OfferInfo{" +
                "offer=" + offer +
                ", online=" + online +
                ", userName='" + userName + '\'' +
                ", averageOrderPoint=" + averageOrderPoint +
                ", relevantOffers=" + relevantOffers +
                ", feedbackCount=" + feedbackCount +
                ", orderCount=" + orderCount +
                ", feedbackOrders=" + feedbackOrders +
                ", orders=" + orders +
                ", forAdmin=" + forAdmin +
                '}';
    }
}
