package ua.com.itproekt.gup.model.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.offer.AddressArea;

import java.math.BigDecimal;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private AddressArea country;
    private AddressArea district;
    private AddressArea city;
    private String fullAddress;
    private BigDecimal lat;
    private BigDecimal lng;

    public AddressArea getCountry() {
        return country;
    }

    public void setCountry(AddressArea country) {
        this.country = country;
    }

    public AddressArea getDistrict() {
        return district;
    }

    public void setDistrict(AddressArea district) {
        this.district = district;
    }

    public AddressArea getCity() {
        return city;
    }

    public void setCity(AddressArea city) {
        this.city = city;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country=" + country +
                ", district=" + district +
                ", city=" + city +
                ", fullAddress='" + fullAddress + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
