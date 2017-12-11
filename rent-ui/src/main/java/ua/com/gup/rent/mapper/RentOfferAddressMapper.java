package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.RentOfferAddress;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferAddressDTO;

@Component
public class RentOfferAddressMapper {

    public RentOfferAddressDTO addressToAddressDTO(RentOfferAddress address) {
        RentOfferAddressDTO offerAddressDTO = new RentOfferAddressDTO();
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

    public RentOfferAddress addressDTOToAddress(RentOfferAddressDTO offerAddressDTO) {
        RentOfferAddress address = new RentOfferAddress();
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
