package ua.com.gup.rent.repository.bonus;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.bonus.RentOfferBonus;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentOfferBonusRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferBonus, String> implements RentOfferBonusRepository {

    public RentOfferBonusRepositoryImpl() {
        super(RentOfferBonus.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferBonus.class)) {
            mongoTemplate.createCollection(RentOfferBonus.class);
        }
    }

    @Override
    public RentOfferBonus findOneByCode(String code) {
        Query query = new Query(Criteria.where("code").is(code));
        return mongoTemplate.findOne(query, RentOfferBonus.class);
    }

    @Override
    public RentOfferBonus findOneByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, RentOfferBonus.class);
    }

    @Override
    public RentOfferBonus findOneById(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, RentOfferBonus.class);
    }
}
