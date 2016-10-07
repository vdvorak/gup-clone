package ua.com.itproekt.gup.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.dto.FavoriteOfferInfo;
import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.dto.OrderInfo;
import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;

import java.util.*;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    PasswordEncoder passwordEncoder;
    BankSession bankSession = new BankSession(); //BankSession bankSession = new BankSession("http://93.73.109.38:8087"); //ToDo need use 'bank_session.properties'
    @Autowired
    OrderService orderService;
    @Autowired
    OffersService offersService;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SubscriptionService subscriptionService;


    //ToDo  make this work after we will repair oauth
//    @Autowired
//    private SessionRegistry sessionRegistry;

    /**
     * @param profile the profile
     */
    @Override
    public void createProfile(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setEmail(profile.getEmail())
                .setSocWendor(profile.getSocWendor())
                .setPassword(hashedPassword)
                .setUserRoles(userRoles)
                .setCreatedDateEqualsToCurrentDate()
                .setNotCompletedFields(11); // strange and magic number. Actually it is total number of fields, that you can manually filled.

        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);
        bankSession.createBalanceRecord(newProfile.getId(), 3);

        profile.setId(newProfile.getId());
    }

    /**
     * @param profile the profile
     */
    @Override
    public void facebookRegister(Profile profile) {
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setSocWendor(profile.getSocWendor())
                .setUid(profile.getUid())
                .setTokenKey(profile.getTokenKey())
                .setUserRoles(userRoles)
                .setCreatedDateEqualsToCurrentDate();

        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);
        bankSession.createBalanceRecord(newProfile.getId(), 3);

        profile.setId(newProfile.getId());
    }


    /**
     * @param id
     * @return
     */
    @Override
    public Profile findById(String id) {
        return profileRepository.findById(id);
    }

    /**
     * @param id the id
     * @return
     */
    @Override
    public Profile findWholeProfileById(String id) {
        return profileRepository.findById(id);
    }

    /***
     * @param profile
     * @return
     */
    @Override
    public Profile editProfile(Profile profile) {
        profile.setNotCompletedFields(countEmptyFields(profile));
        return profileRepository.findProfileAndUpdate(profile);
    }

    /***
     * @param id the id
     */
    @Override
    public void deleteProfileById(String id) {
        profileRepository.deleteProfileById(id);
    }

    /***
     * @param id the id
     * @return
     */
    @Override
    public boolean profileExists(String id) {
        return profileRepository.profileExists(id);
    }

    /**
     * @param email the email
     * @return
     */
    @Override
    public boolean profileExistsWithEmail(String email) {
        return profileRepository.profileExistsWithEmail(email);
    }

    /**
     * @param socWendor the socWendor
     * @return
     */
    @Override
    public boolean profileExistsWithSocWendor(String socWendor) {
        return profileRepository.profileExistsWithSocWendor(socWendor);
    }

    /**
     * @param uid the socWendor
     * @return
     */
    @Override
    public boolean profileExistsWithUid(String uid) {
        return profileRepository.profileExistsWithUid(uid);
    }

    /**
     * @param uid       the uid
     * @param socWendor the socWendor
     * @return
     */
    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return profileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }

    /***
     * @param profileFilterOptions the profile filter options
     * @return
     */
    @Override
    public List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
        return profileRepository.findAllProfiles(profileFilterOptions);
    }

    /**
     * @param profileFilterOptions profile filter options
     * @return
     */
    @Override
    public List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions) {
        return profileRepository.findAllProfilesForAdmin(profileFilterOptions);
    }

    /**
     * @param username the username
     * @return
     */
    @Override
    public Profile findProfileByUsername(String username) {
        return profileRepository.findByUsername(username);
    }

    /**
     * @param email the email
     * @return
     */
    @Override
    public Profile findProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Profile findWholeProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean isUserModerator(Profile user) {
        Set<UserRole> userRoleSet = user.getUserRoles();
        for (UserRole userRole : userRoleSet) {
            if (userRole == UserRole.ROLE_MODERATOR || userRole == UserRole.ROLE_ADMIN || userRole == UserRole.ROLE_SUPPORT) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public boolean isUserAdminById(String userId) {
        Profile profile = profileRepository.findById(userId);
        return isUserModerator(profile);
    }

    /**
     * @param seoWord
     * @return
     */
    @Override
    public boolean isSeoWordFree(String seoWord) {
        Profile profile = profileRepository.findBySeoWord(seoWord);
        return profile == null;
    }

    /**
     * @param profileId     the profile id
     * @param profileRating the profile rating
     */
    @Override
    public void createProfileRating(String profileId, ProfileRating profileRating) {
        ProfileRating newProfileRating = new ProfileRating()
                .setEarnPoints(profileRating.getEarnPoints())
                .setShortDescription(profileRating.getShortDescription())
                .setLongDescription(profileRating.getLongDescription())
                .setIdAttachedFile(profileRating.getIdAttachedFile())
                .setCreatedDateEqualsToCurrentDate();
        profileRepository.createProfileRating(profileId, newProfileRating);
        profileRating.setProfileRatingId(newProfileRating.getProfileRatingId());
    }

    /**
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return
     */
    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        return profileRepository.deleteProfileRating(profileId, profileRatingId);
    }

    /**
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return
     */
    @Override
    public Profile findProfileRating(String profileId, String profileRatingId) {
        return profileRepository.findProfileRating(profileId, profileRatingId);
    }

    /**
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return
     */
    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        return profileRepository.profileRatingExists(profileId, profileRatingId);
    }

