package ua.com.gup.mongo.composition.domain.offer;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.mongo.model.offer.Price;
import ua.com.gup.mongo.model.statistic.OfferStatistic;

import java.time.ZonedDateTime;

/**
 * Database table entity Offer.
 */

@Document(collection = Offer.COLLECTION_NAME, language = "russian")
@CompoundIndexes({
        @CompoundIndex(name = "status_categoriesRegExp", def = "{'status': 1, 'categoriesRegExp': 1}")
})
public class Offer extends CommonRentOffer {

    public static final String COLLECTION_NAME = "offer";
    private static final long serialVersionUID = 1L;
   
    private ZonedDateTime createdDate = ZonedDateTime.now();

    private String lastModifiedBy;

    @Indexed
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    private Price price;

    private OfferStatistic statistic;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public OfferStatistic getStatistic() {
        if (statistic == null) {
            statistic = new OfferStatistic();
        }
        return statistic;
    }

    public void setStatistic(OfferStatistic statistic) {
        this.statistic = statistic;
    }


    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(getCreatedDate().toLocalDate());
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(getCreatedDate().toLocalDate());
    }
}
