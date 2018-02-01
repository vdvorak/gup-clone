package ua.com.gup.rent.service.profile.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentOfferProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.RentOfferProfileBonus;
import ua.com.gup.rent.repository.profile.bonus.RentOfferProfileBonusRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Dvorak
 **/
@Service
public class ProfileBonusServiceImpl extends RentOfferGenericServiceImpl<ProfileCreateBonusDTO, String> implements ProfileBonusService {

    @Autowired
    RentOfferProfileBonusMapper rentOfferProfileBonusMapper;

    @Autowired
    public ProfileBonusServiceImpl(RentOfferProfileBonusRepository rentOfferProfileBonusRepository) {
        super(rentOfferProfileBonusRepository);
    }

    @Override
    public void save(ProfileCreateBonusDTO profileCreateBonusDTO) {
        RentOfferProfileBonus rentOfferProfileBonus = rentOfferProfileBonusMapper.fromDTOToModel(profileCreateBonusDTO);
        getRepository().create(rentOfferProfileBonus);
    }

    @Override
    public List<ProfileCreateBonusDTO> findAll() {
        List<ProfileCreateBonusDTO> listProfileCreateBonusDTO = new ArrayList<ProfileCreateBonusDTO>();
        for (Object item : getRepository().findAll()) {
            listProfileCreateBonusDTO.add(rentOfferProfileBonusMapper.fromModelToDTO((RentOfferProfileBonus) item));
        }
        return listProfileCreateBonusDTO;
    }

    @Override
    public ProfileCreateBonusDTO findOneByCode(String code) {
        return rentOfferProfileBonusMapper.fromModelToDTO(getRepository().findOneByCode(code));
    }

    @Override
    public ProfileCreateBonusDTO findOneByName(String name) {
        return rentOfferProfileBonusMapper.fromModelToDTO(getRepository().findOneByName(name));
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
