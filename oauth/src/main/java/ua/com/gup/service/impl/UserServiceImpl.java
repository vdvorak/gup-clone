package ua.com.gup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.model.FacebookProfile;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.repository.sequence.PublicProfileSequenceRepository;
import ua.com.gup.service.UserService;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PublicProfileSequenceRepository sequenceRepository;

    @Override
    public void createProfile(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        Profile newProfile = new Profile()
                .setPublicId("id" + sequenceRepository.getNextSequenceId(ObjectType.USER))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(profile.getActive())
                .setEmail(profile.getEmail())
                .setMainPhone(profile.getMainPhone())
                .setSocWendor(profile.getSocWendor())
                .setPassword(hashedPassword)
                .setUserRoles(profile.getUserRoles()) //TODO ?
                .setUserType(profile.getUserType())
                .setCreatedDateEqualsToCurrentDate()
                .setBankCard(profile.getBankCard()); // strange and magic number. Actually it is total number of fields, that you can manually filled.

        profileRepository.createProfile(newProfile);

        // create new balance for user in the bank
        //bankSession.createBalanceRecord(newProfile.getId(), 3); //TODO for banking

        profile.setId(newProfile.getId());
    }

    @Override
    public void facebookLogin(FacebookProfile facebookProfile) {
        Profile profile = findProfileByEmail(facebookProfile.getEmail());
        if (profile != null && Boolean.FALSE.equals(profile.isBan())) {
            if (StringUtils.isEmpty(profile.getUsername())) {
                profile.setUsername(facebookProfile.getName());
            }
            if (StringUtils.isEmpty(profile.getFirstname())) {
                profile.setFirstname(facebookProfile.getFirstName());
            }
            if (StringUtils.isEmpty(profile.getLastname())) {
                profile.setLastname(facebookProfile.getLastName());
            }
            profile.setImgUrl(facebookProfile.getImageUrl());
            profile.setActive(Boolean.TRUE);
            profileRepository.updateProfile(profile);
        } else if (profile == null) {
            HashSet<CommonUserRole> userRoles = new HashSet<CommonUserRole>() {{
                add(CommonUserRole.ROLE_USER);
            }};

            profile = new Profile()
                    .setPublicId("id" + sequenceRepository.getNextSequenceId(ObjectType.USER))
                    .setEmail(facebookProfile.getEmail())
                    .setUsername(facebookProfile.getName())
                    .setFirstname(facebookProfile.getFirstName())
                    .setLastname(facebookProfile.getLastName())
                    .setActive(Boolean.TRUE)
                    .setSocWendor("facebook")
                    .setUserRoles(userRoles)
                    .setImgUrl(facebookProfile.getImageUrl())
                    .setCreatedDateEqualsToCurrentDate();
            profileRepository.createProfile(profile);
        }
    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.updateProfile(profile);
    }

    @Override
    public Profile findById(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile findProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        return profileRepository.profileExistsWithEmail(email);
    }

    @Override
    public void updateChatUID(String profileId, String uid) {
        Profile profile = profileRepository.findById(profileId);
        profile.setChatUID(uid);
        profileRepository.updateProfile(profile);
    }
}
