package ua.com.gup.rent.model.mongo.rent.bid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.mongo.offer.Lands;
import ua.com.gup.common.model.mongo.offer.OfferContactInfo;
import ua.com.gup.common.model.mongo.offer.OfferModerationReport;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.rent.RentOfferSettings;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = ObjectType.RENT_OFFER_BID, language = "russian")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of = {"id"})
public class RentOfferBid implements Serializable {
    @Id
    private String id;

    private LocalDate dtStart;
    private LocalDate dtEnd;
    private String comment;

    @CreatedDate
    private DateTime createdDate;
    @LastModifiedDate
    private DateTime lastModifiedDate;
    @Version
    private Long version;

    @CreatedBy
    private GupLoggedUser user;

    private RentOfferBidInfo offerBidInfo;
    private RentOfferBidStatus status;

}
