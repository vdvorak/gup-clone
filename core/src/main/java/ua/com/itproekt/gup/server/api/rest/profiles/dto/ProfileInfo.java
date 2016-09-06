package ua.com.itproekt.gup.server.api.rest.profiles.dto;

import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.subscription.Subscription;

import java.util.List;

public class ProfileInfo {

    //ToDo when this features will work     profileInfo.setIsOnline(isUserOnline(id));


    private Profile profile;
    private Integer unreadEventsCount;
    private Integer unreadMessages;
    private Integer userBalance;
    private Integer userBonusBalance;
    private Integer userAveragePoints;
    private List<OrderFeedback> orderFeedbackList;
    private String internalTransactionHistory;
    private List<Order> orderBuyerList; // order's list where user is buyer
    private List<Order> orderSellerList; // order's list where user is seller;
    private List<Offer> userOfferList;
    private List<Subscription> subscriptionList; // user's subscription list

    private int orderAmount;
    private int totalFeedbackAmount;

    // ToDo история отзывов
    // ToDo запросы на отзывы (спросить Сашу)


    public ProfileInfo() {
    }

    public ProfileInfo(Profile profile) {
        this.profile = profile;
    }

    public Integer getUnreadEventsCount() {
        return unreadEventsCount;
    }

    public ProfileInfo setUnreadEventsCount(Integer unreadEventsCount) {
        this.unreadEventsCount = unreadEventsCount;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public ProfileInfo setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
        return this;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public ProfileInfo setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
        return this;
    }

    public Integer getUserBonusBalance() {
        return userBonusBalance;
    }

    public ProfileInfo setUserBonusBalance(Integer userBonusBalance) {
        this.userBonusBalance = userBonusBalance;
        return this;
    }

    public Integer getUserAveragePoints() {
        return userAveragePoints;
    }

    public ProfileInfo setUserAveragePoints(Integer userAveragePoints) {
        this.userAveragePoints = userAveragePoints;
        return this;
    }

    public List<OrderFeedback> getOrderFeedbackList() {
        return orderFeedbackList;
    }

    public ProfileInfo setOrderFeedbackList(List<OrderFeedback> orderFeedbackList) {
        this.orderFeedbackList = orderFeedbackList;
        return this;
    }

    public String getInternalTransactionHistory() {
        return internalTransactionHistory;
    }

    public ProfileInfo setInternalTransactionHistory(String internalTransactionHistory) {
        this.internalTransactionHistory = internalTransactionHistory;
        return this;
    }

    public List<Order> getOrderBuyerList() {
        return orderBuyerList;
    }

    public ProfileInfo setOrderBuyerList(List<Order> orderBuyerList) {
        this.orderBuyerList = orderBuyerList;
        return this;
    }

    public List<Order> getOrderSellerList() {
        return orderSellerList;
    }

    public ProfileInfo setOrderSellerList(List<Order> orderSellerList) {
        this.orderSellerList = orderSellerList;
        return this;
    }

    public List<Offer> getUserOfferList() {
        return userOfferList;
    }

    public ProfileInfo setUserOfferList(List<Offer> userOfferList) {
        this.userOfferList = userOfferList;
        return this;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public ProfileInfo setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
        return this;
    }

    public int getTotalFeedbackAmount() {
        return totalFeedbackAmount;
    }

    public ProfileInfo setTotalFeedbackAmount(int totalFeedbackAmount) {
        this.totalFeedbackAmount = totalFeedbackAmount;
        return this;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public ProfileInfo setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "profile=" + profile +
                ", unreadEventsCount=" + unreadEventsCount +
                ", unreadMessages=" + unreadMessages +
                ", userBalance=" + userBalance +
                ", userBonusBalance=" + userBonusBalance +
                ", userAveragePoints=" + userAveragePoints +
                ", orderFeedbackList=" + orderFeedbackList +
                ", internalTransactionHistory='" + internalTransactionHistory + '\'' +
                ", orderBuyerList=" + orderBuyerList +
                ", orderSellerList=" + orderSellerList +
                ", userOfferList=" + userOfferList +
                ", subscriptionList=" + subscriptionList +
                ", orderAmount=" + orderAmount +
                ", totalFeedbackAmount=" + totalFeedbackAmount +
                '}';
    }
}
