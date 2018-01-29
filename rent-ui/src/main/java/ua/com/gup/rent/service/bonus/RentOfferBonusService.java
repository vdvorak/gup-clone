package ua.com.gup.rent.service.bonus;

import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.bonus.RentOfferBonusDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferBonusService extends RentOfferGenericService<RentOfferBonusDTO, String> {

    RentOfferBonus save(RentOfferBonusDTO rentOfferCategoryAttributeCreateDTO);

    List<RentOfferBonus> findAll();

    RentOfferBonus findOne(String id);

    Optional<RentOfferBonus> findOneByCode(String code);

    void delete(String id);

    Map<Integer, SortedSet<RentOfferBonusDTO>> findAllRentOfferBonusDTO();
}
