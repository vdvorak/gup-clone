package ua.com.gup.rent.service.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentOfferBonusMapper;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.bonus.RentOfferBonusRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.bonus.RentOfferCreateBonusDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Dvorak
 **/
@Service
public class RentOfferBonusServiceImpl extends RentOfferGenericServiceImpl<RentOfferCreateBonusDTO, String> implements RentOfferBonusService {

    @Autowired
    RentOfferBonusMapper rentOfferBonusMapper;

    @Autowired
    public RentOfferBonusServiceImpl(RentOfferBonusRepository rentOfferBonusRepository) {
        super(rentOfferBonusRepository);
    }

    @Override
    public void save(RentOfferCreateBonusDTO rentOfferCreateBonusDTO) {
        RentOfferBonus rentOfferBonus = rentOfferBonusMapper.fromDTOToModel(rentOfferCreateBonusDTO);
        getRepository().create(rentOfferBonus);
    }

    @Override
    public List<RentOfferCreateBonusDTO> findAll() {
        List<RentOfferCreateBonusDTO> listRentOfferCreateBonusDTO = new ArrayList<RentOfferCreateBonusDTO>();
        for (Object item : getRepository().findAll()) {
            listRentOfferCreateBonusDTO.add(rentOfferBonusMapper.fromModelToDTO((RentOfferBonus) item));
        }
        return listRentOfferCreateBonusDTO;
    }

    @Override
    public RentOfferCreateBonusDTO findOneByCode(String code) {
        return rentOfferBonusMapper.fromModelToDTO(getRepository().findOneByCode(code));
    }

    @Override
    public RentOfferCreateBonusDTO findOneByName(String name) {
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
