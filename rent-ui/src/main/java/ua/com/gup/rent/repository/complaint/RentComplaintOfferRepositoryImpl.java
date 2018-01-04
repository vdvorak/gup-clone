package ua.com.gup.rent.repository.complaint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOfferStatus;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentComplaintOfferRepositoryImpl implements RentComplaintOfferRepository {

    private final Logger log = LoggerFactory.getLogger(RentComplaintOfferRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(RentComplaintOffer complaintOffer) {
        mongoTemplate.insert(complaintOffer);
    }

    @Override
    public void update(RentComplaintOffer complaintOffer) {
        mongoTemplate.save(complaintOffer);
    }

    @Override
    public RentComplaintOffer findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, RentComplaintOffer.class);
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, RentComplaintOffer.class);
    }

    @Override
    public List<RentComplaintOffer> findAll(){
        return mongoTemplate.findAll(RentComplaintOffer.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<RentComplaintOffer> findAllByOfferId(String offerId) {
        Query query = new Query(Criteria.where("offerId").is(offerId));
        return mongoTemplate.find(query, RentComplaintOffer.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<RentComplaintOffer> findAllByInitiatorId(String initiatorId) {
        Query query = new Query(Criteria.where("initiatorId").is(initiatorId));
        return mongoTemplate.find(query, RentComplaintOffer.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<RentComplaintOffer> findAllByStatus(RentComplaintOfferStatus status) {
        Query query = new Query(Criteria.where("status").is(status));
        return mongoTemplate.find(query, RentComplaintOffer.class).stream().collect(Collectors.toList());
    }

}
