package ua.com.gup.rent.repository.bonus;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
@Qualifier("rentOfferBonusRepositoryImpl")
public class RentOfferBonusRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferBonus, String> {

    public RentOfferBonusRepositoryImpl() {
        super(RentOfferBonus.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferBonus.class)) {
            mongoTemplate.createCollection(RentOfferBonus.class);
        }
    }
}
