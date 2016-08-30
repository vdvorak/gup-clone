package ua.com.itproekt.gup.server.api.rest.profiles.dto;

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
                '}';
    }
}
