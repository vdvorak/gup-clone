package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.RentStatus;
import ua.com.gup.rent.model.mongo.RentObject;

@Component
public class RentObjectMapper {

    @Autowired
    private RentPriceMapper rentPriceMapper;

    public ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO fromRentObjectToShortDTO(RentObject o) {
        ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO dto = new ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO();
        dto.setTitle(o.getTitle());
        dto.setDescription(o.getDescription());
        return dto;
    }


    public RentObject fromCreateDTOToRentObject(ua.com.gup.rent.dto.rent.RentCreateObjectDTO rentObjectDTO) {
        RentObject rentObject = new RentObject();
        rentObject.setTitle(rentObjectDTO.getTitle());
        rentObject.setDescription(rentObjectDTO.getDescription());
        rentObject.setStatus(RentStatus.NEW);
        rentObject.setPrice(rentPriceMapper.fromDTOToModel(rentObjectDTO.getPrice()));

        return rentObject;
    }
}
