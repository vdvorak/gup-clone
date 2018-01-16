package ua.com.gup.mongo.composition.domain.offer;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.mongo.model.offer.Price;
import ua.com.gup.mongo.model.statistic.OfferStatistic;

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


    private String lastModifiedBy;
    private Price price;

    @Indexed(unique = true)
    private String seoUrl;

    private OfferStatistic statistic;

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

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


    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }


    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(getCreatedDate().toLocalDate());
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(getCreatedDate().toLocalDate());
    }
}
