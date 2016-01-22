package ua.com.itproekt.gup.model.offer;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

//@CompoundIndexes({
//    @CompoundIndex(name = "user_offers_idx", def = "{'authorId' : 1, 'reservation' : 1}"),// + сортировка
//    @CompoundIndex(name = "createdDate_offers_idx", def = "{'reservation' : 1, 'createdDate' : 1}"),
//    @CompoundIndex(name = "category_offers_idx", def = "{'address.area' : 1, 'address.city' : 1, 'address.country' : 1, 'category' : 1, 'reservation' : 1, 'used' : 1, 'price' : 1, 'createdDate' : 1}"),
//    @CompoundIndex(name = "prop_offers_idx", def = "{'reservation' : 1, 'address.city' : 1, 'address.area' : 1, 'address.country' : 1, 'category' : 1, 'properties.key': 1, 'properties.value': 1, 'used' : 1, 'price' : 1, 'createdDate' : 1}"),
//})

public class Offer {
    @Id
    private String id;
    private String authorId;
    private OfferUserContactInfo userInfo;
    private Integer views;
    private ModerationStatus moderationStatus;
    private Long createdDate;

    private Reservation reservation;
    private Rent rent;

    //user can edit
    private LinkedHashSet<String> categories;
    private List<Property> properties;

    @Size(max = 15)
    private Map<String, String> imagesIds;
    private String videoUrl;
    @Size(min = 5, max = 70)
    private String title;
    @Size(min = 50, max = 4000)
    private String description;
    @Min(1)
    private Integer price;
    private Boolean priceCanBeNegotiated;
    private Boolean urgent;
    private Boolean used; // б/y
    private Boolean active;
    private Boolean canBeReserved;
    private Boolean canBeRented;
    private Address address;
    private Currency currency;

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

    public Integer getPrice() {
        return price;
    }

    public Offer setPrice(Integer price) {
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

    public Boolean getUrgent() {
        return urgent;
    }

    public Offer setUrgent(Boolean urgent) {
        this.urgent = urgent;
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
}
