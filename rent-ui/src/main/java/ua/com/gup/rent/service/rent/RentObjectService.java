package ua.com.gup.rent.service.rent;


import ua.com.gup.rent.service.abstracted.generic.RentGenericService;
import ua.com.gup.rent.service.dto.rent.RentCreateObjectDTO;
import ua.com.gup.rent.service.dto.rent.RentEditObjectDTO;
import ua.com.gup.rent.service.dto.rent.RentObjectDTO;
import ua.com.gup.rent.service.dto.rent.RentShortDetailsObjectDTO;

import java.util.List;

public interface RentObjectService extends RentGenericService<RentObjectDTO, String> {

    void create(RentCreateObjectDTO t);

    void update(RentEditObjectDTO t);

    void deleteById(String rentObjectId);

    List<RentShortDetailsObjectDTO> findAll();

//    List<ShortDetailsRentObjectDTO> findByFilter();
}
