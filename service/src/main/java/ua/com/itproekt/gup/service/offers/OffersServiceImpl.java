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
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.SeoUtils;
import ua.com.itproekt.gup.util.Translit;

import java.io.IOException;
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

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public ResponseEntity<String> createFullOffer(OfferRegistration offerRegistration, MultipartFile[] files) {

        offerSeoUrlAndPaidServicePreparator(seoSequenceService, offerRegistration);


        if (offerRegistration.getOffer().getImages() != null) {

            // prepare images
            List<Image> resultImageList = prepareImageBeforeOfferCreate(offerRegistration, files);


            // add prepared image to the offer
            offerRegistration.getOffer().setImages(resultImageList);
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
                .setImages(offer.getImages())
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

        // FixMe delete this in release, because this method must called only by moderator duirng offer moderation.
        subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(newOffer);

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
                .setImages(oldOffer.getImages())
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
            return new ResponseEntity<>("You did not sent offer ID", HttpStatus.BAD_REQUEST);
        } else if (!offerExists(updatedOffer.getId())) {
            return new ResponseEntity<>("Offer with this ID: " + updatedOffer.getId() + " is not exist", HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = findById(updatedOffer.getId());

        String userId = SecurityOperations.getLoggedUserId();


        // Check if current user is not an author
        if (!findById(updatedOffer.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>("You are not author of this offer", HttpStatus.FORBIDDEN);
        }


        // update SEO url title for offer
        String newTranslitTitle = Translit.makeTransliteration(updatedOffer.getTitle());
        String newSeoUrl = newTranslitTitle + "-" + oldOffer.getSeoKey();
        updatedOffer.setSeoUrl(newSeoUrl);


        updatedOffer.setImages(prepareImageBeforeOfferUpdate(oldOffer, offerRegistration, files));


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


//    @Override
//    public OfferInfo getPrivateOfferInfoById(String offerId) {
//        return privateOfferPreparator(offerRepository.findById(offerId));
//    }


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


    private List<Image> prepareImageBeforeOfferCreate(OfferRegistration offerRegistration, MultipartFile[] files) {
        List<Image> images = offerRegistration.getOffer().getImages();

        List<Image> resultImages = new ArrayList<>();

        for (Image image : images) {

            Integer currentImageIndex = image.getIndex();

            if (currentImageIndex != null) {

                addImageToTheImageLIst(resultImages, files[currentImageIndex]);

            } else if (StringUtils.isNotBlank(image.getUrl())) {

                MultipartFile multipartFile = storageService.imageDownloader(image.getUrl());

                addImageToTheImageLIst(resultImages, multipartFile);
            }

        }
        return resultImages;
    }


    /**
     * This method get image from the web (download it), or take image from client side. Than method save image
     * in the DB and put image's ID to the result Image list.
     *
     * @param resultImages  - the result image list.
     * @param multipartFile - the multipart file.
     */
    private void addImageToTheImageLIst(List<Image> resultImages, MultipartFile multipartFile) {
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();
        Image newImage = new Image();

        try {
            fileUploadWrapper
                    .setServiceName("offers")
                    .setInputStream(multipartFile.getInputStream())
                    .setContentType(multipartFile.getContentType())
                    .setFilename(multipartFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String newImageId = storageService.saveCachedImageOffer(fileUploadWrapper);
        newImage.setImageId(newImageId);
        resultImages.add(newImage);

    }


    private List<Image> prepareImageBeforeOfferUpdate(Offer oldOffer, OfferRegistration newOfferRegistration, MultipartFile[] files) {

        List<Image> resultImages = new ArrayList<>();
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

        List<Image> newImageList = newOfferRegistration.getOffer().getImages();
        List<Image> oldImageList = oldOffer.getImages();


        deleteImages(oldImageList, newImageList); // delete unnecessary images


        for (Image image : newImageList) {

            Integer currentImageIndex = image.getIndex();
            Image newImage = new Image();

            if (currentImageIndex != null) {

                // Сохраняем одну фотографию, которая лежит по указанному индексу
                try {
                    fileUploadWrapper
                            .setServiceName("offers")
                            .setInputStream(files[currentImageIndex].getInputStream())
                            .setContentType(files[currentImageIndex].getContentType())
                            .setFilename(files[currentImageIndex].getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String newImageId = storageService.saveCachedImageOffer(fileUploadWrapper);
                newImage.setImageId(newImageId);
                resultImages.add(newImage);
            } else if (StringUtils.isNotBlank(image.getImageId())) {
                newImage.setImageId(image.getImageId());
                resultImages.add(newImage);
            }
        }

        return resultImages;
    }


    /**
     * Find if in the new ImageList some images were deleted and delete them from the DB.
     *
     * @param oldImageList - the old Image list.
     * @param newImageList - the new Image list.
     */
    private void deleteImages(List<Image> oldImageList, List<Image> newImageList) {
        Set<String> setOfTheImagesForDelete = new HashSet<>();

        boolean isDeleted;

        for (Image oldImage : oldImageList) {
            isDeleted = true;

            for (Image newImage : newImageList) {
                if (oldImage.getImageId().equals(newImage.getImageId())) {
                    isDeleted = false;
                }
            }

            if (isDeleted) {
                setOfTheImagesForDelete.add(oldImage.getImageId());
            }

        }
        storageService.deleteListOfOfferImages(setOfTheImagesForDelete);
    }


    /**
     * Show does new offer have critical changes or not and add information about them into new offer object.
     *
     * @param oldOffer - the old offer version - before update.
     * @param newOffer - the new offer version - candidate to update.
     * @return - true if offer has critical changes, and false - if not.
     */
    private boolean isOfferWasCriticalChanged(Offer oldOffer, Offer newOffer, MultipartFile[] files) {

        Set<OfferModifiedField> newOfferModifiedFields = new HashSet<>();

        if (!oldOffer.getTitle().equals(newOffer.getTitle())) {
            newOfferModifiedFields.add(OfferModifiedField.MODIFIED_TITLE);
        }

        if (!oldOffer.getDescription().equals(newOffer.getDescription())) {
            newOfferModifiedFields.add(OfferModifiedField.MODIFIED_DESCRIPTION);
        }

        if (!oldOffer.getCategories().equals(newOffer.getCategories())) {
            newOfferModifiedFields.add(OfferModifiedField.MODIFIED_CATEGORIES);
        }


        //FixMe this bullshit doesn't work properly - it's show not equals in the same lists
        //        if (!oldOffer.getProperties().equals(newOffer.getProperties())) {
        //            offerModifiedFields.add(OfferModifiedField.MODIFIED_PROPERTIES);
        //        }

        // if we have new images uploaded manual
        if (files.length > 0) {
            newOfferModifiedFields.add(OfferModifiedField.MODIFIED_IMAGES);
        }


        // Если в старой версии статус модерации был NO - то изменённые поля мы добавляем, если COMPLETE - то заменяем
        if (oldOffer.getOfferModerationReports().getModerationStatus() == ModerationStatus.NO) {
            if (oldOffer.getOfferModerationReports().getOfferModifiedFieldLIst() != null) {
                newOfferModifiedFields.addAll(oldOffer.getOfferModerationReports().getOfferModifiedFieldLIst());
            }
        }

        // take old offerModerationReports, add offerModifiedFields and put it into new Offer
        OfferModerationReports resultOfferModerationReports = oldOffer.getOfferModerationReports();

        resultOfferModerationReports.setOfferModifiedFieldLIst(newOfferModifiedFields);

        if (newOfferModifiedFields.size() > 0) {
            newOffer.setOfferModerationReports(resultOfferModerationReports);
            return true;
        }

        return false;
    }

}
