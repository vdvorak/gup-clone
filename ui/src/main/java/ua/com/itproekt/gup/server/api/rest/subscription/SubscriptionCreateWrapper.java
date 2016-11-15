package ua.com.itproekt.gup.server.api.rest.subscription;

import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

/**
 * Wrapper class for request bod for create subscription.
 *
 * @author Kobylyatskyy Alexander
 */
public class SubscriptionCreateWrapper {
    OfferFilterOptions offerFilterOptions;
    String notAuthEmail; // email for not authorized person.


    public OfferFilterOptions getOfferFilterOptions() {
        return offerFilterOptions;
    }

    public void setOfferFilterOptions(OfferFilterOptions offerFilterOptions) {
        this.offerFilterOptions = offerFilterOptions;
    }

    public String getNotAuthEmail() {
        return notAuthEmail;
    }

    public void setNotAuthEmail(String notAuthEmail) {
        this.notAuthEmail = notAuthEmail;
    }

    @Override
    public String toString() {
        return "SubscriptionCreateWrapper{" +
                "offerFilterOptions=" + offerFilterOptions +
                ", notAuthEmail='" + notAuthEmail + '\'' +
                '}';
    }
}
