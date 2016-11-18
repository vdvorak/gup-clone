package ua.com.itproekt.gup.service.offers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.dto.OfferRegistration;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.*;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.offer.paidservices.PaidServices;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.SeoUtils;
import ua.com.itproekt.gup.util.Translit;

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
    SeoSequenceService seoSequenceService;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ActivityFeedService activityFeedService;
    @Autowired
    private StorageService storageService;


    @Override
    public ResponseEntity<String> createWithRegistration(OfferRegistration offerRegistration, MultipartFile[] files) {

        String userId = SecurityOperations.getLoggedUserId();

        if (userId == null && (offerRegistration.getEmail() == null || offerRegistration.getPassword() == null)) {
            return new ResponseEntity<>("You don't have userId or email or password", HttpStatus.BAD_REQUEST);
        }

        // if user is not logged in
        if (userId == null) {

            if (profilesService.profileExistsWithEmail(offerRegistration.getEmail())) {
                return new ResponseEntity<>("Someone user still have this email", HttpStatus.CONFLICT);
            }

            // create new profile
            Profile newProfile = profilesService.createProfileFromOfferRegistration(offerRegistration);

            // set author to new offer
            offerRegistration.getOffer().setAuthorId(newProfile.getId());

        } else {
            // if user is logged in
            offerRegistration.getOffer().setAuthorId(userId);
        }


        offerSeoUrlAndPaidServicePreparator(seoSequenceService, offerRegistration);

        if (StringUtils.isNotBlank(offerRegistration.getSelectedImageType())){
            // prepare images
            Map<String, String> resultImageMap = prepareImageBeforeOfferCreate(offerRegistration, files);

            // add prepared image to the offer
            offerRegistration.getOffer().setImagesIds(resultImageMap);
        }



        // create new offer
        create(offerRegistration.getOffer());

        return new ResponseEntity<>(offerRegistration.getOffer().getSeoUrl(), HttpStatus.CREATED);
    }


    @Override
    public void create(Offer offer) {
        OfferModerationReports offerModerationReports = new OfferModerationReports();
        offerModerationReports.setModerationStatus(ModerationStatus.COMPLETE);

        Offer newOffer = new Offer()
                .setAuthorId(offer.getAuthorId())
                .setUserInfo(offer.getUserInfo())
                .setCreatedDateEqualsToCurrentDate()
                .setOfferModerationReports(offerModerationReports)
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
                .setMonthOfPrices(offer.getMonthOfPrices())
                .setRents(offer.getRents());

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
                .setOfferModerationReports(oldOffer.getOfferModerationReports())
                .setUserInfo(oldOffer.getUserInfo())
                .setCategories(oldOffer.getCategories())
                .setProperties(oldOffer.getProperties())
                .setImagesIds(oldOffer.getImagesIds())
                .setVideoUrl(oldOffer.getVideoUrl())
                .setSeoUrl(oldOffer.getSeoUrl())
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
                .setMonthOfPrices(oldOffer.getMonthOfPrices())
                .setRents(oldOffer.getRents());

        return offerRepository.findAndUpdate(newOffer);
    }


    @Override
    public ResponseEntity<String> editByUser(OfferRegistration offerRegistration, MultipartFile[] files) {

        Offer updatedOffer = offerRegistration.getOffer();

        // check is offer not null and exist
        if (updatedOffer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!offerExists(updatedOffer.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = findById(updatedOffer.getId());

        String userId = SecurityOperations.getLoggedUserId();


        // Check if current user is not an author
        if (!findById(updatedOffer.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // update SEO url title for offer
        String newTranslitTitle = Translit.makeTransliteration(updatedOffer.getTitle());
        String newSeoUrl = newTranslitTitle + "-" + oldOffer.getSeoKey();
        updatedOffer.setSeoUrl(newSeoUrl);

        updatedOffer.setImagesIds(prepareImageBeforeOfferUpdate(oldOffer, offerRegistration, files));


        // if critical information was changed in the offer - we must resubmit this offer for the moderation
        if (isOfferWasCriticalChanged(oldOffer, updatedOffer, files)) {
            oldOffer.getOfferModerationReports().setModerationStatus(ModerationStatus.NO);
            updatedOffer.setOfferModerationReports(oldOffer.getOfferModerationReports());
        }

        Offer newOffer = edit(updatedOffer);

        return new ResponseEntity<>(newOffer.getSeoUrl(), HttpStatus.OK);
    }


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
     * Return List of offers which are relevant to specific one.
     *
     * @param offer - the offer to which we must find relevant offers.
     * @return - the list of the offers.
     */
    @Override
    public List<OfferInfo> getListOfRelevantPublicOffersForSpecificOffer(Offer offer) {


        // receive list of relevant offer then transform it into offerInfo list
        List<OfferInfo> relevantOffersList = publicMiniOfferInfoPreparator(offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptionsPreparatorForRelevantSearchWithCity(offer), offer.getId()).getEntities());


        if (relevantOffersList.size() < 20) {

            // prepare list of the offers ID's which must be excluded in the next iterations of search
            List<String> currentOffersIds = new ArrayList<>();

            // add current offer's ID to exclude list
            currentOffersIds.add(offer.getId());

            for (OfferInfo offerInfo : relevantOffersList) {
                currentOffersIds.add(offerInfo.getOffer().getId());
            }


            //add extra offers from same area
            relevantOffersList.addAll(publicMiniOfferInfoPreparator(offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptionsPreparatorForRelevantSearchWithCountry(offer), currentOffersIds).getEntities()));
        }


        if (relevantOffersList.size() < 20) {

            // prepare list of the offers ID's which must be excluded in the next iterations of search
            List<String> currentOffersIds = new ArrayList<>();

            // add current offer's ID to exclude list
            currentOffersIds.add(offer.getId());

            for (OfferInfo offerInfo : relevantOffersList) {
                currentOffersIds.add(offerInfo.getOffer().getId());
            }

            int limit = 20 - relevantOffersList.size();

            // add extra offers from all categories
            relevantOffersList.addAll(publicMiniOfferInfoPreparator(offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptionsPreparatorOnlyWithSkipAndLimit(limit), currentOffersIds).getEntities()));
        }

        return relevantOffersList;
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
     * Create OfferFilterOption object for search offers relevant to current on it's city.
     *
     * @param offer the offer to which we must find relevant offers.
     * @return the OfferFilterOptions object.
     */
    private OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearchWithCity(Offer offer) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAddress(new Address());

        // add categories in filter
        offerFilterOptions.setCategories(offer.getCategories());

        // add address in filter
        if (offer.getAddress().getCity() != null) {
            offerFilterOptions
                    .getAddress()
                    .setCity(offer.getAddress().getCity());
            offerFilterOptions.getAddress().setArea(offer.getAddress().getArea());
        }

        return offerFilterOptions;
    }

    /**
     * Create OfferFilterOption object for search offers relevant to current on it's country.
     *
     * @param offer the offer to which we must find relevant offers.
     * @return the OfferFilterOptions object.
     */
    private OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearchWithCountry(Offer offer) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAddress(new Address());

        // add categories in filter
        offerFilterOptions.setCategories(offer.getCategories());

        offerFilterOptions.getAddress().setCountry(offer.getAddress().getCountry());

        return offerFilterOptions;
    }

    /**
     * Return OfferFilterOption object only with skip and limit parameter/
     *
     * @return the OfferFilterOptions object
     */
    private OfferFilterOptions offerFilterOptionsPreparatorOnlyWithSkipAndLimit(int limit) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setSkip(0);
        offerFilterOptions.setLimit(limit);
        return offerFilterOptions;
    }

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


        offer.setOfferModerationReports(null);

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
            List<String> currentOffersIds = new ArrayList<>();
            for (Offer offer : resultList) {
                currentOffersIds.add(offer.getId());
            }

            offerFilterOptions.setLimit(18 - resultList.size());
            offerFilterOptions.setCategories(null);

            // add the missing offers amount to result list
            resultList.addAll(offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptions, currentOffersIds).getEntities());
        }

        return resultList;
    }


    /**
     * Add SeoUrl to offer and create new PaidService in offer
     *
     * @param seoSequenceService the link to seoSequenceService instance
     * @param offerRegistration  offerRegistration object
     */
    private void offerSeoUrlAndPaidServicePreparator(SeoSequenceService seoSequenceService, OfferRegistration offerRegistration) {
        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();
        SeoUtils.makeSeoFieldsForOffer(offerRegistration.getOffer(), longValueOfSeoKey);

        PaidServices paidServices = new PaidServices();
        paidServices.setLastUpdateDateToCurrentDate();
        offerRegistration.getOffer().setPaidServices(paidServices);
    }


    /**
     * @param offerRegistration - the OfferRegistration object.
     * @param files             - the array of Multipart files.
     * @return - the Map of images ID's and their positions.
     */
    private Map<String, String> prepareImageBeforeOfferCreate(OfferRegistration offerRegistration, MultipartFile[] files) {
        Map<String, String> importImagesMap = new HashMap<>();
        Map<String, String> ownAddedImagesMap = new HashMap<>();
        int firstPositionForImages = 0;

        MultipartFile[] multipartImportFiles = null; // here will be files from OLX


        // download and prepare images from the web
        if (offerRegistration.getImportImagesUrlList() != null && offerRegistration.getImportImagesUrlList().size() > 0) {
            multipartImportFiles = storageService.imageDownloader(offerRegistration.getImportImagesUrlList());
        }

        // check if is the main image among the imported photos
        if (offerRegistration.getSelectedImageType().equals("olx")) {

            if (multipartImportFiles != null) {
                importImagesMap = storageService.saveCachedMultiplyImageOfferWithIndex(multipartImportFiles, 1, Integer.parseInt(offerRegistration.getSelectedImageIndex()));
                firstPositionForImages = importImagesMap.size();
            }

            if (files.length > 1) {
                ownAddedImagesMap = storageService.saveCachedMultiplyImageOfferWithIndex(files, firstPositionForImages + 1, 0);
            }

            // check if the main image among the manual upload photos
        } else if (offerRegistration.getSelectedImageType().equals("file")) {

            if (files.length > 1) {
                ownAddedImagesMap = storageService.saveCachedMultiplyImageOfferWithIndex(files, 1, Integer.parseInt(offerRegistration.getSelectedImageIndex()));
                firstPositionForImages = ownAddedImagesMap.size();
            }

            if (multipartImportFiles != null) {
                importImagesMap = storageService.saveCachedMultiplyImageOfferWithIndex(multipartImportFiles, firstPositionForImages + 1, 0);
            }
        }


        Map<String, String> resultImageMap = new HashMap<>();

        resultImageMap.putAll(importImagesMap);
        resultImageMap.putAll(ownAddedImagesMap);

        return resultImageMap;
    }


    private Map<String, String> prepareImageBeforeOfferUpdate(Offer oldOffer, OfferRegistration newOfferRegistration, MultipartFile[] files) {

        Map<String, String> ownAddedImagesMap = new HashMap<>();
        Map<String, String> updatedOldImagesMap = new HashMap<>();

        int firstPositionForImages = 0;


        // ToDo физически удаляем фото из базы, если нужно
        //Find images in old offer version that were deleted in new
        // and delete them from base in all resized variants.
        storageService.deleteDiffImagesAfterOfferUpdate(oldOffer.getImagesIds(), newOfferRegistration.getOffer().getImagesIds());


        // если "главная" фотка выбрана среди "старых"
        if (newOfferRegistration.getSelectedImageType().equals("old")) {

            if (newOfferRegistration.getOffer().getImagesIds() != null) {
                updatedOldImagesMap = replaceImagesForFirst(newOfferRegistration.getOffer().getImagesIds(), 2, newOfferRegistration.getSelectedImageIndex());
                firstPositionForImages = updatedOldImagesMap.size();
            }

            if (files.length > 1) {
                // меряем size карты и со следующего индекса грузим фотки
                ownAddedImagesMap = storageService.saveCachedMultiplyImageOfferWithIndex(files, firstPositionForImages + 1, 0);
            }

        }


        if (newOfferRegistration.getSelectedImageType().equals("file")) {

            if (files.length > 1) {
                // ToDo тут всё просто - как и в случае создания - делаем карту с первой фоткой
                ownAddedImagesMap = storageService.saveCachedMultiplyImageOfferWithIndex(files, 1, Integer.parseInt(newOfferRegistration.getSelectedImageIndex()));
                firstPositionForImages = ownAddedImagesMap.size();
            }

            if (newOfferRegistration.getOffer().getImagesIds() != null) {
                // ToDo меряем size предыдущей карты и со следующего индекса - перекладываем эту карту
                updatedOldImagesMap = replaceImagesForFirst(newOfferRegistration.getOffer().getImagesIds(), firstPositionForImages + 1, Integer.toString(0));

            }

        }


        Map<String, String> resultImageMap = new HashMap<>();
        resultImageMap.putAll(updatedOldImagesMap);
        resultImageMap.putAll(ownAddedImagesMap);

        return resultImageMap;
    }


    private Map<String, String> replaceImagesForFirst(Map<String, String> oldMap, int startImageIndex, String firstImageKey) {

        Map<String, String> resultMap = new HashMap<>();

        if (StringUtils.isNotEmpty(firstImageKey)) {
            // главной делаем фотку, которая пришла в firstImageKey, а остальные перекладываем с новым индексом
            for (String s : oldMap.keySet()) {
                if (oldMap.get(s).equals(firstImageKey)) {
                    resultMap.put(firstImageKey, "1");
                } else {
                    resultMap.put(s, Integer.toString(startImageIndex));
                    startImageIndex++;
                }
            }
            // если < 1 - значит "главной" фотки среди этой карты нет
        } else if (Integer.parseInt(firstImageKey) < 1) {
            for (String s : oldMap.keySet()) {
                resultMap.put(s, Integer.toString(startImageIndex));
                startImageIndex++;
            }

        }

        return resultMap;
    }


    /**
     * Update and save images for offer into DB.
     *
     * @param storageService        - the link to storageService instance.
     * @param updatedOfferImagesMap - the updated map of images.
     * @param files                 - the images for update.
     * @return - the Map of images ID and their positions.
     */
    private Map<String, String> updaterOfferImages(StorageService storageService, Map<String, String> updatedOfferImagesMap, MultipartFile[] files) {

        int newStartPositionForImages = updatedOfferImagesMap.size(); // old images here
        Map<String, String> newImageMap = new HashMap<>();

        newStartPositionForImages++;

        Map<String, String> mapWithNewPhoto = storageService.saveCachedMultiplyImageOffer(files, newStartPositionForImages);

        newImageMap.putAll(updatedOfferImagesMap);
        newImageMap.putAll(mapWithNewPhoto);

        return newImageMap;
    }

    /**
     * Show does new offer have critical changes or not.
     *
     * @param oldOffer - the old offer version - before update.
     * @param newOffer - the new offer version - candidate to update.
     * @return - true if offer has critical changes, and false - if not.
     */
    private boolean isOfferWasCriticalChanged(Offer oldOffer, Offer newOffer, MultipartFile[] files) {
        if (!oldOffer.getTitle().equals(newOffer.getTitle())) {
            return true;
        }

        if (!oldOffer.getDescription().equals(newOffer.getDescription())) {
            return true;
        }

        if (!oldOffer.getCategories().equals(newOffer.getCategories())) {
            return true;
        }

        if (!oldOffer.getProperties().equals(newOffer.getProperties())) {
            return true;
        }

        // if we have new images uploaded manual
        if (files.length > 0) {
            return true;
        }

        return false;
    }

}
