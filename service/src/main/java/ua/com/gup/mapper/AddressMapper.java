package ua.com.gup.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.dto.offer.OfferAddressDTO;
import ua.com.gup.model.offer.Address;

@Component
public class AddressMapper {

    public OfferAddressDTO addressToAddressDTO(Address address) {
        OfferAddressDTO offerAddressDTO = new OfferAddressDTO();
        if (address != null) {
            offerAddressDTO.setLat(address.getLat());
            offerAddressDTO.setLng(address.getLng());
            offerAddressDTO.setLevel(address.getLevel());
            offerAddressDTO.setCountry(address.getCountry());
            offerAddressDTO.setRegion(address.getRegion());
            offerAddressDTO.setDistrict(address.getDistrict());
            offerAddressDTO.setCouncil(address.getCouncil());
            offerAddressDTO.setCity(address.getCity());
            offerAddressDTO.setFullAddress(address.getFullAddress());
        }
        return offerAddressDTO;
    }

    public Address addressDTOToAddress(OfferAddressDTO offerAddressDTO) {
        Address address = new Address();
        if (offerAddressDTO != null) {
            address.setLng(offerAddressDTO.getLng());
            address.setLat(offerAddressDTO.getLat());
            address.setLevel(offerAddressDTO.getLevel());
            address.setCountry(offerAddressDTO.getCountry());
            address.setRegion(offerAddressDTO.getRegion());
            address.setDistrict(offerAddressDTO.getDistrict());
            address.setCouncil(offerAddressDTO.getCouncil());
            address.setCity(offerAddressDTO.getCity());
            address.setFullAddress(offerAddressDTO.getFullAddress());
        }
        return address;
    }
}
