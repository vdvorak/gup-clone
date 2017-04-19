package ua.com.gup.service.dto;


/**
 * A DTO for creation the Offer entity.
 */
public class OfferCreateDTO extends OfferBaseDTO {

    private AddressDTO address;

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
