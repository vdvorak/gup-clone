package ua.com.gup.service.offers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.domain.Event;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.offer.OfferModerationReport;
import ua.com.gup.domain.offer.OfferRepository;
import ua.com.gup.domain.offer.model.Address;
import ua.com.gup.domain.offer.model.OfferCategory;
import ua.com.gup.dto.OfferInfo;
import ua.com.gup.dto.OfferRegistration;
import ua.com.gup.model.activityfeed.EventType;
import ua.com.gup.model.offer.Image;
import ua.com.gup.model.offer.OfferModifiedField;
import ua.com.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.gup.model.offer.Reservation;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.model.offer.paidservices.PaidServices;
import ua.com.gup.model.order.Order;
import ua.com.gup.model.order.OrderFeedback;
import ua.com.gup.server.api.rest.dto.FileUploadWrapper;
import ua.com.gup.service.activityfeed.ActivityFeedService;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.service.order.OrderService;
import ua.com.gup.service.sequence.SeoSequenceService;
import ua.com.gup.util.EntityPage;
import ua.com.gup.util.SecurityOperations;
import ua.com.gup.util.SeoUtils;
import ua.com.gup.util.Translit;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;


@Service
public class OffersServiceImpl implements OffersService {


    @Autowired
    private OrderService orderService;

    @Autowired
    private SeoSequenceService seoSequenceService;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ActivityFeedService activityFeedService;

    @Autowired
    private StorageService storageService;

//    @Autowired
//    private SubscriptionService subscriptionService;  //TODO ?


    @Override
    public void create(Offer offer) {
        OfferModerationReport offerModerationReport = new OfferModerationReport();
        //todo maybe add in future
        //offerModerationReport.setModerationStatus(ModerationStatus.NO);

        try {
            List<Image> images = null;
            if (offer.getImageIds().size() < 1) {
                images = new ArrayList<>();
                Image image = new Image();
                image.setUrl("null");
                image.setImageId("58edef514c8ea73b0dff0164"); //TODO hard)
                images.add(image);

            }
            //todo vdvorak
            //offer.setImageIds(images);
        } catch (NullPointerException e) {
            offer.setImageIds(null);
        }

        Offer newOffer = new Offer();
        newOffer.setAuthorId(offer.getAuthorId());
        //newOffer.setUserInfo(offer.getUserInfo());
        newOffer.setCreatedDate(ZonedDateTime.now());
        newOffer.setLastOfferModerationReport(offerModerationReport);
        //newOffer.setProperties(offer.getProperties())
        newOffer.setImageIds(offer.getImageIds());
        newOffer.setSeoUrl(offer.getSeoUrl());
        //newOffer.setSeoKey(offer.getSeoKey())
        newOffer.setCategories(offer.getCategories());
        newOffer.setYoutubeVideoId(offer.getYoutubeVideoId());
        newOffer.setTitle(offer.getTitle());
        newOffer.setDescription(offer.getDescription());
        newOffer.setPrice(offer.getPrice());
        //newOffer.setOldPrice(0l)
        //newOffer.setPriceCanBeNegotiated(offer.getPriceCanBeNegotiated())
        //newOffer.setUsed(offer.getUsed())
        //newOffer.setActive(Boolean.TRUE)
        //newOffer.setDeleted(false)
        newOffer.setAddress(offer.getAddress());
        newOffer.getPrice().setCurrency(offer.getPrice().getCurrency());
        //newOffer.setCanBeReserved(offer.getCanBeReserved())
        //newOffer.setMaximumReservedPeriod(offer.getMaximumReservedPeriod())
        //newOffer.setCanBeRented(offer.getCanBeRented())
        //newOffer.setAvailableShippingMethods(offer.getAvailableShippingMethods())
        //newOffer.setAvailablePaymentMethods(offer.getAvailablePaymentMethods())
        //newOffer.setShowOrdersCount(offer.isShowOrdersCount())
        //newOffer.setPaidServices(offer.getPaidServices())
        //newOffer.setMonthOfPrices(offer.getMonthOfPrices())
        //newOffer.setRents(offer.getRents())
        //newOffer.setMadeInUkraine(offer.isMadeInUkraine());

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
    public Offer findBySeoUrlAndIncPhoneViews(String seoUrl) {
        Offer offer = offerRepository.findBySeoUrl(seoUrl);
        offerRepository.incPhoneViewsAtOne(offer.getId());
        return offer;
    }

    @Override
    public boolean offerExists(String id) {
        return offerRepository.offerExists(id);
    }

    @Override
    public void delete(String id) {
        List<String> imagesIds = findById(id).getImageIds();
        if (imagesIds != null) {
            offerRepository.delete(id);
        }

    }

    @Override
    public EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions) {
        return offerRepository.findOffersWithOptions(offerFilterOptions);
    }


