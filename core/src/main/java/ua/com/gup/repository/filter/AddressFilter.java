package ua.com.gup.repository.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Address filter model")
public class AddressFilter {

    @ApiModelProperty("Country codes with ',' delimiter")
    private String countries;
    @ApiModelProperty("Area codes with ',' delimiter")
    private String areas;
    @ApiModelProperty("City codes with ',' delimiter")
    private String cities;

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}
