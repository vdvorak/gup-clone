package ua.com.itproekt.gup.service.offers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;

import java.util.Map;

//FIXME: @Service
public class CalendarStatusServiceImpl extends CalendarStatusService {

    private Logger logger = Logger.getLogger(CalendarStatusServiceImpl.class);

    @Autowired
    private OfferRepository offerRepository;

    public CalendarStatusServiceImpl(Long weekdayPrice, Long weekendPrice) {
        super(weekdayPrice, weekendPrice);
    }

    public CalendarStatusServiceImpl(){
        super();
    }

    public CalendarStatusServiceImpl(String jsonRestore){
        super(jsonRestore);
    }

    /**
     * @param offer
     */
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
                .setIsDeleted(false)
                .setAddress(offer.getAddress())
                .setCurrency(offer.getCurrency())
                .setCanBeReserved(offer.getCanBeReserved())
                .setMaximumReservedPeriod(offer.getMaximumReservedPeriod())
                .setCanBeRented(offer.getCanBeRented())
                .setAvailableShippingMethods(offer.getAvailableShippingMethods())
                .setAvailablePaymentMethods(offer.getAvailablePaymentMethods())
                .setShowOrdersCount(offer.isShowOrdersCount())
                .setPaidServices(offer.getPaidServices());

        offerRepository.create(newOffer);

        offer.setId(newOffer.getId());
    }

    /**
     * @param oldOffer
     * @return
     */
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
                .setIsDeleted(oldOffer.isDeleted())
                .setPaidServices(oldOffer.getPaidServices());


        return offerRepository.findAndUpdate(newOffer);
    }

    public Offer findById(String offerId) {
        return offerRepository.findById(offerId);
    }

    public boolean offerExists(String id) {
        return offerRepository.offerExists(id);
    }

    public void delete(String id) {
        Map<String, String> imagesIds = findById(id).getImagesIds();
        if (imagesIds != null) {
            //ToDo логику для удаления фотографий с оффера
//            storageRepository.delete(ServiceNames.OFFERS.toString(), imagesIds.keySet());
        }
        offerRepository.delete(id);
    }

}
