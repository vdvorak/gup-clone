package ua.com.gup.rent.service;

import ua.com.gup.rent.dto.rentobject.CreateRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.EditRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.RentObjectDTO;
import ua.com.gup.rent.dto.rentobject.ShortDetailsRentObjectDTO;

import java.util.List;

public interface RentObjectService extends GenericService<RentObjectDTO, String> {

    void create(CreateRentObjectDTO t);

    void update(EditRentObjectDTO t);

    void deleteById(String rentObjectId);

    List<ShortDetailsRentObjectDTO> findAll();
}
