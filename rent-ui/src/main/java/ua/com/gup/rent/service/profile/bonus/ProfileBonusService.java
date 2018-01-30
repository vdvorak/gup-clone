package ua.com.gup.rent.service.profile.bonus;

import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;

import java.util.List;

/**
 * @author Victor Dvorak
 **/
public interface ProfileBonusService extends RentOfferGenericService<ProfileCreateBonusDTO, String> {

    void save(ProfileCreateBonusDTO profileCreateBonusDTO);

    List<ProfileCreateBonusDTO> findAll();

    ProfileCreateBonusDTO findOneByCode(String code);

    ProfileCreateBonusDTO findOneByName(String name);

    void delete(String id);

}
