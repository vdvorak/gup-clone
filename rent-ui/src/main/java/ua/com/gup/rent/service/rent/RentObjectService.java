package ua.com.gup.rent.service.rent;


import ua.com.gup.rent.service.abstracted.generic.RentGenericService;
import ua.com.gup.rent.service.dto.rent.RentCreateDTO;
import ua.com.gup.rent.service.dto.rent.RentEditDTO;
import ua.com.gup.rent.service.dto.rent.RentDTO;
import ua.com.gup.rent.service.dto.rent.RentShortDetailsDTO;

import java.util.List;

public interface RentObjectService extends RentGenericService<RentDTO, String> {

    void create(RentCreateDTO t);

    void update(RentEditDTO t);

    void deleteById(String rentObjectId);

    List<RentShortDetailsDTO> findAll();

//    List<ShortDetailsRentObjectDTO> findByFilter();
}
