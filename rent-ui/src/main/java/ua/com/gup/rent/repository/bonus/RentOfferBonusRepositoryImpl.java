package ua.com.gup.rent.repository.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author Victor Dvorak
 **/
@Repository
@Qualifier("rentBonusRepository")
public  class RentOfferBonusRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferBonus,String> {

    @Autowired
    private RentOfferBonusRepository rentOfferBonusRepository;

    public RentOfferBonusRepositoryImpl() {
        super(RentOfferBonus.class);
    }
    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferBonus.class)) {
            mongoTemplate.createCollection(RentOfferBonus.class);
        }
    }

    public Optional<RentOfferBonus> findOneByCode(String code) {
        return rentOfferBonusRepository.findOneByCode(code);
    }

    public Optional<RentOfferBonus> findOneByName(String name) {
        return rentOfferBonusRepository.findOneByName(name);
    }
}
