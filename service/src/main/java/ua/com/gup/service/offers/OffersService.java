package ua.com.gup.service.offers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.domain.Offer;
import ua.com.gup.dto.OfferInfo;
import ua.com.gup.dto.OfferRegistration;
import ua.com.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.gup.model.offer.Reservation;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.model.order.Order;
import ua.com.gup.util.EntityPage;

import java.util.List;
import java.util.Set;


/**
 * Interface for working with offers.
 *
 * @author Koblyatskyy Alexander
 */
public interface OffersService {
    /**
     * Create one offer and return it.
     *
     * @param offer - the offer object.
     */
    void create(Offer offer);


    /**
     * Return one offer by it's id.
     *
     * @param offerId - the offer id
     * @return - the offer object
     */
    Offer findById(String offerId);

    /**
     * Return one offer by it's seo Key.
     *
     * @param seoKey - the seo key of the offer.
     * @return - the offer.
     */
    Offer findBySeoKey(String seoKey);

    /**
     * Methods receive seoUrl as string. Return offer and increase it numbers of views.
     *
     * @param seoUrl - the Seo URL of the specific offer.
     * @return - the Offer object
     */
    Offer findBySeoUrlAndIncViews(String seoUrl);

    /**
     * Return offer and increase it's views count.
     *
     * @param offerId - the offer ID.
     * @return - the Offer object
     */
    Offer findOfferAndIncViews(String offerId);

    Offer findBySeoUrlAndIncPhoneViews(String seoUrl);

    /**
     * Delete offer by it's id.
     *
     * @param id - the offer id.
     */
    void delete(String id);

    /**
     * Return true if offer exist.
     *
     * @param id - the offer id.
     * @return - the true or false.
     */
    boolean offerExists(String id);

    /**
     * Return EntityPage of Offers that received with offer filter options.
     *
     * @param offerFilterOptions - the OfferFilterOptions object.
     * @return - the EntityPage of Offers.
     */
    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Edit offer and return new updated one.
     *
     * @param oldOffer - the Offer which we need to update from.
     * @return - the new Offer.
     */
    Offer edit(Offer oldOffer);

    /**
     * This method edit offer and previously check specific fields for update and can change moderator status if some
     * of the field were updated.
     *
     * @param offerRegistration - the OfferRegistration object.
     * @param files             - the array fo MultiPartFile - images from client side.
     * @return - the ResponseEntity object for the REST controller.
     */
    ResponseEntity<String> editByUser(OfferRegistration offerRegistration, MultipartFile[] files);

    /**
     * Reservation of the one offer.
     *
     * @param offerId     - the Offer id.
     * @param reservation - the Reservation object.
     */
    void reserveOffer(String offerId, Reservation reservation);

    /**
     * Delete reservation by offer id.
     *
     * @param offerId - the offer's ID which must be deleted.
     */
    void deleteReservation(String offerId);

    /**
     * Rent offer for specific period.
     *
     * @param offerId               - the offer's ID.
     * @param rentedOfferPeriodInfo - the period of offer rent.
     */
    void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo);

    /**
     * Delete specific rent from offer.
     *
     * @param offerId - the offer's ID.
     * @param rentId  - the id of rent.
     */
    void deleteRent(String offerId, String rentId);

    /**
     * Change the active status of the offer.
     *
     * @param offerId  - the offer ID.
     * @param isActive - the true or false.
     */
    void setActive(String offerId, boolean isActive);

    /**
     * Method for autocomplete input in frontend for searching offers.
     *
     * @param name - the String input part.
     * @return - the Set of results string.
     */
    Set<String> getMatchedNames(String name);

    /**
     * Return one offer for public view. Delete some fields from offer which should not be shown in public view.
     *
     * @param offer - the offer.
     * @return - the offer wrapped in OfferInfo class.
     */
    OfferInfo getPublicOfferInfoByOffer(Offer offer);


//    /**
//     * One offer that can contain privet information for it's author.
//     *
//     * @param offerId               - the offer's ID.
//     * @return                      - the offer wrapped in OfferInfo class.
//     */
//    OfferInfo getPrivateOfferInfoById(String offerId);

    /**
     * One offer that can contain privet information for it's author.
     *
     * @param offer - the offer.
     * @return - the offer wrapped in OfferInfo class.
     */
    OfferInfo getPrivateOfferInfoByOffer(Offer offer);

    /**
     * Return list of lightweight offers i.e. for page with search result.
     *
     * @param offerFilterOptions - the object with filter options.
     * @return - the list of offers wrapped in OfferInfo class.
     */
    List<OfferInfo> getListOfMiniPublicOffersWithOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Return list of offer info objects found with offer filter options and exclude one specific offer.
     *
     * @param offerFilterOptions - the offer filter object.
     * @param excludeOfferId     - the ID of the offer which must be excluded from result set.
     * @return - the list of the OfferInfo object.
     */
    List<OfferInfo> getListOfMiniPublicOffersWithOptionsAndExclude(OfferFilterOptions offerFilterOptions, String excludeOfferId);


    /**
     * Return list of offer info objects found with offer filter options and exclude list of specific offers.
     *
     * @param offerFilterOptions - the OfferFilterOptions objects.
     * @param orderTotalList     - the list of the offer's IDs which must be excluded from result.
     * @return - the list of the OfferInfo objects.
     */
    List<OfferInfo> getListOfPrivateOfferInfoWithOptions(OfferFilterOptions offerFilterOptions, List<Order> orderTotalList);

    /**
     * Return list of Offer Info objects with private information. Accept offerFilterOptions.
     *
     * @param offerFilterOptions - the OfferFilterOptions object.
     * @return - the list of the OfferInfo objects.
     */
    List<OfferInfo> getListOfPrivateOfferInfoWithOptions(OfferFilterOptions offerFilterOptions);


    /**
     * Return List of offers which are relevant to specific one.
     *
     * @param offer - the offer to which we must find relevant offers.
     * @return - the list of the offers.
     */
    List<OfferInfo> getListOfRelevantPublicOffersForSpecificOffer(Offer offer);

    /**
     * Return id if main offer image
     *
     * @return - the ID of the main image.
     */
    String getMainOfferImage(Offer offer);
}
