package ua.com.gup.dto;


public class FavoriteOfferInfo {

    private String favoriteOfferId;
    private String favoriteOfferTitle;
    private String favoriteOfferImage;
    private String favoriteOfferSeoUrl;

    public String getFavoriteOfferId() {
        return favoriteOfferId;
    }

    public void setFavoriteOfferId(String favoriteOfferId) {
        this.favoriteOfferId = favoriteOfferId;
    }

    public String getFavoriteOfferTitle() {
        return favoriteOfferTitle;
    }

    public void setFavoriteOfferTitle(String favoriteOfferTitle) {
        this.favoriteOfferTitle = favoriteOfferTitle;
    }

    public String getFavoriteOfferImage() {
        return favoriteOfferImage;
    }

    public void setFavoriteOfferImage(String favoriteOfferImage) {
        this.favoriteOfferImage = favoriteOfferImage;
    }

    public String getFavoriteOfferSeoUrl() {
        return favoriteOfferSeoUrl;
    }

    public void setFavoriteOfferSeoUrl(String favoriteOfferSeoUrl) {
        this.favoriteOfferSeoUrl = favoriteOfferSeoUrl;
    }

    @Override
    public String toString() {
        return "FavoriteOfferInfo{" +
                "favoriteOfferId='" + favoriteOfferId + '\'' +
                ", favoriteOfferTitle='" + favoriteOfferTitle + '\'' +
                ", favoriteOfferImage='" + favoriteOfferImage + '\'' +
                ", favoriteOfferSeoUrl='" + favoriteOfferSeoUrl + '\'' +
                '}';
    }
}
