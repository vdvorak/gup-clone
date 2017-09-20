package ua.com.gup.model.offer;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import ua.com.gup.service.offers.PriceOfRentsRestore;
import ua.com.gup.service.offers.RentsRestore;
import ua.com.gup.util.OfferUserContactInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyRent {

    @Id
    private String               id;
    private String               offerId;
    private String               authorId;
    private OfferUserContactInfo userInfo;
    private PriceOfRentsRestore  monthOfPrices;
    private RentsRestore         rents;
    private Long                 createdDate;


    public String getId() {
        return id;
    }

    public DailyRent setId(String id) {
        this.id = id;
        return this;
    }

    public String getOfferId() {
        return offerId;
    }

    public DailyRent setOfferId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public DailyRent setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public OfferUserContactInfo getUserInfo() {
        return userInfo;
    }

    public DailyRent setUserInfo(OfferUserContactInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public PriceOfRentsRestore getMonthOfPrices() {
        return monthOfPrices;
    }

    public DailyRent setMonthOfPrices(PriceOfRentsRestore monthOfPrices) {
        this.monthOfPrices = monthOfPrices;
        return this;
    }

    public RentsRestore getRents() {
        return rents;
    }

    public DailyRent setRents(RentsRestore rents) {
        this.rents = rents;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public DailyRent setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public String toString() {
        return "DailyRent{" +
                "id='" + id + '\'' +
                ", offerId='" + offerId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", userInfo=" + userInfo +
                ", monthOfPrices=" + monthOfPrices +
                ", rents=" + rents +
                ", createdDate=" + createdDate +
                '}';
    }
}
