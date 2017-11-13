package ua.com.gup.dto.offer.view;

import java.math.BigDecimal;

public class OfferViewCoordinatesDTO {
    private String offerSeoUrl;
    private BigDecimal[] coordinates;

    public OfferViewCoordinatesDTO(String offerSeoUrl, BigDecimal[] coordinates) {
        this.offerSeoUrl = offerSeoUrl;
        this.coordinates = coordinates;
    }

    public String getOfferSeoUrl() {
        return offerSeoUrl;
    }

    public BigDecimal[] getCoordinates() {
        return coordinates;
    }
}
