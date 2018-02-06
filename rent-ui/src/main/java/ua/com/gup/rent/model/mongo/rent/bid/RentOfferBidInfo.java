package ua.com.gup.rent.model.mongo.rent.bid;

import lombok.Data;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.offer.Lands;
import ua.com.gup.common.model.mongo.offer.OfferContactInfo;
import ua.com.gup.rent.model.rent.RentOfferSettings;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class RentOfferBidInfo {

    private RentOfferPrice price;
    private RentOfferSettings settings;
    private Integer rentObjectsCount;
    private String seoUrl;
    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    protected String title;
    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    protected String description;
    protected Address address;
    protected List<Integer> categories;
    protected String authorId;
    protected List<ImageStorage> images;
    protected Lands lands;
    protected String youtubeVideoId;
    protected OfferContactInfo contactInfo;
    protected LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();
    protected LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();
    protected LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();
    protected LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();


}
