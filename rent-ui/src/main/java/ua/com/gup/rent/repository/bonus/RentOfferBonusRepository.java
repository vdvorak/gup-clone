package ua.com.gup.rent.repository.bonus;

import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferBonusRepository extends  RentOfferGenericRepository<RentOfferBonus, String> {
    RentOfferBonus findOneByCode(String code);
    RentOfferBonus findOneByName(String name);
    RentOfferBonus findOneById(String id);

}
