package ua.com.itproekt.gup.server.api.rest.offers;


import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.util.Translit;

import java.util.HashMap;
import java.util.Map;

public final class OfferRestHelper {

    /**
     * Update and save images into DB
     *
     * @param storageService        - the link to storageService instance
     * @param updatedOfferImagesMap - the updated map of images
     * @param files                 - the images for update
     * @return                      - the Map of images ID and their positions.
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
     * @param offer     - the new offer object from client side.
     * @param oldOffer  - the offer which was before update.
     * @return          - the Offer object.
     */
    static Offer offerPreparatorForEditOffer(Offer offer, Offer oldOffer) {

        String newTransiltTitle = Translit.makeTransliteration(offer.getTitle());

        String newSeoUrl = newTransiltTitle + "-" + oldOffer.getSeoKey();

        offer.setSeoUrl(newSeoUrl);

        return offer;
    }

}
