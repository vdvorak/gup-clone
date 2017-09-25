package ua.com.gup.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonGeoDTO {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "level")
    private Integer level;
    @JsonProperty(value = "parent")
    private Long parent;
    @JsonProperty("country")
    private GeoDto country;
    @JsonProperty("region")
    private GeoDto region;
    @JsonProperty("district")
    private GeoDto district;
    @JsonProperty("council")
    private GeoDto council;
    @JsonProperty("city")
    private GeoDto city;

    public GeoDto getCountry() {
        return country;
    }

    public void setCountry(GeoDto country) {
        this.country = country;
    }

    public GeoDto getRegion() {
        return region;
    }

    public void setRegion(GeoDto region) {
        this.region = region;
    }

    public GeoDto getDistrict() {
        return district;
    }

    public void setDistrict(GeoDto district) {
        this.district = district;
    }

    public GeoDto getCouncil() {
        return council;
    }

    public void setCouncil(GeoDto council) {
        this.council = council;
    }

    public GeoDto getCity() {
        return city;
    }

    public void setCity(GeoDto city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }


}
