package ua.com.gup.rent.service.rent;

import ua.com.gup.rent.dto.rent.CreateRentObjectDTO;
import ua.com.gup.rent.dto.rent.EditRentObjectDTO;
import ua.com.gup.rent.dto.rent.RentObjectDTO;
import ua.com.gup.rent.dto.rent.ShortDetailsRentObjectDTO;
import ua.com.gup.rent.service.abstracted.generic.GenericService;

import java.util.List;

public interface RentObjectService extends GenericService<RentObjectDTO, String> {

    void create(CreateRentObjectDTO t);

    void update(EditRentObjectDTO t);

    void deleteById(String rentObjectId);

    List<ShortDetailsRentObjectDTO> findAll();
}
