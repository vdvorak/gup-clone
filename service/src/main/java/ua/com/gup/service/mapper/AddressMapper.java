package ua.com.gup.service.mapper;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.Address;
import ua.com.gup.service.dto.AddressDTO;

@Component
public class AddressMapper {
    public AddressDTO addressToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLat(address.getLocation().getX());
        addressDTO.setLng(address.getLocation().getY());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setCity(address.getCity());
        return addressDTO;
    }

    public Address addressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setLocation(new GeoJsonPoint(addressDTO.getLat(), addressDTO.getLng()));
        address.setCountry(addressDTO.getCountry());
        address.setDistrict(addressDTO.getDistrict());
        address.setCity(addressDTO.getCity());
        return address;
    }
}
