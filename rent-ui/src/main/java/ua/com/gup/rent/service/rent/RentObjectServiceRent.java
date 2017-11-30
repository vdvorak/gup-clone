package ua.com.gup.rent.service.rent;

import java.util.List;

public interface RentObjectServiceRent extends ua.com.gup.rent.service.abstracted.generic.RentGenericService<ua.com.gup.rent.dto.rent.RentObjectDTO, String> {

    void create(ua.com.gup.rent.dto.rent.RentCreateObjectDTO t);

    void update(ua.com.gup.rent.dto.rent.RentEditObjectDTO t);

    void deleteById(String rentObjectId);

    List<ua.com.gup.rent.dto.rent.RentShortDetailsObjectDTO> findAll();
}
