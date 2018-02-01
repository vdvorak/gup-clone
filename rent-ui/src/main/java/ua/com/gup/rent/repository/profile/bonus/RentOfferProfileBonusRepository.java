package ua.com.gup.rent.repository.profile.bonus;

import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferProfileBonusRepository extends RentOfferGenericRepository<ProfileBonus, String> {

    ProfileBonus findOneByCode(String code);

    ProfileBonus findOneByName(String name);

    ProfileBonus findOneById(String id);

}
