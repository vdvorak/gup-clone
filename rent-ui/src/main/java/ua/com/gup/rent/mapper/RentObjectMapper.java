package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.rent.Rent;
import ua.com.gup.rent.model.rent.RentStatus;
import ua.com.gup.rent.service.dto.rent.RentCreateDTO;
import ua.com.gup.rent.service.dto.rent.RentShortDetailsDTO;

@Component
public class RentObjectMapper {

    @Autowired
    private RentPriceMapper rentPriceMapper;

    public RentShortDetailsDTO fromRentObjectToShortDTO(Rent o) {
        RentShortDetailsDTO dto = new RentShortDetailsDTO();
        dto.setTitle(o.getTitle());
        dto.setDescription(o.getDescription());
        return dto;
    }


    public Rent fromCreateDTOToRentObject(RentCreateDTO rentObjectDTO) {
        Rent rent = new Rent();
        rent.setTitle(rentObjectDTO.getTitle());
        rent.setDescription(rentObjectDTO.getDescription());
        rent.setStatus(RentStatus.NEW);
        rent.setRentPrice(rentPriceMapper.fromDTOToModel(rentObjectDTO.getPrice()));

        return rent;
    }
}