//    @Override
//    public void addFriend(String profileId, String friendProfileId) {
//        profileRepository.addFriend(profileId, friendProfileId);
//    }

    /**
     * @param term
     * @return
     */
    @Override
    public Set<String> getMatchedNames(String term) {
        return profileRepository.getMatchedNames(term);
    }

    /**
     * @param term
     * @return
     */
    @Override
    public List<Profile> getMatchedNamesWithIds(String term) {
        return profileRepository.getMatchedNamesToFindWithId(term);
    }

    /**
     * @param term
     * @return
     */
    @Override
    public List<Profile> getMatchedCompanies(String term) {
        return profileRepository.getMatchedCompanies(term);
    }

    /**
     * @param profileOwnerContactListId
     * @param contactId
     */
    @Override
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        profileRepository.addContactToContactList(profileOwnerContactListId, contactId);
    }


    //ToDo  make this work after we will repair oauth
    @Override
    public boolean isUserOnline(String userId) {
//        Profile profile = findWholeProfileById(userId);
//
//        List<Object> principals = sessionRegistry.getAllPrincipals();
//        User user;
//        for (Object principal : principals) {
//            user = (User) principal;
//            if (user.getUsername().equals(profile.getEmail()))
//                return true;
//        }
        return false;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public ProfileInfo findPrivateProfileByIdAndUpdateLastLoginDate(String id) {

        Profile profile = findById(id);
        profile.setLastLoginDateEqualsToCurrentDate();
        profileRepository.findProfileAndUpdate(profile);
        return prepareAdditionalFieldForPrivate(profile);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public ProfileInfo findPrivateProfileByEmailAndUpdateLastLoginDate(String email) {
        long startTime = System.currentTimeMillis();

        Profile profile = findProfileByEmail(email);

        System.err.println("findProfileByEmail time: " + (System.currentTimeMillis() - startTime));

        profile.setLastLoginDateEqualsToCurrentDate();

        startTime = System.currentTimeMillis();
        profileRepository.findProfileAndUpdate(profile);
        System.err.println("findProfileAndUpdate time: " + (System.currentTimeMillis() - startTime));

        ProfileInfo profileInfo = prepareAdditionalFieldForPrivate(findProfileByEmail(email));


        return profileInfo;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProfileInfo findPublicProfileById(String id) {
        return prepareAdditionalFieldForPublic(findById(id));
    }


    /**
     * @param profileFilterOptions
     * @return
     */
    @Override
    public List<ProfileInfo> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions) {
        return getListOfPublicProfilesWithOptions(profileRepository.findAllProfiles(profileFilterOptions));
    }


    /**
     * @param uid
     * @param socWendor
     * @return
     */
    @Override
    public ProfileInfo findPrivateProfileByUidAndUpdateLastLoginDate(String uid, String socWendor) {
        return prepareAdditionalFieldForPrivate(profileRepository.findProfileByUidAndWendor(uid, socWendor));
    }


    /**
     * @param uid       the uid
     * @param socWendor the socWendor
     * @return
     */
    @Override
    public Profile findProfileByUidAndWendor(String uid, String socWendor) {
        return profileRepository.findProfileByUidAndWendor(uid, socWendor);
    }

    /**
     * @param profile
     * @return
     */
    private ProfileInfo prepareAdditionalFieldForPrivate(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);

        OrderFilterOptions orderFilterOptionsForUser = new OrderFilterOptions();
        orderFilterOptionsForUser.setBuyerId(profile.getId());
        orderFilterOptionsForUser.setSellerId(profile.getId());


        //ToDo make me simple
        OfferFilterOptions offerFilterOptionsForAuthor = new OfferFilterOptions();
        offerFilterOptionsForAuthor.setAuthorId(profile.getId());
        offerFilterOptionsForAuthor.setLimit(20);


        List<Order> orderListForUser = orderService.findOrdersWihOptions(orderFilterOptionsForUser);
        List<OrderInfo> orderInfoListForUser = orderService.orderInfoListPreparatorForPrivate(orderListForUser, profile);


        List<OfferInfo> userOfferInfoList = offersService.getListOfPrivateOfferInfoWithOptions(offerFilterOptionsForAuthor, orderListForUser);


        SubscriptionFilterOptions subscriptionFilterOptions = new SubscriptionFilterOptions();
        subscriptionFilterOptions.setUserId(profile.getId());

        List<Subscription> subscriptionList = subscriptionService.findWithFilterOption(subscriptionFilterOptions).getEntities();


        List<OrderInfo> orderInfoSellerList = orderService.orderInfoSellerListFromTotalOrderListOfUser(orderInfoListForUser, profile.getId());
        List<OrderInfo> orderInfoBuyerList = orderService.orderInfoBuyerListFromTotalOrderListOfUser(orderInfoListForUser, profile.getId());

        int totalOrdersAmount = orderInfoListForUser.size();

        int totalFeedbackAmount = orderService.calculateFeedbackAmountForOrderList(orderInfoListForUser);

        List<FavoriteOfferInfo> favoriteOfferInfoList = favoriteOfferInfoListPreparator(profile);

        
        profileInfo.setUserBalance(bankSession.getUserBalance(profile.getId()))
                .setUserBonusBalance(Integer.parseInt(bankSession.getBonusByUserId(profile.getId())))
                .setInternalTransactionHistory(bankSession.getInternalTransactionsJsonByUserId(profile.getId()))
                .setUserOfferInfoList(userOfferInfoList)
                .setSubscriptionList(subscriptionList)
                .setTotalFeedbackAmount(totalFeedbackAmount)
                .setOrderAmount(totalOrdersAmount)
                .setOrderInfoBuyerList(orderInfoBuyerList)
                .setOrderInfoSellerList(orderInfoSellerList)
                .setFavoriteOfferInfoList(favoriteOfferInfoList);


        profileInfo.setUserAveragePoints(orderService.calculateAveragePointsForListOfOrders(orderInfoListToOrderList(orderInfoSellerList)));

        profileInfo.getProfile().setFavoriteOffers(null);

        profileInfo.getProfile().setPassword(null);
        return profileInfo;
    }

    /**
     * @param profile
     * @return
     */
    private ProfileInfo prepareAdditionalFieldForPublic(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);

        profileInfo.getProfile()
                .setEmail(null)
                .setPassword(null)
                .setContactList(null)
                .setUserProfile(null)
                .setOrderAddressList(null)
                .setUserRoles(null)
                .setOfferUserContactInfoList(null)
                .setFavoriteOffers(null);

        List<OrderFeedback> orderFeedbackList = feedbackListPreparatorForProfile(profile.getId());

        profileInfo.setUserBalance(null)
                .setUserBonusBalance(null)
                .setUnreadMessages(null)
                .setUnreadMessages(null)
                .setOrderFeedbackList(orderFeedbackList) // all users feedback for his offer


                        //ToDo boost this shit! based on orderFeedbackList
                .setUserAveragePoints(orderService.calculateAveragePointsForOrderFeedbackList(orderFeedbackList)); // ToDo impl this!

        return profileInfo;
    }

    /**
     * @param profileList
     * @return
     */
    private List<ProfileInfo> getListOfPublicProfilesWithOptions(List<Profile> profileList) {
        List<ProfileInfo> profileInfoList = new ArrayList<>();
        for (Profile profile : profileList) {
            profileInfoList.add(prepareAdditionalFieldForPublic(profile));
        }
        return profileInfoList;
    }

    /**
     * @param newProfile
     */
    private void setEmptyFieldsForNewUser(Profile newProfile) {
        newProfile.setFavoriteOffers(new HashSet<>());

        Contact contact = new Contact();
        contact.setType(UserType.INDIVIDUAL);
        contact.setContactEmails(new HashSet<>());
        contact.setContactPhones(new HashSet<>());
        contact.setNaceId(new ArrayList<>());
        contact.setSocNetLink(new HashMap<>());

        newProfile.setPoint(0)
                .setProfileRating(new HashSet<>())
                .setContactList(new HashSet<>())
                .setUserProfile(new UserProfile())
                .setContact(contact)
                .setOfferUserContactInfoList(null)
                .setOrderAddressList(null);
    }


    /**
     * @param profileId
     * @return
     */
    private List<OrderFeedback> feedbackListPreparatorForProfile(String profileId) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAuthorId(profileId);

        List<Offer> offerList = offersService.findOffersWihOptions(offerFilterOptions).getEntities();

        List<OrderFeedback> allOffersFeedbackList = new ArrayList<>();

        for (Offer offer : offerList) {
            List<OrderFeedback> oneOfferOrderList = orderService.findAllFeedbacksForOffer(offer.getId());
            for (OrderFeedback orderFeedback : oneOfferOrderList) {
                allOffersFeedbackList.add(orderFeedback);
            }
        }
        return allOffersFeedbackList;
    }


    /**
     * @param profileId
     * @return
     */
    private List<Order> orderListPreparatorForUser(String profileId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setBuyerId(profileId);
        orderFilterOptions.setSellerId(profileId);
        return orderService.findOrdersWihOptions(orderFilterOptions);
    }


    /**
     * Calculate average point of orders for user (seller) from order list
     *
     * @param profileId
     * @return
     */
    private int calculateAveragePointsForSellerByUserId(String profileId) {
        List<OrderFeedback> orderFeedbackList = feedbackListPreparatorForProfile(profileId);
        return orderService.calculateAveragePointsForOrderFeedbackList(orderFeedbackList);
    }

    /**
     * @param profile
     * @return
     */
    private List<FavoriteOfferInfo> favoriteOfferInfoListPreparator(Profile profile) {
        List<FavoriteOfferInfo> favoriteOfferInfoList = new ArrayList<>();

        Set<String> favoriteOffers = profile.getFavoriteOffers();

        if (favoriteOffers == null) {
            return null;
        }

        for (String favoriteOfferId : favoriteOffers) {
            favoriteOfferInfoList.add(favoriteOfferInfoPreparator(favoriteOfferId));
        }
        return favoriteOfferInfoList;
    }

    /**
     * @param favoriteOfferId
     * @return
     */
    private FavoriteOfferInfo favoriteOfferInfoPreparator(String favoriteOfferId) {
        FavoriteOfferInfo favoriteOfferInfo = new FavoriteOfferInfo();

        Offer offer = offersService.findById(favoriteOfferId);

        favoriteOfferInfo.setFavoriteOfferId(favoriteOfferId);
        favoriteOfferInfo.setFavoriteOfferSeoUrl(offer.getSeoUrl());
        favoriteOfferInfo.setFavoriteOfferTitle(offer.getTitle());
        favoriteOfferInfo.setFavoriteOfferImage(offersService.getMainOfferImage(offer));

        return favoriteOfferInfo;
    }

    /**
     * @param orderInfoList
     * @return
     */
    private List<Order> orderInfoListToOrderList(List<OrderInfo> orderInfoList) {
        List<Order> orderList = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfoList) {
            orderList.add(orderInfo.getOrder());
        }
        return orderList;
    }


    private int countEmptyFields(Profile profile) {
        int result = 0;


        // 1
        if (profile.getMainPhoneNumber() == null) {
            result++;
        }

        // 1
        if (profile.getUsername() == null) {
            result++;
        }

        // 1
        if (profile.getImgId() == null && profile.getImgUrl() == null) {
            result++;
        }

        // 1
        if (profile.getBirthDate() == null) {
            result++;
        }


        // 8 maximum
        if (profile.getContact() == null) {
            result = result + 8;
        } else {
            if (profile.getContact().getPosition() == null) {
                result++;
            }
            if (profile.getContact().getCompanyName() == null) {
                result++;
            }
            if (profile.getContact().getAboutUs() == null) {
                result++;
            }
            if (profile.getContact().getSkypeUserName() == null) {
                result++;
            }
            if (profile.getContact().getLinkToWebSite() == null) {
                result++;
            }
            if (profile.getContact().getContactEmails() == null) {
                result++;
            }
            if (profile.getContact().getContactPhones() == null) {
                result++;
            }
            if (profile.getContact().getSocNetLink() == null) {
                result++;
            }
        }

        return result;
    }


}
