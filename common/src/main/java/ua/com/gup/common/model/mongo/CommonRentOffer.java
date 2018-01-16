/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.offer.Lands;
import ua.com.gup.common.model.mongo.offer.OfferContactInfo;
import ua.com.gup.common.model.mongo.offer.OfferModerationReport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class CommonRentOffer implements Serializable {

    @Id
    protected String id;
    protected ZonedDateTime createdDate;
    protected ZonedDateTime lastModifiedDate;
    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    protected String title;
    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    protected String description;
    protected Address address;
    protected CommonStatus status;
    protected List<Integer> categories;
    protected String authorId;
    protected List<ImageStorage> images;
    protected Lands lands;
    protected String youtubeVideoId;
    protected OfferContactInfo contactInfo;
    protected OfferModerationReport lastOfferModerationReport;
    protected LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();
    protected LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();
    protected LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();
    protected LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    public CommonRentOffer() {
    }

    public CommonRentOffer(CommonRentOffer cro) {
        this.createdDate = cro.getCreatedDate();
        this.lastModifiedDate = cro.getLastModifiedDate();
        this.title = cro.getTitle();
        this.description = cro.getDescription();
        this.address = cro.getAddress();
        this.status = cro.getStatus();
        this.categories = cro.getCategories();
        this.authorId = cro.getAuthorId();
        this.images = cro.getImages();
        this.lands = cro.getLands();
        this.youtubeVideoId = cro.getYoutubeVideoId();
        this.contactInfo = cro.getContactInfo();
        this.lastOfferModerationReport = cro.getLastOfferModerationReport();
        this.attrs = cro.getAttrs();
        this.multiAttrs = cro.getMultiAttrs();
        this.numAttrs = cro.getNumAttrs();
        this.boolAttrs = cro.getBoolAttrs();
    }


    public List<ImageStorage> getImages() {
        if (images == null) {
            images = new LinkedList();
        }
        return images;
    }

}
