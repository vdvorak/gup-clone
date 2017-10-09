package ua.com.gup.service.offers;


import org.springframework.http.HttpStatus;
import ua.com.gup.domain.offer.Offer;

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
