package ua.com.gup.rent.service.profile.bonus;

import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;

import java.util.List;

/**
 * @author Victor Dvorak
 **/
public interface ProfileBonusService extends RentOfferGenericService<CommonProfileBonusDTO, String> {

    void save(CommonProfileBonusDTO createProfileBonusDTO);

    void update(CommonProfileBonusDTO editProfileBonusDTO);

    List<ProfileEditBonusDTO> findAll();

    ProfileEditBonusDTO findOneByCode(String code);

    ProfileEditBonusDTO findOneByName(String name);

    void delete(String id);

}