    @Override
    public Offer edit(Offer oldOffer) {
        Offer newOffer = new Offer();
        newOffer.setId(oldOffer.getId());
        newOffer.setLastOfferModerationReport(oldOffer.getLastOfferModerationReport());
        newOffer.setAuthorId(oldOffer.getAuthorId());
        //newOffer.setUserInfo(oldOffer.getUserInfo());
        newOffer.setCreatedDate(ZonedDateTime.now());
        newOffer.setCategories(oldOffer.getCategories());
        //newOffer.setProperties(oldOffer.getProperties());
        newOffer.setImageIds(oldOffer.getImageIds());
        newOffer.setSeoUrl(oldOffer.getSeoUrl());
        //newOffer.setSeoKey(oldOffer.getSeoKey());
        //newOffer.setSeoCategory(oldOffer.getSeoCategory());
        newOffer.setYoutubeVideoId(oldOffer.getYoutubeVideoId());
        newOffer.setTitle(oldOffer.getTitle());
        newOffer.setDescription(oldOffer.getDescription());
        newOffer.setPrice(oldOffer.getPrice());
        //newOffer.setOldPrice(offerRepository.findById(oldOffer.getId()).getPrice() != null ? offerRepository.findById(oldOffer.getId()).getPrice() : 0l)
        //newOffer.setPriceCanBeNegotiated(oldOffer.getPriceCanBeNegotiated());
        //newOffer.setUsed(oldOffer.getUsed());
        //newOffer.setActive(oldOffer.getActive());
        newOffer.setAddress(oldOffer.getAddress());
        newOffer.getPrice().setCurrency(oldOffer.getPrice().getCurrency());
        //newOffer.setCanBeReserved(oldOffer.getCanBeReserved());
        //newOffer.setMaximumReservedPeriod(oldOffer.getMaximumReservedPeriod());
        //newOffer.setCanBeRented(oldOffer.getCanBeRented());
        //newOffer.setAvailableShippingMethods(oldOffer.getAvailableShippingMethods());
        //newOffer.setAvailablePaymentMethods(oldOffer.getAvailablePaymentMethods());
        //newOffer.setShowOrdersCount(oldOffer.isShowOrdersCount()); //TODO
        //newOffer.setDeleted(oldOffer.isDeleted());
        //newOffer.setPaidServices(oldOffer.getPaidServices());
        //newOffer.setMonthOfPrices(oldOffer.getMonthOfPrices());
        //newOffer.setRents(oldOffer.getRents());
        //newOffer.setMadeInUkraine(oldOffer.isMadeInUkraine());

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
        String newSeoUrl = newTranslitTitle + "-" + oldOffer.getSeoUrl();
        updatedOffer.setSeoUrl(newSeoUrl);


        updatedOffer.setImageIds(prepareImageBeforeOfferUpdate(oldOffer, offerRegistration, files));


        // if critical information was changed in the offer - we must resubmit this offer for the moderation
        //todo maybe add in future
        /*if (isOfferWasCriticalChanged(oldOffer, updatedOffer, files)) {
            oldOffer.getLastOfferModerationReport().setModerationStatus(ModerationStatus.NO);
            updatedOffer.setOfferModerationReports(oldOffer.getOfferModerationReports());
        }*/

        Offer newOffer = edit(updatedOffer);

        return new ResponseEntity<>(newOffer.getSeoUrl(), HttpStatus.OK);
    }

    @Override
    public void reserveOffer(String offerId, Reservation reservation) {
        Reservation newReservation = new Reservation()
                .setProfileId(reservation.getProfileId())
                .setUserContactInfo(reservation.getUserContactInfo())
                .setCreatedDateEqualsToCurrentDate();

        Offer newOffer = new Offer();
        newOffer.setId(offerId);
        //newOffer.setReservation(newReservation);

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
        Offer offer = new Offer();
        offer.setId(offerId);
        //offer.setActive(isActive);
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
        OfferFilterOptions offerFilterOptions = offerFilterOptionsPreparatorForRelevantSearchWithCity(offer);
        LinkedList<OfferCategory> categories = new LinkedList<>(offerFilterOptions.getCategories());
        int numberOfFilterOptions = categories.size() + 1;
        Set<String> currentOffersIds = new HashSet<>();
        currentOffersIds.add(offer.getId());
        Map<String, Offer> relevantOffersMap = new LinkedHashMap<>();
        // receive list of relevant offers first 3 categories match then 2 and 1
        for (int i = 0; i < numberOfFilterOptions && relevantOffersMap.size() < 20; i++) {
            // prepare set of the offers ID's which must be excluded in the next iterations of search
            currentOffersIds.addAll(relevantOffersMap.keySet());
            offerFilterOptions.setLimit(20 - relevantOffersMap.size());
            // add current offer's to result map
            //add extra offers from current filter options
            offerRepository.findOffersWithOptionsAndExcludes(offerFilterOptions, currentOffersIds).getEntities().forEach(
                    o -> relevantOffersMap.put(o.getId(), o)
            );
            if (categories.size() > 1) {
                categories.removeLast();
                offerFilterOptions.setCategories(new LinkedList<>(categories));
            } else {
                int limit = 20 - relevantOffersMap.size();
                offerFilterOptions = offerFilterOptionsPreparatorOnlyWithSkipAndLimit(limit);
            }
        }
        List<OfferInfo> relevantOffersList = publicMiniOfferInfoPreparator(new ArrayList<>(relevantOffersMap.values()));
        return relevantOffersList;
    }


