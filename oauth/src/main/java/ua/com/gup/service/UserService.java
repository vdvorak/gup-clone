package ua.com.gup.service;

import ua.com.gup.mongo.composition.domain.profile.Profile;

public interface UserService {

    void createProfile(Profile profile);

    void facebookRegister(Profile profile);

    void updateProfile(Profile profile);

    Profile findById(String id);

    Profile findProfileByEmail(String email);

    boolean profileExistsWithEmail(String email);

    void updateChatUID(String profileId, String uid);
}
