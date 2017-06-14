package ua.com.itproekt.gup.model.offer;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.offer.paidservices.PaidServices;
import ua.com.itproekt.gup.service.offers.PriceOfRentsRestore;
import ua.com.itproekt.gup.service.offers.RentsRestore;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Rent2.COLLECTION_NAME)
public class Rent2 {

    public static final String COLLECTION_NAME = "rent2";
    private static final long serialVersionUID = 1L;

    @Id
    private String              id;
    private String              offerId;
    private PriceOfRentsRestore monthOfPrices;
    private RentsRestore        rents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public PriceOfRentsRestore getMonthOfPrices() {
        return monthOfPrices;
    }

    public void setMonthOfPrices(PriceOfRentsRestore monthOfPrices) {
        this.monthOfPrices = monthOfPrices;
    }

    public RentsRestore getRents() {
        return rents;
    }

    public void setRents(RentsRestore rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id='" + id + '\'' +
                ", offerId='" + offerId + '\'' +
                ", monthOfPrices=" + monthOfPrices +
                ", rents=" + rents +
                '}';
    }
}
