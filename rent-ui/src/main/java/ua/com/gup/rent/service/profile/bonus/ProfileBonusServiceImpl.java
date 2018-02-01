package ua.com.gup.rent.service.profile.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.repository.profile.bonus.RentOfferProfileBonusRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Dvorak
 **/
@Service
public class ProfileBonusServiceImpl extends RentOfferGenericServiceImpl<ProfileEditBonusDTO, String> implements ProfileBonusService {

    @Autowired
    private ProfileBonusMapper profileBonusMapper;

    @Autowired
    public ProfileBonusServiceImpl(RentOfferProfileBonusRepository rentOfferProfileBonusRepository) {
        super(rentOfferProfileBonusRepository);
    }

    @Override
    public void save(ProfileEditBonusDTO profileBonusDTO) {
        ProfileBonus profileBonus = profileBonusMapper.fromDTOToModel(profileBonusDTO);
        getRepository().create(profileBonus);
    }

    @Override
    public void update(ProfileEditBonusDTO profileBonusDTO) {
        getRepository().update(profileBonusMapper.fromDTOToModel(profileBonusDTO));
    }


    @Override
    public List<ProfileEditBonusDTO> findAll() {
        List<ProfileEditBonusDTO> listProfileBonusDTO = new ArrayList<ProfileEditBonusDTO>();
        for (ProfileBonus item : getRepository().findAll()) {
            listProfileBonusDTO.add(profileBonusMapper.fromModelToDTO(item));
        }
        return listProfileBonusDTO;
    }

    @Override
    public ProfileEditBonusDTO findOneByCode(String code) {
        return profileBonusMapper.fromModelToDTO(getRepository().findOneByCode(code));
    }

    @Override
    public ProfileEditBonusDTO findOneByName(String name) {
        return profileBonusMapper.fromModelToDTO(getRepository().findOneByName(name));
    }

    @Override
    public void delete(String id) {
        getRepository().delete(getRepository().findOneById(id));
    }

    @Override
    protected RentOfferProfileBonusRepository getRepository() {
        return (RentOfferProfileBonusRepository) super.getRepository();
    }
}
