package ua.com.itproekt.gup.dao.tender.doer;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DoerRepositoryImpl implements DoerRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    // query.fields().exclude("fieldName");

    @Override
    public void createDoer(Doer doer) {
        mongoTemplate.save(doer);
    }

    @Override
    public Doer findById(String id) {
//        return doerMongoRepository.findByIdWholeProfile(id);
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Doer.class);
    }

    @Override
    public Doer findDoerAndUpdate(Doer doer) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(doer);
    }

    @Override
    public void update(Doer doer) {
        mongoTemplate.save(doer);
    }

    @Override
    public int deleteDoerById(String id) {
//        return doerMongoRepository.deleteById(id);
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Doer.class);
        return result.getN();
    }

    @Override
    public boolean doerExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, Doer.class);
    }

    @Override
    public EntityPage<Doer> findWihOptions(DoerFilterOptions doerFilterOptions) {

        Query query = new Query();
        if (doerFilterOptions.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").all(doerFilterOptions.getAuthorId()));
        }


        if (doerFilterOptions.getClientId() != null) {
            query.addCriteria(Criteria.where("clients").elemMatch(Criteria.where("id").is(doerFilterOptions.getClientId())));
        }

        if (doerFilterOptions.getTitle() != null) {
            query.addCriteria(Criteria.where("title").all(doerFilterOptions.getTitle()));
        }

        if (doerFilterOptions.getBody() != null) {
            query.addCriteria(Criteria.where("body").all(doerFilterOptions.getBody()));
        }


        if (doerFilterOptions.getSearchField() != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("title").regex("(?i:.*" + doerFilterOptions.getSearchField() + ".*)"),
                    Criteria.where("body").regex("(?i:.*" + doerFilterOptions.getSearchField() + ".*)")));
        }

        if (doerFilterOptions.getNaceIdIn() != null) {
            query.addCriteria(Criteria.where("naceId").in(doerFilterOptions.getNaceIdIn()));
        }

        if (doerFilterOptions.getSortDirection() != null && doerFilterOptions.getSortField() != null) {
            query.with(new Sort(Sort.Direction.fromString(doerFilterOptions.getSortDirection()), doerFilterOptions.getSortField()));
        }

        query.skip(doerFilterOptions.getSkip());
        query.limit(doerFilterOptions.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, Doer.class),
                mongoTemplate.find(query, Doer.class));
    }


    @Override
    public Set<String> getMatchedNames(String term) {
        String searchFieldRegex = "(?i:.*" + term + ".*)";
        Query query = new Query();

        query.addCriteria(new Criteria().orOperator(Criteria.where("title").regex(searchFieldRegex)));

        query.fields().include("title");
        query.skip(0);
        query.limit(10);

        return mongoTemplate.find(query, Doer.class).stream().map(Doer::getTitle).collect(Collectors.toSet());
    }
}