    @Override
    public String getMainOfferImage(Offer offer) {
        List<String> imageListId = offer.getImageIds();
        if (imageListId != null) {
            for (String imageId : imageListId) {
                if (StringUtils.isNotBlank(imageId)) {
                    return imageId;
                }
            }
        }
        return null;
    }
    //----------------------------------------------------- Helpers ----------------------------------------------------

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
            /*offerFilterOptions.getAddress().setArea(offer.getAddress().getArea());*/
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
        offer.setLastOfferModerationReport(null);
        offerInfo.setOffer(offer);
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
        offerInfo.setOrders(orderService.findAllOrdersForOffer(offer.getId()));
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
        offerInfo.setOrderCount(orderAmountForOffer);


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
        LinkedList<OfferCategory> currentCategory = new LinkedList<>();
        int newLimit = 18;

        List<OfferCategory> favouriteCategories = offerFilterOptions.getFavouriteCategories();
        int categoriesAmount = favouriteCategories.size();
        if (categoriesAmount == 1) {
            newLimit = 18;
        } else if (categoriesAmount == 2) {
            newLimit = 9;
        } else if (categoriesAmount == 3) {
            newLimit = 6;
        }

        offerFilterOptions.setLimit(newLimit);

        for (OfferCategory favouriteCategory : favouriteCategories) {
            currentCategory.clear();
            currentCategory.add(favouriteCategory);
            // add to result list new portion of offers
            resultList.addAll(offerRepository.findOffersWithOptions(offerFilterOptions).getEntities());

        }
        offerFilterOptions.setCategories(currentCategory);

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
        //todo maybe add in future Offer change
        //offerRegistration.getOffer().setPaidServices(paidServices);
    }


    /**
     * This method get image from the web (download it), or take image from client side. Than method save image
     * in the DB and put image's ID to the result Image list.
     *
     * @param resultImages  - the result image list.
     * @param multipartFile - the multipart file.
     */
    private void addImageToTheImageLIst(List<String> resultImages, MultipartFile multipartFile) {
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
        resultImages.add(newImage.getImageId());

    }


    private List<String> prepareImageBeforeOfferUpdate(Offer oldOffer, OfferRegistration newOfferRegistration, MultipartFile[] files) {

        List<String> resultImages = new ArrayList<>();
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

        List<Image> newImageList = newOfferRegistration.getImages();
        List<String> oldImageList = oldOffer.getImageIds();
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
                resultImages.add(newImageId);
            } else if (StringUtils.isNotBlank(image.getImageId())) {
                newImage.setImageId(image.getImageId());
                resultImages.add(image.getImageId());
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
    private void deleteImages(List<String> oldImageList, List<Image> newImageList) {
        Set<String> setOfTheImagesForDelete = new HashSet<>();
        boolean isDeleted;
        for (String oldImage : oldImageList) {
            isDeleted = true;
            for (Image newImage : newImageList) {
                if (oldImage.equals(newImage.getImageId())) {
                    isDeleted = false;
                }
            }
            if (isDeleted) {
                setOfTheImagesForDelete.add(oldImage);
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


        //todo maybe add in future Offer is changed
        // Если в старой версии статус модерации был NO - то изменённые поля мы добавляем, если COMPLETE - то заменяем
        /*if (oldOffer.getLastOfferModerationReport().getModerationStatus() == ModerationStatus.NO) {
            if (oldOffer.getOfferModerationReports().getOfferModifiedFieldLIst() != null) {
                newOfferModifiedFields.addAll(oldOffer.getOfferModerationReports().getOfferModifiedFieldLIst());
            }
        }*/

        // take old offerModerationReports, add offerModifiedFields and put it into new Offer
        OfferModerationReport resultOfferModerationReports = oldOffer.getLastOfferModerationReport();

        //todo maybe add in future Offer is changed
        //resultOfferModerationReports.setOfferModifiedFieldLIst(newOfferModifiedFields);

        if (newOfferModifiedFields.size() > 0) {
            newOffer.setLastOfferModerationReport(resultOfferModerationReports);
            return true;
        }

        return false;
    }

}
