/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategorySingleAttributeValue;
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
@EqualsAndHashCode(of = {"id"})
public abstract class CommonRentOffer implements Serializable {

    @Id
    @Getter @Setter
    private String id;

    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    private String title;

    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    private String description;

    private List<Integer> categories;

    private String authorId;

    @Indexed(unique = true)
    private String seoUrl;

    @Setter
    private List<ImageStorage> images;

    private Address address;

    private Lands lands;

    private String youtubeVideoId;

    private CommonStatus status;

    private OfferContactInfo contactInfo;

    private OfferModerationReport lastOfferModerationReport;

    private LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();


    public List<ImageStorage> getImages() {
        if (images == null) {
            images = new LinkedList();
        }
        return images;
    }

}
