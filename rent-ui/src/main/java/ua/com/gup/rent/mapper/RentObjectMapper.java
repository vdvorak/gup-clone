package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.dto.rent.CreateRentObjectDTO;
import ua.com.gup.rent.dto.rent.ShortDetailsRentObjectDTO;
import ua.com.gup.rent.model.RentStatus;
import ua.com.gup.rent.model.mongo.RentObject;

@Component
public class RentObjectMapper {

    @Autowired
    private RentPriceMapper rentPriceMapper;

    public ShortDetailsRentObjectDTO fromRentObjectToShortDTO(RentObject o) {
        ShortDetailsRentObjectDTO dto = new ShortDetailsRentObjectDTO();
        dto.setTitle(o.getTitle());
        dto.setDescription(o.getDescription());
        return dto;
    }


    public RentObject fromCreateDTOToRentObject(CreateRentObjectDTO rentObjectDTO) {
        RentObject rentObject = new RentObject();
        rentObject.setTitle(rentObjectDTO.getTitle());
        rentObject.setDescription(rentObjectDTO.getDescription());
        rentObject.setStatus(RentStatus.NEW);
        rentObject.setPrice(rentPriceMapper.fromDTOToModel(rentObjectDTO.getPrice()));

        return rentObject;
    }
}
