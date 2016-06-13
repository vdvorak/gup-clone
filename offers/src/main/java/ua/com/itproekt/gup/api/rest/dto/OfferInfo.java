package ua.com.itproekt.gup.api.rest.dto;

import ua.com.itproekt.gup.model.offer.Address;
import ua.com.itproekt.gup.model.offer.Currency;
import ua.com.itproekt.gup.model.offer.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by jfractal on 10.06.2016.
 */
public class OfferInfo {
    private String title;
    private Integer views;
    private Map<String, String> imagesIds;
    private Integer price;
    private Currency currency;
    private List<Property> properties;
    private Address address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Map<String, String> getImagesIds() {
        return imagesIds;
    }

    public void setImagesIds(Map<String, String> imagesIds) {
        this.imagesIds = imagesIds;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
