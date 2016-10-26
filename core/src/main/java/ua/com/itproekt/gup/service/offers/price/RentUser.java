package ua.com.itproekt.gup.service.offers.price;

public class RentUser implements Cloneable {

    private Long userId;
    private String fullName;
    private Long imgId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
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
                '}';
    }
}
