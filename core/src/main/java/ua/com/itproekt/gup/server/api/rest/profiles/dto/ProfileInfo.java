package ua.com.itproekt.gup.server.api.rest.profiles.dto;

import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.profiles.Profile;

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

    // ToDo подписки
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
                '}';
    }
}
