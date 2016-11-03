package ua.com.itproekt.gup.service.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.dto.OfferRegistration;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.*;

@Service
public class OffersServiceImpl implements OffersService {

    @Autowired
    ProfilesService profilesService;
    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    OrderService orderService;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ActivityFeedService activityFeedService;


    @Override
    public void createWithRegistration(OfferRegistration offerRegistration) {

        Profile profile = new Profile();

        Set<UserRole> offerUserRoleSet = new HashSet<>();
        offerUserRoleSet.add(UserRole.ROLE_USER);

        profile
                .setEmail(offerRegistration.getEmail())
                .setPassword(offerRegistration.getPassword())
                .setUserRoles(offerUserRoleSet);

        profilesService.createProfile(profile);
        verificationTokenService.sendEmailRegistrationToken(profile.getId());

        offerRegistration.getOffer().setAuthorId(profile.getId());
        create(offerRegistration.getOffer());
    }


    @Override
    public void create(Offer offer) {
        Offer newOffer = new Offer()
                .setAuthorId(offer.getAuthorId())
                .setUserInfo(offer.getUserInfo())
                .setCreatedDateEqualsToCurrentDate()
                .setModerationStatus(ModerationStatus.COMPLETE)
                .setCategories(offer.getCategories())
                .setProperties(offer.getProperties())
                .setImagesIds(offer.getImagesIds())
                .setSeoUrl(offer.getSeoUrl())
                .setSeoKey(offer.getSeoKey())
                .setSeoCategory(offer.getSeoCategory())
                .setVideoUrl(offer.getVideoUrl())
                .setTitle(offer.getTitle())
                .setDescription(offer.getDescription())
                .setPrice(offer.getPrice())
                .setPriceCanBeNegotiated(offer.getPriceCanBeNegotiated())
                .setUsed(offer.getUsed())
                .setActive(Boolean.TRUE)
                .setDeleted(false)
                .setAddress(offer.getAddress())
                .setCurrency(offer.getCurrency())
                .setCanBeReserved(offer.getCanBeReserved())
                .setMaximumReservedPeriod(offer.getMaximumReservedPeriod())
                .setCanBeRented(offer.getCanBeRented())
                .setAvailableShippingMethods(offer.getAvailableShippingMethods())
                .setAvailablePaymentMethods(offer.getAvailablePaymentMethods())
                .setShowOrdersCount(offer.isShowOrdersCount())
                .setPaidServices(offer.getPaidServices())
                .setMonthOfPrices(offer.getMonthOfPrices());

        offerRepository.create(newOffer);

        offer.setId(newOffer.getId());
    }

    @Override
    public Offer findById(String offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public Offer findBySeoKey(String seoKey) {
        return offerRepository.findBySeoKey(seoKey);
    }

    @Override
    public Offer findBySeoUrlAndIncViews(String seoUrl) {
        String seoKey = seoUrl.substring(seoUrl.lastIndexOf("-") + 1);
        Offer offer = offerRepository.findBySeoKey(seoKey);
        offerRepository.incViewsAtOne(offer.getId());
        return offer;
    }

    @Override
    public Offer findOfferAndIncViews(String offerId) {
        offerRepository.incViewsAtOne(offerId);
        return offerRepository.findById(offerId);
    }

    @Override
    public boolean offerExists(String id) {
        return offerRepository.offerExists(id);
    }

    @Override
    public void delete(String id) {
        Map<String, String> imagesIds = findById(id).getImagesIds();
        if (imagesIds != null) {
            //ToDo логику для удаления фотографий с оффера
//            storageRepository.delete(ServiceNames.OFFERS.toString(), imagesIds.keySet());
        }
        offerRepository.delete(id);
    }

    @Override
    public EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions) {
        return offerRepository.findOffersWithOptions(offerFilterOptions);
    }


    @Override
    public Offer edit(Offer oldOffer) {
        Offer newOffer = new Offer()
                .setId(oldOffer.getId())
                .setModerationStatus(oldOffer.getModerationStatus())
                .setUserInfo(oldOffer.getUserInfo())
                .setCategories(oldOffer.getCategories())
                .setProperties(oldOffer.getProperties())
                .setImagesIds(oldOffer.getImagesIds())
                .setVideoUrl(oldOffer.getVideoUrl())
                .setSeoUrl(oldOffer.getSeoUrl())
                .setModerationMessage(oldOffer.getModerationMessage())
                .setSeoCategory(oldOffer.getSeoCategory())
                .setTitle(oldOffer.getTitle())
                .setDescription(oldOffer.getDescription())
                .setPrice(oldOffer.getPrice())
                .setPriceCanBeNegotiated(oldOffer.getPriceCanBeNegotiated())
                .setUsed(oldOffer.getUsed())
                .setActive(oldOffer.getActive())
                .setCanBeReserved(oldOffer.getCanBeReserved())
                .setAddress(oldOffer.getAddress())
                .setMaximumReservedPeriod(oldOffer.getMaximumReservedPeriod())
                .setCurrency(oldOffer.getCurrency())
                .setAvailableShippingMethods(oldOffer.getAvailableShippingMethods())
                .setAvailablePaymentMethods(oldOffer.getAvailablePaymentMethods())
                .setShowOrdersCount(oldOffer.isShowOrdersCount())
                .setDeleted(oldOffer.isDeleted())
                .setPaidServices(oldOffer.getPaidServices())
                .setMonthOfPrices(oldOffer.getMonthOfPrices());

        return offerRepository.findAndUpdate(newOffer);
    }

//    @Override
//    public ModerationMessage moderateOffer(ModerationMessage moderationMessage) {
//        moderationMessage.setCreatedDateEqualsToCurrentDate();
//        moderationMessage.setIsRead(false);
//        return moderationMessage;
//    }

