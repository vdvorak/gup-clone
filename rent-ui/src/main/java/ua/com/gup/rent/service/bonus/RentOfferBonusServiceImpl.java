package ua.com.gup.rent.service.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentOfferBonusMapper;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.bonus.RentOfferBonusRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.bonus.RentOfferBonusDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Dvorak
 **/
@Service
public class RentOfferBonusServiceImpl extends RentOfferGenericServiceImpl<RentOfferBonusDTO, String> implements RentOfferBonusService {

    @Autowired
    RentOfferBonusMapper rentOfferBonusMapper;

    @Autowired
    public RentOfferBonusServiceImpl(RentOfferBonusRepository rentOfferBonusRepository) {
        super(rentOfferBonusRepository);
    }

    @Override
    public void save(RentOfferBonusDTO rentOfferBonusDTO) {
        RentOfferBonus rentOfferBonus = rentOfferBonusMapper.fromDTOToModel(rentOfferBonusDTO);
        getRepository().create(rentOfferBonus);
    }

    @Override
    public List<RentOfferBonusDTO> findAll() {
        List<RentOfferBonusDTO> listRentOfferBonusDTO = new ArrayList<RentOfferBonusDTO>();
        for (Object item : getRepository().findAll()) {
            listRentOfferBonusDTO.add(rentOfferBonusMapper.fromModelToDTO((RentOfferBonus) item));
        }
        return listRentOfferBonusDTO;
    }

    @Override
    public RentOfferBonusDTO findOneByCode(String code) {
        return rentOfferBonusMapper.fromModelToDTO(getRepository().findOneByCode(code));
    }

    @Override
    public RentOfferBonusDTO findOneByName(String name) {
        return rentOfferBonusMapper.fromModelToDTO(getRepository().findOneByName(name));
    }

    @Override
    public void delete(String id) {
        getRepository().delete(getRepository().findOneById(id));
    }

    @Override
    protected RentOfferBonusRepository getRepository() {
        return (RentOfferBonusRepository) super.getRepository();
    }
}
