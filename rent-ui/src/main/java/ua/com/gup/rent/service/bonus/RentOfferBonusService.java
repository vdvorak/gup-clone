package ua.com.gup.rent.service.bonus;

import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.bonus.RentOfferCreateBonusDTO;

import java.util.List;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferBonusService extends RentOfferGenericService<RentOfferCreateBonusDTO, String> {

    void save(RentOfferCreateBonusDTO rentOfferCreateBonusDTO);

    List<RentOfferCreateBonusDTO> findAll();

    RentOfferCreateBonusDTO findOneByCode(String code);

    RentOfferCreateBonusDTO findOneByName(String name);

    void delete(String id);

}
