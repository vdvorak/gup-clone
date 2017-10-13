package ua.com.gup.dto;

import ua.com.gup.mongo.composition.domain.profile.Profile;

public class ProfileInfo {

    private Profile profile;
    private Integer unreadEventsCount;
    private Integer unreadMessages;
    private Integer userBalance;
    private Integer userBonusBalance;
    private Integer userAveragePoints;
    private String internalTransactionHistory;
    private int orderAmount;
    private int totalFeedbackAmount;

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

    public String getInternalTransactionHistory() {
        return internalTransactionHistory;
    }

    public ProfileInfo setInternalTransactionHistory(String internalTransactionHistory) {
        this.internalTransactionHistory = internalTransactionHistory;
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
                ", internalTransactionHistory='" + internalTransactionHistory + '\'' +
                ", orderAmount=" + orderAmount +
                ", totalFeedbackAmount=" + totalFeedbackAmount +
                '}';
    }
}
