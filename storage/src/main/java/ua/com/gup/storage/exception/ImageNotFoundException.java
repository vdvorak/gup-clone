package ua.com.gup.storage.exception;

public class ImageNotFoundException extends Throwable {
    private String imageKey;

    public ImageNotFoundException(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getImageKey() {
        return imageKey;
    }

}
