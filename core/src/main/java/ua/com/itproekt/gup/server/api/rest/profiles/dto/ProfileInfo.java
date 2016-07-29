package ua.com.itproekt.gup.server.api.rest.profiles.dto;

import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileInfo {

    //ToDo when this features will work     profileInfo.setIsOnline(isUserOnline(id));
    //ToDo profileInfo.setLastLoginDateEqualsToCurrentDate() - сохранять в базу


    private Profile profile;
    private Integer unreadEventsCount;

//    public ProfileInfo(Profile profile) {
//        this.profile = profile;
//    }


    public ProfileInfo() {

    }

    private ProfileInfo(Profile profile) {
        this.profile = profile;
    }

    public Integer getUnreadEventsCount() {
        return unreadEventsCount;
    }

    public void setUnreadEventsCount(Integer unreadEventsCount) {
        this.unreadEventsCount = unreadEventsCount;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
                .setUnreadMessages(null);
        profileInfo.setUnreadEventsCount(null);
        return profileInfo;
    }

    public ProfileInfo getPrivateProfile(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);
        profileInfo.getProfile()
                .setPassword(null)
                .setUnreadMessages(6);//FixMe make this realy work
        profileInfo.setUnreadEventsCount(42);//ToDo make this work
        return profileInfo;
    }


    @Override
    public String toString() {
        return "ProfileInfo{" +
                "profile=" + profile +
                ", unreadEventsCount=" + unreadEventsCount +
                '}';
    }
}
