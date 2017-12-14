package ua.com.gup.rent.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileRating;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferPrivateProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferPublicProfileDTO;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import java.util.List;
import java.util.Set;


@Service
public class RentOfferProfilesServiceImpl implements RentOfferProfilesService {
    private final Logger log = LoggerFactory.getLogger(RentOfferProfilesServiceImpl.class);

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    /**
     * Create profile.
     *
     * @param profile - the profile.
     */
    @Override
    public void createProfile(RentOfferProfile profile) {

    }

    /**
     * Create profile.
     *
     * @param profile - the Profile object.
     */
    @Override
    public void facebookRegister(RentOfferProfile profile) {

    }

    @Override
    public RentOfferProfile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }


    @Override
    public RentOfferProfile findWholeProfileById(String id) {
        return rentOfferProfileRepository.findById(id);
    }


    @Override
    public RentOfferProfile editProfile(RentOfferProfile profile) {
        return rentOfferProfileRepository.findProfileAndUpdate(profile);
    }


    @Override
    public void deleteProfileById(String id) {
        rentOfferProfileRepository.deleteProfileById(id);
    }


    @Override
    public boolean profileExists(String id) {
        return rentOfferProfileRepository.profileExists(id);
    }

    @Override
    public boolean profileExistsByPublicId(String id) {
        return rentOfferProfileRepository.profileExistsByPublicId(id);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        return rentOfferProfileRepository.profileExistsWithEmail(email);
    }

    @Override
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        return rentOfferProfileRepository.profileExistsWithMainPhoneNumber(mainPhoneNumber);
    }


    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return rentOfferProfileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }


    @Override
    public RentOfferProfile findProfileByUsername(String username) {
        return rentOfferProfileRepository.findByUsername(username);
    }


    @Override
    public RentOfferProfile findProfileByEmail(String email) {
        return rentOfferProfileRepository.findByEmail(email);
    }

    @Override
    public RentOfferProfile findProfileByMainPhone(String mainPhone) {
        return rentOfferProfileRepository.findProfileByMainPhone(mainPhone);
    }

    @Override
    public RentOfferProfile findWholeProfileByEmail(String email) {
        return rentOfferProfileRepository.findByEmail(email);
    }


    @Override
    public boolean isSeoWordFree(String seoWord) {
        RentOfferProfile profile = rentOfferProfileRepository.findBySeoWord(seoWord);
        return profile == null;
    }


    @Override
    public void createProfileRating(String profileId, RentOfferProfileRating profileRating) {
        RentOfferProfileRating newProfileRating = new RentOfferProfileRating()
                .setEarnPoints(profileRating.getEarnPoints())
                .setShortDescription(profileRating.getShortDescription())
                .setLongDescription(profileRating.getLongDescription())
                .setIdAttachedFile(profileRating.getIdAttachedFile())
                .setCreatedDateEqualsToCurrentDate();
        rentOfferProfileRepository.createProfileRating(profileId, newProfileRating);
        profileRating.setProfileRatingId(newProfileRating.getProfileRatingId());
    }


    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        return rentOfferProfileRepository.deleteProfileRating(profileId, profileRatingId);
    }


    @Override
    public RentOfferProfile findProfileRating(String profileId, String profileRatingId) {
        return rentOfferProfileRepository.findProfileRating(profileId, profileRatingId);
    }


    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        return rentOfferProfileRepository.profileRatingExists(profileId, profileRatingId);
    }


    @Override
    public Set<String> getMatchedNames(String term) {
        return rentOfferProfileRepository.getMatchedNames(term);
    }


    @Override
    public Set<String> getAdminIdAll() {
        return rentOfferProfileRepository.getAdminIdAll();
    }

    @Override
    public Set<String> getAdminIdAllByOnline() {
        return rentOfferProfileRepository.getAdminIdAllByOnline();
    }

    @Override
    public String getAdminId() {
        return rentOfferProfileRepository.getAdminId();
    }

    @Override
    public String getAdminIdByOnline() {
        return rentOfferProfileRepository.getAdminIdByOnline();
    }


    @Override
    public List<RentOfferProfile> getMatchedNamesWithIds(String term) {
        return rentOfferProfileRepository.getMatchedNamesToFindWithId(term);
    }


    @Override
    public List<RentOfferProfile> getMatchedCompanies(String term) {
        return rentOfferProfileRepository.getMatchedCompanies(term);
    }


    @Override
    public void toggleProfileInUserSocialList(String userId, String publicProfileId) {
        if (rentOfferProfileRepository.profileExistsInUserSocialList(userId, publicProfileId)) {
            rentOfferProfileRepository.deleteProfileFromUserSocialList(userId, publicProfileId);
            rentOfferProfileRepository.decrementProfileStatistic(publicProfileId, "inContactsCounter");
        } else {
            rentOfferProfileRepository.addProfileToUserSocialList(userId, publicProfileId);
            rentOfferProfileRepository.incrementProfileStatistic(publicProfileId, "inContactsCounter");
        }
    }


    @Override
    public RentOfferProfileDTO findPrivateProfileByIdAndUpdateLastLoginDate(String id) {
        RentOfferProfile profile = findById(id);
        rentOfferProfileRepository.findProfileAndUpdate(profile);
        return new RentOfferPrivateProfileDTO(profile);
    }

    @Override
    public RentOfferProfileDTO incMainPhoneViewsAtOne(String id) {
        RentOfferProfile profile = rentOfferProfileRepository.incMainPhoneViewsAtOne(id);
        if (profile != null) {
            return new RentOfferPublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public RentOfferProfileDTO findPrivateProfileByEmailAndUpdateLastLoginDate(String email) {
        RentOfferProfile profile = findProfileByEmail(email);
        rentOfferProfileRepository.findProfileAndUpdate(profile);
        RentOfferProfileDTO profileInfo = new RentOfferPrivateProfileDTO(findProfileByEmail(email));
        return profileInfo;
    }


    @Override
    public RentOfferProfileDTO findPublicProfileById(String id) {

        RentOfferProfile profile = findById(id);
        if (profile != null) {
            return new RentOfferPublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public RentOfferProfileDTO findPublicProfileByPublicId(String id) {
        RentOfferProfile profile = findByPublicId(id);
        if (profile != null) {
            return new RentOfferPublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public RentOfferProfileDTO findPrivateProfileDTOByUid(String uid, String socWendor) {
        return new RentOfferPrivateProfileDTO(rentOfferProfileRepository.findProfileByUidAndWendor(uid, socWendor));
    }

    @Override
    public RentOfferProfileDTO findPrivateProfileDTOByPhoneNumberd(String phoneNumber, String socWendor) {
        return new RentOfferPrivateProfileDTO(rentOfferProfileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor));
    }

    @Override
    public RentOfferProfile findProfileByUidAndWendor(String uid, String socWendor) {
        return rentOfferProfileRepository.findProfileByUidAndWendor(uid, socWendor);
    }

    @Override
    public RentOfferProfile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor) {
        return rentOfferProfileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor);
    }


    @Override
    public void deleteFromMyContactList(String profileId) {
        String userId = RentSecurityUtils.getCurrentUserId();

        RentOfferProfile profile = findById(userId);

       /* Set<ProfileContactList> contactList = profile.getContactList(); //Map<String, String> contactList = profile.getContactList(); //Set<String> contactList = profile.getContactList();
        contactList.remove(profileId);
        profile.setContactList(contactList);*/

        editProfile(profile);
    }


    @Override
    public void updateFavoriteOffers(String offerId) {
        RentOfferProfile profile = findById(RentSecurityUtils.getCurrentUserId());
        Set<String> favoriteOffers = profile.getFavoriteOffers();
        for (String favoriteOffer : favoriteOffers) {
            if (favoriteOffer.equals(offerId)) {
                favoriteOffers.remove(offerId);
                return;
            }
        }
        favoriteOffers.add(offerId);
    }


    @Override
    public void updateChatUID(String profileId, String uid) {
        RentOfferProfile profile = rentOfferProfileRepository.findById(profileId);
        profile.setChatUID(uid);
        rentOfferProfileRepository.save(profile);

    }
}
