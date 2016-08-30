package ua.com.itproekt.gup.dao.reservationSchedule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.reservationSchedule.ReservationSchedule;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

@Repository
public class ReservationScheduleRepositoryImpl implements ReservationScheduleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ReservationSchedule findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, ReservationSchedule.class);
    }

    @Override
    public ReservationSchedule update(ReservationSchedule reservationSchedule) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(reservationSchedule);
    }
}
