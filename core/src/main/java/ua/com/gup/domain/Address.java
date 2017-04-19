package ua.com.gup.domain;


import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class Address {

//    @GeoSpatialIndexed
    private GeoJsonPoint location;

    private AddressArea country;

    private AddressArea district;

    private AddressArea city;

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

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
}
