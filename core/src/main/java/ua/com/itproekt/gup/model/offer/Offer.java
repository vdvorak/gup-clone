package ua.com.itproekt.gup.model.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import ua.com.itproekt.gup.model.offer.paidservices.PaidServices;
import ua.com.itproekt.gup.service.offers.PriceOfRentsRestore;
import ua.com.itproekt.gup.util.OfferUserContactInfo;
import ua.com.itproekt.gup.util.PaymentMethod;
import ua.com.itproekt.gup.util.TransportCompany;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@CompoundIndexes({
//    @CompoundIndex(name = "user_offers_idx", def = "{'authorId' : 1, 'reservation' : 1}"),// + сортировка
//    @CompoundIndex(name = "createdDate_offers_idx", def = "{'reservation' : 1, 'createdDate' : 1}"),
//    @CompoundIndex(name = "category_offers_idx", def = "{'address.area' : 1, 'address.city' : 1, 'address.country' : 1, 'category' : 1, 'reservation' : 1, 'used' : 1, 'price' : 1, 'createdDate' : 1}"),
//    @CompoundIndex(name = "prop_offers_idx", def = "{'reservation' : 1, 'address.city' : 1, 'address.area' : 1, 'address.country' : 1, 'category' : 1, 'properties.key': 1, 'properties.value': 1, 'used' : 1, 'price' : 1, 'createdDate' : 1}"),
//})

//@CompoundIndexes({
//        @CompoundIndex(name = "read_all_main_idx", def = "{'moderationStatus' : 1, 'createdDate' : -1, 'active' : 1}"),// + сортировка
//        @CompoundIndex(name = "createdDate_offers_idx", def = "{'createdDate' : 1}")
//})

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {

    @Id
    private String id;
    private String authorId;
    private OfferUserContactInfo userInfo;
    private Boolean active;
    private Long createdDate;
    private Reservation reservation;
    private Integer views;
    private PriceOfRentsRestore monthOfPrices;
    private Rent rent;
    private String seoUrl; // full SEO url with key - for siteMap
    private String seoKey; // only key - for search in DB

    private LinkedHashSet<String> categories;
    private String seoCategory; // last category for seo meta tags
    private List<Property> properties;
    @Size(max = 10)
    private Map<String, String> imagesIds;
    private String videoUrl;
    @Size(min = 2, max = 70)
    private String title;
    @Size(max = 5000)
    private String description;

    @Digits(integer = 10, fraction = 0)
    private Long price;


    private Currency currency;
    private Address address;

    private Boolean priceCanBeNegotiated;
    private Boolean used; // б/y
    private Boolean canBeReserved;
    private Boolean canBeRented;
    private boolean showOrdersCount; //user decide to show offer orders amount or not
    private Integer maximumReservedPeriod;

    private Set<TransportCompany> availableShippingMethods;
    private Set<PaymentMethod> availablePaymentMethods;
    private PaidServices paidServices;

    private ModerationStatus moderationStatus;
    private Long lastModerationDate;
    private ModerationMessage moderationMessage;

    boolean deleted;

    public Offer setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }


    //--------------------------------------------------------------------------------

    public ModerationStatus getModerationStatus() {
        return moderationStatus;
    }

    public Offer setModerationStatus(ModerationStatus moderationStatus) {
        this.moderationStatus = moderationStatus;
        return this;
    }

    public Boolean getPriceCanBeNegotiated() {
        return priceCanBeNegotiated;
    }

    public Offer setPriceCanBeNegotiated(Boolean priceCanBeNegotiated) {
        this.priceCanBeNegotiated = priceCanBeNegotiated;
        return this;
    }

    public Boolean getUsed() {
        return used;
    }

    public Offer setUsed(Boolean used) {
        this.used = used;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Offer setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public Offer setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
        return this;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public Offer setSeoKey(String seoKey) {
        this.seoKey = seoKey;
        return this;
    }

    public OfferUserContactInfo getUserInfo() {
        return userInfo;
    }

    public Offer setUserInfo(OfferUserContactInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Offer setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Rent getRent() {
        return rent;
    }

    public Offer setRent(Rent rent) {
        this.rent = rent;
        return this;
    }

    public PriceOfRentsRestore getMonthOfPrices() {
        return monthOfPrices;
    }

    public Offer setMonthOfPrices(PriceOfRentsRestore monthOfPrices) {
        this.monthOfPrices = monthOfPrices;
        return this;
    }

    public Boolean getCanBeRented() {
        return canBeRented;
    }

    public Offer setCanBeRented(Boolean canBeRented) {
        this.canBeRented = canBeRented;
        return this;
    }

    public String getId() {
        return id;
    }

    public Offer setId(String id) {
        this.id = id;
        return this;
    }

    public LinkedHashSet<String> getCategories() {
        return categories;
    }

    public Offer setCategories(LinkedHashSet<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getSeoCategory() {
        return seoCategory;
    }

    public Offer setSeoCategory(String seoCategory) {
        this.seoCategory = seoCategory;
        return this;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Offer setProperties(List<Property> properties) {
        this.properties = properties;
        return this;
    }

    public Map<String, String> getImagesIds() {
        return imagesIds;
    }

    public Offer setImagesIds(Map<String, String> imagesIds) {
        this.imagesIds = imagesIds;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Offer setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Offer setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public Offer setViews(Integer views) {
        this.views = views;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public Offer setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Boolean getCanBeReserved() {
        return canBeReserved;
    }

    public Offer setCanBeReserved(Boolean canBeReserved) {
        this.canBeReserved = canBeReserved;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Offer setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Offer setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Offer setReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Offer setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ModerationMessage getModerationMessage() {
        return moderationMessage;
    }

    public Offer setModerationMessage(ModerationMessage moderationMessage) {
        this.moderationMessage = moderationMessage;
        return this;
    }

    public Integer getMaximumReservedPeriod() {
        return maximumReservedPeriod;
    }

    public Offer setMaximumReservedPeriod(Integer maximumReservedPeriod) {
        this.maximumReservedPeriod = maximumReservedPeriod;
        return this;
    }

    public Long getLastModerationDate() {
        return lastModerationDate;
    }

    public Offer setLastModerationDate(Long lastModerationDate) {
        this.lastModerationDate = lastModerationDate;
        return this;
    }

    public Set<TransportCompany> getAvailableShippingMethods() {
        return availableShippingMethods;
    }

    public Offer setAvailableShippingMethods(Set<TransportCompany> availableShippingMethods) {
        this.availableShippingMethods = availableShippingMethods;
        return this;
    }

    public Set<PaymentMethod> getAvailablePaymentMethods() {
        return availablePaymentMethods;
    }

    public Offer setAvailablePaymentMethods(Set<PaymentMethod> availablePaymentMethods) {
        this.availablePaymentMethods = availablePaymentMethods;
        return this;
    }

    public PaidServices getPaidServices() {
        return paidServices;
    }

    public Offer setPaidServices(PaidServices paidServices) {
        this.paidServices = paidServices;
        return this;
    }

    public boolean isShowOrdersCount() {
        return showOrdersCount;
    }

    public Offer setShowOrdersCount(boolean showOrdersCount) {
        this.showOrdersCount = showOrdersCount;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Offer setIsDeleted(boolean isDeleted) {
        this.deleted = isDeleted;
        return this;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", userInfo=" + userInfo +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", reservation=" + reservation +
                ", views=" + views +
                ", rent=" + rent +
                ", seoUrl='" + seoUrl + '\'' +
                ", seoKey='" + seoKey + '\'' +
                ", categories=" + categories +
                ", seoCategory='" + seoCategory + '\'' +
                ", properties=" + properties +
                ", imagesIds=" + imagesIds +
                ", videoUrl='" + videoUrl + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", address=" + address +
                ", priceCanBeNegotiated=" + priceCanBeNegotiated +
                ", used=" + used +
                ", canBeReserved=" + canBeReserved +
                ", canBeRented=" + canBeRented +
                ", showOrdersCount=" + showOrdersCount +
                ", maximumReservedPeriod=" + maximumReservedPeriod +
                ", availableShippingMethods=" + availableShippingMethods +
                ", availablePaymentMethods=" + availablePaymentMethods +
                ", paidServices=" + paidServices +
                ", moderationStatus=" + moderationStatus +
                ", lastModerationDate=" + lastModerationDate +
                ", moderationMessage=" + moderationMessage +
                ", deleted=" + deleted +
                '}';
    }
}
