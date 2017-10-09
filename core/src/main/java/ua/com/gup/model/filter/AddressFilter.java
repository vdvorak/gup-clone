package ua.com.gup.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Address filter model")
public class AddressFilter {

    @ApiModelProperty("Country codes with ',' delimiter")
    private String countries;
    @ApiModelProperty("Area codes with ',' delimiter")
    private String districts;
    @ApiModelProperty("City codes with ',' delimiter")
    private String cities;

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}
