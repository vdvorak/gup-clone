package ua.com.itproekt.gup.server.api.rest.profiles.dto;

import ua.com.itproekt.gup.model.profiles.Profile;

public class ProfileInfo {

    private Profile profile;
    private int unreadEventsCount;

    public ProfileInfo(Profile profile) {
        this.profile = profile;
    }


    public int getUnreadEventsCount() {
        return unreadEventsCount;
    }

    public void setUnreadEventsCount(int unreadEventsCount) {
        this.unreadEventsCount = unreadEventsCount;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "profile=" + profile +
                ", unreadEventsCount=" + unreadEventsCount +
                '}';
    }
}
