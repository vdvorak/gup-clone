package ua.com.gup.rent.service.rent;

import ua.com.gup.rent.dto.rent.RentObjectDTO;
import ua.com.gup.rent.service.abstracted.generic.GenericService;

import java.util.List;

public interface RentObjectService extends GenericService<RentObjectDTO, String> {

    void create(ua.com.gup.rent.dto.rent.RentCreateObjectDTO t);

    void update(ua.com.gup.rent.dto.rent.RentEditObjectDTO t);

    void deleteById(String rentObjectId);

    List<ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO> findAll();
}
