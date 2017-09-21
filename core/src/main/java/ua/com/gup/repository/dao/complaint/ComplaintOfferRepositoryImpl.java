package ua.com.gup.repository.dao.complaint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.gup.domain.complaint.ComplaintOffer;
import ua.com.gup.domain.complaint.ComplaintOfferStatus;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ComplaintOfferRepositoryImpl implements ComplaintOfferRepository {

    private final Logger log = LoggerFactory.getLogger(ComplaintOfferRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(ComplaintOffer complaintOffer) {
        mongoTemplate.insert(complaintOffer);
    }

    @Override
    public void update(ComplaintOffer complaintOffer) {
        mongoTemplate.save(complaintOffer);
    }

    @Override
    public ComplaintOffer findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, ComplaintOffer.class);
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, ComplaintOffer.class);
    }

    @Override
    public List<ComplaintOffer> findAll(){
        return mongoTemplate.findAll(ComplaintOffer.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<ComplaintOffer> findAllByOfferId(String offerId) {
        Query query = new Query(Criteria.where("offerId").is(offerId));
        return mongoTemplate.find(query, ComplaintOffer.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<ComplaintOffer> findAllByInitiatorId(String initiatorId) {
        Query query = new Query(Criteria.where("initiatorId").is(initiatorId));
        return mongoTemplate.find(query, ComplaintOffer.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<ComplaintOffer> findAllByStatus(ComplaintOfferStatus status) {
        Query query = new Query(Criteria.where("status").is(status));
        return mongoTemplate.find(query, ComplaintOffer.class).stream().collect(Collectors.toList());
    }

}
