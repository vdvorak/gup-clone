package ua.com.itproekt.gup.service.offers;


import org.springframework.http.HttpStatus;

/**
 * This interface describe
 *
 * @author Kobylyatskyy Alexander
 */
public interface OfferModerationService {

    /**
     * Edit offer by moderator and return suitable http status.
     *
     * @param inputOffer    - the offer from moderator
     * @return              - the HTTP status
     */
    HttpStatus editOfferByModerator(Offer inputOffer);
}
