package ua.com.itproekt.gup.dao.profile;

import ua.com.itproekt.gup.model.profiles.*;

public interface LawyerProfileRepository {

    void createProfile(LawyerProfile lawyerProfile);

    LawyerProfile findProfileAndUpdate(LawyerProfile lawyerProfile);

    int deleteProfileById(String id);

    boolean profileExists(String id);

    boolean profilePublicExists(String id);

    boolean profileExistsWithEmail(String email);

    LawyerProfile findByUsername(String username);

    LawyerProfile findByEmail(String email);

    LawyerProfile findProfileByMainPhone(String mainPhone);

    LawyerProfile findById(String id);

    LawyerProfile findByPublicId(String id);

}

