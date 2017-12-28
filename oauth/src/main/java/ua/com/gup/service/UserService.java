package ua.com.gup.service;

import ua.com.gup.model.FacebookProfile;
import ua.com.gup.mongo.composition.domain.profile.Profile;

public interface UserService {

    void createProfile(Profile profile);

    void updateProfile(Profile profile);

    Profile findById(String id);

    Profile findProfileByEmail(String email);

    Profile findProfileByFacebookId(String facebookId);

    boolean profileExistsWithEmail(String email);

    void updateChatUID(String profileId, String uid);

    void registerByFacebook(FacebookProfile profile);

    void updateLastLoginDate(String profileId);
}
