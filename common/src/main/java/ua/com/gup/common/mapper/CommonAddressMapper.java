package ua.com.gup.common.mapper;

import ua.com.gup.common.dto.CommonAddressDTO;
import ua.com.gup.common.model.address.Address;

public class CommonAddressMapper {
    public CommonAddressDTO addressToAddressDTO(Address address) {
        CommonAddressDTO offerAddressDTO = new CommonAddressDTO();
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

    public Address addressDTOToAddress(CommonAddressDTO offerAddressDTO) {
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
