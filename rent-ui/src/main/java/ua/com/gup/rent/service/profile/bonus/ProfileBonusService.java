package ua.com.gup.rent.service.profile.bonus;

import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;

import java.util.List;

/**
 * @author Victor Dvorak
 **/
public interface ProfileBonusService extends RentOfferGenericService<ProfileBonus, String> {

    void save(ProfileBonus profileBonus);

    void update(ProfileBonus profileBonus);

    List<ProfileBonus> findAll();

    ProfileBonus findOneByCode(String code);

    ProfileBonus findOneByName(String name);

    ProfileBonus findOneById(String id);

    void delete(String id);

}
