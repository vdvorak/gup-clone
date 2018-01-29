package ua.com.gup.rent.service.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.bonus.RentOfferBonusDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

/**
 * @author Victor Dvorak
 **/
@Service
public  class RentOfferBonusServiceImpl extends RentOfferGenericServiceImpl<RentOfferBonusDTO,String> implements RentOfferBonusService  {

    @Autowired
    public RentOfferBonusServiceImpl(@Qualifier("rentOfferBonusRepositoryImpl") RentOfferGenericRepository rentOfferGenericRepository) {
        super(rentOfferGenericRepository);
    }

    @Override
    public RentOfferBonus save(RentOfferBonusDTO rentOfferCategoryAttributeCreateDTO) {
        return null; //getRepository().create();
    }

    @Override
    public List<RentOfferBonus> findAll() {
        return null;
    }

    @Override
    public RentOfferBonus findOne(String id) {
        return null;
    }

    @Override
    public Optional<RentOfferBonus> findOneByCode(String code) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Map<Integer, SortedSet<RentOfferBonusDTO>> findAllRentOfferBonusDTO() {
        return null;
    }
}
