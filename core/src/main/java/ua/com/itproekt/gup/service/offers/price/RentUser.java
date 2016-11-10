package ua.com.itproekt.gup.service.offers.price;

public class RentUser implements Cloneable {

    private String userId;
    private String fullName;
    private String imgId;
    private Integer rating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public RentUser clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + userId +
                ", FullName='" + fullName + '\'' +
                ", imgId=" + imgId +
                ", rating=" + rating +
                '}';
    }
}
