package ua.com.gup.service.dto.enumeration;


public enum OfferImageSizeType {
    SMALL(350, 200), MEDIUM(700, 400), LARGE(1920, 1080);
    private int width;
    private int height;

    OfferImageSizeType(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
