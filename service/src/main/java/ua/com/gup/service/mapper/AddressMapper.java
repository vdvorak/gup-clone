package ua.com.gup.service.mapper;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.offer.model.Address;
import ua.com.gup.dto.offer.OfferAddressDTO;
import ua.com.gup.dto.offer.OfferAddressShortDTO;

@Component
public class AddressMapper {

    public OfferAddressDTO addressToAddressDTO(Address address) {
        OfferAddressDTO offerAddressDTO = new OfferAddressDTO();
        offerAddressDTO.setLat(address.getLocation().getX());
        offerAddressDTO.setLng(address.getLocation().getY());
        offerAddressDTO.setCountry(address.getCountry());
        offerAddressDTO.setDistrict(address.getDistrict());
        offerAddressDTO.setCity(address.getCity());
        offerAddressDTO.setFullAddress(address.getFullAddress());
        address.setLevel(offerAddressDTO.getLevel());
        return offerAddressDTO;
    }

    public OfferAddressShortDTO addressToAddressShortDTO(Address address) {
        OfferAddressShortDTO offerAddressShortDTO = new OfferAddressShortDTO();
        offerAddressShortDTO.setLat(address.getLocation().getX());
        offerAddressShortDTO.setLng(address.getLocation().getY());
        return offerAddressShortDTO;
    }

    public Address addressDTOToAddress(OfferAddressDTO offerAddressDTO) {
        Address address = new Address();
        address.setLocation(new GeoJsonPoint(offerAddressDTO.getLat(), offerAddressDTO.getLng()));
        address.setCountry(offerAddressDTO.getCountry());
        address.setDistrict(offerAddressDTO.getDistrict());
        address.setCity(offerAddressDTO.getCity());
        address.setFullAddress(offerAddressDTO.getFullAddress());
        address.setLevel(offerAddressDTO.getLevel());
        return address;
    }
}
