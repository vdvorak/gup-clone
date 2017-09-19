package ua.com.itproekt.gup.dto;

/**
 * Wrapper class for sending email for the offers subscribers.
 *
 * @author Kobylyatskyy Alexander
 */
public class SubscribeOfferEmail {

    private Offer offer;

    private String userName; // user name if it's exist

    private String deleteThisSubscribeLink;// link which delete this subscribe;
    private String deleteAllSubscribesLink; // link which delete all user's subscribe;


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeleteThisSubscribeLink() {
        return deleteThisSubscribeLink;
    }

    public void setDeleteThisSubscribeLink(String deleteThisSubscribeLink) {
        this.deleteThisSubscribeLink = deleteThisSubscribeLink;
    }

    public String getDeleteAllSubscribesLink() {
        return deleteAllSubscribesLink;
    }

    public void setDeleteAllSubscribesLink(String deleteAllSubscribesLink) {
        this.deleteAllSubscribesLink = deleteAllSubscribesLink;
    }

    @Override
    public String toString() {
        return "SubscribeOfferEmail{" +
                "offer=" + offer +
                ", userName='" + userName + '\'' +
                ", deleteThisSubscribeLink='" + deleteThisSubscribeLink + '\'' +
                ", deleteAllSubscribesLink='" + deleteAllSubscribesLink + '\'' +
                '}';
    }
}
