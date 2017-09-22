package ua.com.gup.model.offers.price;

public class RentUser implements Cloneable {

    private String id;
    private String fullName;
    private String imgId;
    private Integer rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "'id': '" + id + "'" +
                ", 'fullName': " +((fullName==null) ? fullName : "'" + fullName + "'") +
                ", 'imgId': " +((imgId==null) ? imgId : "'" + imgId + "'") +
                ", 'rating': " +((rating==null) ? rating : "'" + rating + "'") +
                '}';
    }
}
