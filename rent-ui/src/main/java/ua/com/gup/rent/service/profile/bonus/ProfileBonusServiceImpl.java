package ua.com.gup.rent.service.profile.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.repository.profile.bonus.RentOfferProfileBonusRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Dvorak
 **/
@Service
public class ProfileBonusServiceImpl extends RentOfferGenericServiceImpl<ProfileBonus, String> implements ProfileBonusService {


    @Autowired
    public ProfileBonusServiceImpl(RentOfferProfileBonusRepository rentOfferProfileBonusRepository) {
        super(rentOfferProfileBonusRepository);
    }

    @Override
    public void save(ProfileBonus profileBonus) {
        getRepository().create(profileBonus);
    }

    @Override
    public void update(ProfileBonus profileBonus) {
        //profileBonusMapper.fromDTOToModel((ProfileEditBonusDTO)editProfileBonusDTO)
        getRepository().update(profileBonus);
    }


    @Override
    public List<ProfileBonus> findAll() {
        //profileBonusMapper.fromModelToDTO(item)
        List<ProfileBonus> listProfileBonus = new ArrayList<ProfileBonus>();
        for (ProfileBonus item : getRepository().findAll()) {
            listProfileBonus.add(item);
        }
        return listProfileBonus;
    }

    @Override
    public ProfileBonus findOneByCode(String code) {
        //profileBonusMapper.fromModelToDTO(getRepository().findOneByCode(code));
        return getRepository().findOneByCode(code);
    }

    @Override
    public ProfileBonus findOneByName(String name) {
        //profileBonusMapper.fromModelToDTO(getRepository().findOneByName(name));
        return getRepository().findOneByName(name);
    }

    @Override
    public ProfileBonus findOneById(String id) {
        return getRepository().findOneById(id);
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
