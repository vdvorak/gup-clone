package ua.com.gup.rent.repository.profile.bonus;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.profile.bonus.RentOfferProfileBonus;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentOfferProfileBonusRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferProfileBonus, String> implements RentOfferProfileBonusRepository {

    public RentOfferProfileBonusRepositoryImpl() {
        super(RentOfferProfileBonus.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferProfileBonus.class)) {
            mongoTemplate.createCollection(RentOfferProfileBonus.class);
        }
    }

    @Override
    public RentOfferProfileBonus findOneByCode(String code) {
        Query query = new Query(Criteria.where("code").is(code));
        return mongoTemplate.findOne(query, RentOfferProfileBonus.class);
    }

    @Override
    public RentOfferProfileBonus findOneByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, RentOfferProfileBonus.class);
    }

    @Override
    public RentOfferProfileBonus findOneById(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, RentOfferProfileBonus.class);
    }
}
