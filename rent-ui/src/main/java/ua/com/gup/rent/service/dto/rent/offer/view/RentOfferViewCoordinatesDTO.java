package ua.com.gup.rent.service.dto.rent.offer.view;

import java.math.BigDecimal;

public class RentOfferViewCoordinatesDTO {
    private String offerSeoUrl;
    private BigDecimal[] coordinates;

    public RentOfferViewCoordinatesDTO(String offerSeoUrl, BigDecimal[] coordinates) {
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
