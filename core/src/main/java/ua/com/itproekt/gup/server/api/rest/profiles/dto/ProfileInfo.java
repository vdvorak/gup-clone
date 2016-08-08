package ua.com.itproekt.gup.server.api.rest.profiles.dto;

import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileInfo {

    //ToDo when this features will work     profileInfo.setIsOnline(isUserOnline(id));


    private Profile profile;
    private Integer unreadEventsCount;
    private Integer unreadMessages;
    private Integer userBalance;
    private Integer userBonusBalance;


    public ProfileInfo() {
    }

    private ProfileInfo(Profile profile) {
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


    public List<ProfileInfo> getListOfPublicProfilesWithOptions(List<Profile> profileList) {
        List<ProfileInfo> profileInfoList = new ArrayList<>();
        for (Profile profile : profileList) {
            profileInfoList.add(getPublicProfile(profile));
        }
        return profileInfoList;
    }


    public ProfileInfo getPublicProfile(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);
        profileInfo.getProfile()
                .setEmail(null)
                .setPassword(null)
                .setContactList(null)
                .setUserProfile(null)
                .setOrderAddressList(null)
                .setUserRoles(null)
                .setOfferUserContactInfoList(null);


        profileInfo.setUnreadEventsCount(null)
                .setUnreadMessages(null);
        return profileInfo;
    }

    public ProfileInfo getPrivateProfile(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);
        profileInfo.getProfile()
                .setPassword(null);
        profileInfo.setUnreadEventsCount(42)//FixMe make this work
                .setUnreadMessages(6);//FixMe make this realy work
        return profileInfo;
    }


}
