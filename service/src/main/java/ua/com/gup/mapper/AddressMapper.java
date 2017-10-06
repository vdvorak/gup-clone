package ua.com.gup.mapper;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import ua.com.gup.model.offer.Address;
import ua.com.gup.dto.offer.OfferAddressDTO;
import ua.com.gup.dto.offer.OfferAddressShortDTO;

@Component
public class AddressMapper {

    public OfferAddressDTO addressToAddressDTO(Address address) {
        OfferAddressDTO offerAddressDTO = new OfferAddressDTO();
        if (address != null) {
            if (address.getLocation() != null) {
                offerAddressDTO.setLat(address.getLocation().getY());
                offerAddressDTO.setLng(address.getLocation().getX());
            }
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

    public OfferAddressShortDTO addressToAddressShortDTO(Address address) {
        OfferAddressShortDTO offerAddressShortDTO = new OfferAddressShortDTO();
        if (address != null) {
            if (address.getLocation() != null) {
                offerAddressShortDTO.setLat(address.getLocation().getX());
                offerAddressShortDTO.setLng(address.getLocation().getY());
            }
        }
        return offerAddressShortDTO;
    }

    public Address addressDTOToAddress(OfferAddressDTO offerAddressDTO) {
        Address address = new Address();
        if (offerAddressDTO != null) {
            address.setLocation(new GeoJsonPoint(offerAddressDTO.getLng(), offerAddressDTO.getLat()));
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
