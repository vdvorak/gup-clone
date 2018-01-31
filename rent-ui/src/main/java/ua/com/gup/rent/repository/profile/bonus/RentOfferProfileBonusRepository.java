package ua.com.gup.rent.repository.profile.bonus;

import ua.com.gup.rent.model.mongo.profile.bonus.RentOfferProfileBonus;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferProfileBonusRepository extends RentOfferGenericRepository<RentOfferProfileBonus, String> {

    RentOfferProfileBonus findOneByCode(String code);

    RentOfferProfileBonus findOneByName(String name);

    RentOfferProfileBonus findOneById(String id);

}
