package ua.com.itproekt.gup.model.offer;


public class Image {
    private Integer index; // file index in the MultipartFile array;
    private String url; // url for import images from WEB
    private String imageId; // images ID in the DB

    public Image() {
    }

    public Image(Integer index, String url, String imageId) {
        this.index = index;
        this.url = url;
        this.imageId = imageId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "index=" + index +
                ", url='" + url + '\'' +
                ", id='" + imageId + '\'' +
                '}';
    }
}