    @Override
    public void reserveOffer(String offerId, Reservation reservation) {
        Reservation newReservation = new Reservation()
                .setProfileId(reservation.getProfileId())
                .setUserContactInfo(reservation.getUserContactInfo())
                .setCreatedDateEqualsToCurrentDate();

        Offer newOffer = new Offer()
                .setId(offerId)
                .setReservation(newReservation);

        Offer updatedOffer = offerRepository.findAndUpdate(newOffer);
        activityFeedService.createEvent(new Event(updatedOffer.getAuthorId(), EventType.OFFER_RESERVATION, offerId, null, SecurityOperations.getLoggedUserId()));
    }

    @Override
    public void deleteReservation(String offerId) {
        offerRepository.deleteReservation(offerId);
    }

    @Override
    public void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo) {
        offerRepository.rentOffer(offerId, rentedOfferPeriodInfo);
    }

    @Override
    public void deleteRent(String offerId, String rentId) {
        offerRepository.deleteRent(offerId, rentId);
    }

    @Override
    public void setActive(String offerId, boolean isActive) {
        Offer offer = new Offer()
                .setId(offerId)
                .setActive(isActive);
        offerRepository.findAndUpdate(offer);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return offerRepository.getMatchedNames(name);
    }


    @Override
    public OfferInfo getPublicOfferInfoByOffer(Offer offer) {
        return publicOfferPreparator(offer);
    }


    @Override
    public OfferInfo getPrivateOfferInfoById(String offerId) {
        return privateOfferPreparator(offerRepository.findById(offerId));
    }


    @Override
    public OfferInfo getPrivateOfferInfoByOffer(Offer offer) {
        return privateOfferPreparator(offer);
    }


    @Override
    public List<OfferInfo> getListOfMiniPublicOffersWithOptions(OfferFilterOptions offerFilterOptions) {

        // here we receive list of offers but with redundant information
        List<Offer> notPreparedOfferList;

        if (offerFilterOptions.getFavouriteCategories() == null) {
            notPreparedOfferList = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();
        } else {
            // if we have favourite categories in FO object - we must find offer relevant for each category
            notPreparedOfferList = prepareListOfOffersRelevantToFavouriteCategories(offerFilterOptions);
        }

        return publicMiniOfferInfoPreparator(notPreparedOfferList);
    }


    @Override
    public List<OfferInfo> getListOfMiniPublicOffersWithOptionsAndExclude(OfferFilterOptions offerFilterOptions, String excludeOfferId) {
        return publicMiniOfferInfoPreparator(offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptions, excludeOfferId).getEntities());
    }

    @Override
    public List<OfferInfo> getListOfPrivateOfferInfoWithOptions(OfferFilterOptions offerFilterOptions, List<Order> orderTotalList) {
        List<OfferInfo> offerInfoList = new ArrayList<>();

        List<Offer> offerList = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();

        for (Offer offer : offerList) {
            offerInfoList.add(privateOfferPreparatorForShortList(offer, orderTotalList));
        }

        return offerInfoList;
    }


    @Override
    public List<OfferInfo> getListOfPrivateOfferInfoWithOptions(OfferFilterOptions offerFilterOptions) {
        List<OfferInfo> offerInfoList = new ArrayList<>();

        List<Offer> offerList = offerRepository.findOffersWithOptions(offerFilterOptions).getEntities();

        List<Order> orderTotalList = orderService.findAllOrdersForUser(offerFilterOptions.getAuthorId());


        for (Offer offer : offerList) {
            offerInfoList.add(privateOfferPreparatorForShortList(offer, orderTotalList));
        }

        return offerInfoList;
    }


    /**
     * Return main image of the offer.
     *
     * @param offer - the offer
     * @return - the ID of the main image
     */
    @Override
    public String getMainOfferImage(Offer offer) {

        Map<String, String> imagesMap;

        imagesMap = offer.getImagesIds();

        for (String s : imagesMap.keySet()) {
            if (imagesMap.get(s).equals("1")) {
                return s;
            }
        }

        return null;
    }


    //----------------------------------------------------- Helpers -----------------------------------------
    //-------------------------------------------------------------------------------------------------------


    /**
     * Make list of offerInfo from list of offers: delete unnecessary fields, add some additional fields
     *
     * @param offerList list of offers
     * @return offerInfo list
     */
    private List<OfferInfo> publicMiniOfferInfoPreparator(List<Offer> offerList) {
        List<OfferInfo> offerInfoList = new ArrayList<>();
        for (Offer offer : offerList) {
            offerInfoList.add(publicOfferPreparator(offer));
        }
        return offerInfoList;
    }


    /**
     * Create OfferInfo and put there public version of offer
     * and some additional fields
     *
     * @param offer Offer
     * @return OfferInfo
     */
    private OfferInfo publicOfferPreparator(Offer offer) {
        OfferInfo offerInfo = new OfferInfo();


//        long startTime = System.currentTimeMillis();
//
//        Profile profile = profilesService.findById(offer.getAuthorId());
//        System.err.println("PROFILE find by id (Offer Read All) time: " + (System.currentTimeMillis() - startTime));


        offer.setLastModerationDate(null);
        offer.setModerationMessage(null);
        offerInfo.setOffer(offer);
//        offerInfo.setIsOnline(profile.isOnline());

        return offerInfo;
    }

    /**
     * Create OfferInfo and put there private version of offer
     * and some additional fields
     *
     * @param offer offer
     * @return offerInfo object
     */
    private OfferInfo privateOfferPreparator(Offer offer) {
        OfferInfo offerInfo = new OfferInfo();

        List<OrderFeedback> orderFeedbackList = orderService.findAllFeedbacksForOffer(offer.getId());

        offerInfo.setOffer(offer);
        offerInfo.setOrderList(orderService.findAllOrdersForOffer(offer.getId()));
        offerInfo.setAverageOrderPoint(orderService.calculateAveragePointsForOrderFeedbackList(orderFeedbackList));
        return offerInfo;
    }


    // ----------------------------- For offer short list in private profile cabinet ----------------------

    /**
     * @param offer          - the offer.
     * @param orderTotalList - the order of the offer.
     * @return - the OfferInfo object.
     */
    private OfferInfo privateOfferPreparatorForShortList(Offer offer, List<Order> orderTotalList) {
        OfferInfo offerInfo = new OfferInfo();

        int orderAmountForOffer = 0;

        List<OrderFeedback> orderFeedbackList = new ArrayList<>();

        for (Order order : orderTotalList) {
            if (order.getOfferId().equals(offer.getId())) {
                if (order.getOrderFeedback() != null) {
                    orderFeedbackList.add(order.getOrderFeedback());
                }
                orderAmountForOffer++;
            }
        }

        offerInfo.setOffer(offer);
        offerInfo.setFeedbackCount(orderFeedbackList.size());
        offerInfo.setOrdersCount(orderAmountForOffer);


        return offerInfo;
    }


    /**
     * Prepare LIst of Offers relevant to favourite categories.
     *
     * @param offerFilterOptions - the FilterOptions object.
     * @return - the list of the offers.
     */
    private List<Offer> prepareListOfOffersRelevantToFavouriteCategories(OfferFilterOptions offerFilterOptions) {
        List<Offer> resultList = new ArrayList<>();
        LinkedHashSet<String> currentCategory = new LinkedHashSet<>();
        int newLimit = 18;

        List<String> favouriteCategories = offerFilterOptions.getFavouriteCategories();
        int categoriesAmount = favouriteCategories.size();
        if (categoriesAmount == 1) {
            newLimit = 18;
        } else if (categoriesAmount == 2) {
            newLimit = 9;
        } else if (categoriesAmount == 3) {
            newLimit = 6;
        }

        offerFilterOptions.setLimit(newLimit);

        for (String favouriteCategory : favouriteCategories) {
            currentCategory.clear();
            currentCategory.add(favouriteCategory);
            offerFilterOptions.setCategories(currentCategory);

            // add to result list new portion of offers
            resultList.addAll(offerRepository.findOffersWithOptions(offerFilterOptions).getEntities());

        }


        if (resultList.size() < 18) {

            // create list of the current offer's ID
            List<String> currentOfferIds = new ArrayList<>();
            for (Offer offer : resultList) {
                currentOfferIds.add(offer.getId());
            }

            offerFilterOptions.setLimit(18 - resultList.size());
            offerFilterOptions.setCategories(null);

            // add the missing offers amount to result list
            resultList.addAll(offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptions, currentOfferIds).getEntities());
        }

        return resultList;
    }

}
