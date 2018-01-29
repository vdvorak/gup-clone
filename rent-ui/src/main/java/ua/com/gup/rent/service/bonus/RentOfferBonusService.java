package ua.com.gup.rent.service.bonus;

import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.bonus.RentOfferBonusDTO;

import java.util.List;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferBonusService extends RentOfferGenericService<RentOfferBonusDTO, String> {

    void save(RentOfferBonusDTO rentOfferBonusDTO);

    List<RentOfferBonusDTO> findAll();

    RentOfferBonusDTO findOneByCode(String code);

    RentOfferBonusDTO findOneByName(String name);

    void delete(String id);

}
