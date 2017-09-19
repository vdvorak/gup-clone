package ua.com.gup.util;


public class SeoMetaTags {
    private String title;
    private String mainImgId;
    private String seoCategory;
    private String seoAdress;
    private String seoUrl;
    private String price;
    private String currency;


    public SeoMetaTags() {
    }


    public String getTitle() {
        return title;
    }

    public SeoMetaTags setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMainImgId() {
        return mainImgId;
    }

    public SeoMetaTags setMainImgId(String mainImgId) {
        this.mainImgId = mainImgId;
        return this;
    }

    public String getSeoCategory() {
        return seoCategory;
    }

    public SeoMetaTags setSeoCategory(String seoCategory) {
        this.seoCategory = seoCategory;
        return this;
    }

    public String getSeoAdress() {
        return seoAdress;
    }

    public SeoMetaTags setSeoAdress(String seoAdress) {
        this.seoAdress = seoAdress;
        return this;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public SeoMetaTags setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public SeoMetaTags setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public SeoMetaTags setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @Override
    public String toString() {
        return "SeoMetaTags{" +
                "title='" + title + '\'' +
                ", mainImgId='" + mainImgId + '\'' +
                ", seoCategory='" + seoCategory + '\'' +
                ", seoAdress='" + seoAdress + '\'' +
                ", seoUrl='" + seoUrl + '\'' +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
