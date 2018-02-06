package ua.com.gup.repository.geo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.mongo.composition.domain.geo.GeoModel;
import ua.com.gup.common.model.Locale;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class GeoRepositoryImpl implements GeoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(GeoModel.class)) {
            mongoTemplate.createCollection(GeoModel.class);
        }
    }

    @Override
    public GeoModel findOne(Long id) {
        return mongoTemplate.findById(id, GeoModel.class);
    }

    @Override
    public List<GeoModel> findByIds(Long[] ids) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query, GeoModel.class);
    }

    @Override
    public List<GeoModel> findByNameStartWithAndLocale(String name, Locale lang) {
        Query query = new Query();
        query.addCriteria(Criteria.where(String.format("name.%s", lang)).regex(String.format("^%s", name), "i"));
        return mongoTemplate.find(query, GeoModel.class);
    }


}
