package ua.com.itproekt.gup.service.offers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;

@Service
public class OfferPricesServiceImpl extends OfferPricesService {

    private Logger logger = Logger.getLogger(OfferPricesServiceImpl.class);

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

    public OfferPricesServiceImpl(Long weekdayPrice, Long weekendPrice) {
        super(weekdayPrice, weekendPrice);
    }

    public OfferPricesServiceImpl(){
        super();
    }

    public OfferPricesServiceImpl(String jsonRestore){
        super(jsonRestore);
    }

    public OfferPricesServiceImpl(String jsonRestore, String jsonRentsRestore){
        super(jsonRestore, jsonRentsRestore);
    }

    public OfferPricesServiceImpl(PriceOfRentsRestore restore){
        super(restore);
    }

    /**
     * (Класс-обвертка) здесь выполняются разные проверки на владение объявлением, права бронирования...
     *
     * @param offerId
     * @return
     */
    public Offer edit(String offerId, PriceOfRentsRestore monthOfPrices) {
        if (!offerRepository.offerExists(offerId)) System.err.println("Offer (" + offerId + ") is found!");
        else System.err.println("Offer (" + offerId + ") not found?");

        Offer oldOffer = offerRepository.findById(offerId);

        Offer newOffer = new Offer()
                .setId(oldOffer.getId())
//                .setModerationStatus(oldOffer.getModerationStatus())
//                .setUserInfo(oldOffer.getUserInfo())
//                .setCategories(oldOffer.getCategories())
//                .setProperties(oldOffer.getProperties())
//                .setImagesIds(oldOffer.getImagesIds())
//                .setVideoUrl(oldOffer.getVideoUrl())
//                .setSeoUrl(oldOffer.getSeoUrl())
//                .setModerationMessage(oldOffer.getModerationMessage())
//                .setSeoCategory(oldOffer.getSeoCategory())
//                .setTitle(oldOffer.getTitle())
//                .setDescription(oldOffer.getDescription())
//                .setPrice(oldOffer.getPrice())
//                .setPriceCanBeNegotiated(oldOffer.getPriceCanBeNegotiated())
//                .setUsed(oldOffer.getUsed())
//                .setActive(oldOffer.getActive())
//                .setCanBeReserved(oldOffer.getCanBeReserved())
//                .setAddress(oldOffer.getAddress())
//                .setMaximumReservedPeriod(oldOffer.getMaximumReservedPeriod())
//                .setCurrency(oldOffer.getCurrency())
//                .setAvailableShippingMethods(oldOffer.getAvailableShippingMethods())
//                .setAvailablePaymentMethods(oldOffer.getAvailablePaymentMethods())
//                .setShowOrdersCount(oldOffer.isShowOrdersCount())
//                .setIsDeleted(oldOffer.isDeleted())
//                .setPaidServices(oldOffer.getPaidServices())
                .setMonthOfPrices(monthOfPrices);

        return offerRepository.findAndUpdate(newOffer);
    }

}
