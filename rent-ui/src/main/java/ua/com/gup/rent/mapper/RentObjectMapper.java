package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.RentStatus;
import ua.com.gup.rent.model.mongo.rent.RentObject;
import ua.com.gup.rent.service.dto.rent.RentCreateDTO;
import ua.com.gup.rent.service.dto.rent.RentShortDetailsDTO;

@Component
public class RentObjectMapper {

    @Autowired
    private RentPriceMapper rentPriceMapper;

    public RentShortDetailsDTO fromRentObjectToShortDTO(RentObject o) {
        RentShortDetailsDTO dto = new RentShortDetailsDTO();
        dto.setTitle(o.getTitle());
        dto.setDescription(o.getDescription());
        return dto;
    }


    public RentObject fromCreateDTOToRentObject(RentCreateDTO rentObjectDTO) {
        RentObject rentObject = new RentObject();
        rentObject.setTitle(rentObjectDTO.getTitle());
        rentObject.setDescription(rentObjectDTO.getDescription());
        rentObject.setStatus(RentStatus.NEW);
        rentObject.setRentPrice(rentPriceMapper.fromDTOToModel(rentObjectDTO.getPrice()));

        return rentObject;
    }
}
