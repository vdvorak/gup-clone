package ua.com.itproekt.gup.dto;


import ua.com.itproekt.gup.model.order.Order;

public class OrderInfo {
    private Order order;


    private String buyerImg;
    private String buyerImgUrl; // image from social network if it's exist
    private String buyerName;

    private String sellerImg;
    private String sellerImgUrl; // image from social network if it's exist
    private String sellerName;


    public Order getOrder() {
        return order;
    }

    public OrderInfo setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getBuyerImg() {
        return buyerImg;
    }

    public OrderInfo setBuyerImg(String buyerImg) {
        this.buyerImg = buyerImg;
        return this;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public OrderInfo setBuyerName(String buyerName) {
        this.buyerName = buyerName;
        return this;
    }

    public String getSellerImg() {
        return sellerImg;
    }

    public OrderInfo setSellerImg(String sellerImg) {
        this.sellerImg = sellerImg;
        return this;
    }

    public String getSellerName() {
        return sellerName;
    }

    public OrderInfo setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

    public String getBuyerImgUrl() {
        return buyerImgUrl;
    }

    public OrderInfo setBuyerImgUrl(String buyerImgUrl) {
        this.buyerImgUrl = buyerImgUrl;
        return this;
    }

    public String getSellerImgUrl() {
        return sellerImgUrl;
    }

    public OrderInfo setSellerImgUrl(String sellerImgUrl) {
        this.sellerImgUrl = sellerImgUrl;
        return this;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "order=" + order +
                ", buyerImg='" + buyerImg + '\'' +
                ", buyerImgUrl='" + buyerImgUrl + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", sellerImg='" + sellerImg + '\'' +
                ", sellerImgUrl='" + sellerImgUrl + '\'' +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}
