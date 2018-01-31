package ua.com.gup.rent.repository.profile.bonus;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;

/**
 * @author Victor Dvorak
 **/
@Repository
public class RentOfferProfileBonusRepositoryImpl extends RentOfferGenericRepositoryImpl<ProfileBonus, String> implements RentOfferProfileBonusRepository {

    public RentOfferProfileBonusRepositoryImpl() {
        super(ProfileBonus.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(ProfileBonus.class)) {
            mongoTemplate.createCollection(ProfileBonus.class);
        }
    }

    @Override
    public ProfileBonus findOneByCode(String code) {
        Query query = new Query(Criteria.where("code").is(code));
        return mongoTemplate.findOne(query, ProfileBonus.class);
    }

    @Override
    public ProfileBonus findOneByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, ProfileBonus.class);
    }

    @Override
    public ProfileBonus findOneById(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, ProfileBonus.class);
    }
}
