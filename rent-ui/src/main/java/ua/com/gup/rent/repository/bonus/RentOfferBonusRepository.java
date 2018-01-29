package ua.com.gup.rent.repository.bonus;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.Optional;

/**
 * @author Victor Dvorak
 **/
public interface RentOfferBonusRepository extends MongoRepository<RentOfferBonus, String>,RentOfferGenericRepository<RentOfferBonus,String> {
    //this implements MongoRepository method
    Optional<RentOfferBonus> findOneByCode(String code);
    Optional<RentOfferBonus> findOneByName(String name);
}
