package ua.com.itproekt.gup.server.api.rest.offers;


import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.dto.OfferRegistration;
import ua.com.itproekt.gup.model.offer.Address;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.offer.paidservices.PaidServices;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.util.SeoUtils;
import ua.com.itproekt.gup.util.Translit;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public final class OfferRestHelper {



    // FixMe delete this if relevant offers work fine!
//    /**
//     * Create OfferFilterOption object for search offers relevant to current on it's city.
//     *
//     * @param offer the offer to which we must find relevant offers.
//     * @return      the OfferFilterOptions object.
//     */
//    static OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearchWithCity(Offer offer) {
//        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
//        offerFilterOptions.setAddress(new Address());
//
//        // add categories in filter
//        offerFilterOptions.setCategories(offer.getCategories());
//
//        // add address in filter
//        if (offer.getAddress().getCity() != null) {
//            offerFilterOptions
//                    .getAddress()
//                    .setCity(offer.getAddress().getCity());
//            offerFilterOptions.getAddress().setArea(offer.getAddress().getArea());
//        }
//
//        return offerFilterOptions;
//    }
//
//
//    /**
//     * Create OfferFilterOption object for search offers relevant to current on it's country.
//     *
//     * @param offer the offer to which we must find relevant offers.
//     * @return      the OfferFilterOptions object.
//     */
//    static OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearchWithCountry(Offer offer) {
//        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
//        offerFilterOptions.setAddress(new Address());
//
//        // add categories in filter
//        offerFilterOptions.setCategories(offer.getCategories());
//
//        offerFilterOptions.getAddress().setCountry(offer.getAddress().getCountry());
//
//        return offerFilterOptions;
//    }
//
//
//    /**
//     * Return OfferFilterOption object only with skip and limit parameter/
//     *
//     * @return  the OfferFilterOptions object
//     */
//    static OfferFilterOptions offerFilterOptionsPreparatorOnlyWithSkipAndLimit() {
//        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
//        offerFilterOptions.setSkip(0);
//        offerFilterOptions.setLimit(20);
//        return offerFilterOptions;
//    }

    /**
     * Add SeoUrl to offer and create new PaidService in offer
     *
     * @param seoSequenceService    the link to seoSequenceService instance
     * @param offerRegistration     offerRegistration object
     */
    static void offerSeoUrlAndPaidServicePreparator(SeoSequenceService seoSequenceService, OfferRegistration offerRegistration) {
        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();
        SeoUtils.makeSeoFieldsForOffer(offerRegistration.getOffer(), longValueOfSeoKey);

        PaidServices paidServices = new PaidServices();
        paidServices.setLastUpdateDateToCurrentDate();
        offerRegistration.getOffer().setPaidServices(paidServices);
    }


    /**
     * Update and save images into DB
     *
     * @param storageService        the link to storageService instance
     * @param updatedOfferImagesMap the updated map of images
     * @param files                 the images for update
     * @return                      the Map of images ID and their positions.
     */
    static Map<String, String> updaterOfferImages(StorageService storageService, Map<String, String> updatedOfferImagesMap, MultipartFile[] files) {

        int newStartPositionForImages = updatedOfferImagesMap.size(); // old images here
        Map<String, String> newImageMap = new HashMap<>();

        newStartPositionForImages++;

        Map<String, String> mapWithNewPhoto = storageService.saveCachedMultiplyImageOffer(files, newStartPositionForImages);

        newImageMap.putAll(updatedOfferImagesMap);
        newImageMap.putAll(mapWithNewPhoto);

        return newImageMap;
    }


    /**
     * Prepare and update some additional fields for offer during it's update.
     *
     * @param offer     the new offer object from client side.
     * @param oldOffer  the offer which was before update.
     * @return          the Offer object.
     */
    static Offer offerPreparatorForEditOffer(Offer offer, Offer oldOffer){

        String newTransiltTitle = Translit.makeTransliteration(offer.getTitle());

        String newSeoUrl = newTransiltTitle + "-" + oldOffer.getSeoKey();

        offer.setSeoUrl(newSeoUrl);

        return offer;
    }

}
