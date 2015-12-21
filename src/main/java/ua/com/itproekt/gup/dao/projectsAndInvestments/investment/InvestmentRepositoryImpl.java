package ua.com.itproekt.gup.dao.projectsAndInvestments.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.ApplicationForInvestment;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPost;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPostFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;

@Repository
public class InvestmentRepositoryImpl implements InvestmentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(InvestorPost.class)) {
            mongoTemplate.createCollection(InvestorPost.class);
        }
    }

    @Override
    public void create(InvestorPost investorPost) {
        mongoTemplate.insert(investorPost);
    }

    @Override
    public InvestorPost findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, InvestorPost.class);
    }

    @Override
    public InvestorPost findInvestorAndUpdate(InvestorPost investorPost) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(investorPost);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, InvestorPost.class);
    }

    @Override
    public boolean investorPostExist(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, InvestorPost.class);
    }

    @Override
    public EntityPage<InvestorPost> findInvestorPostsWihOptions(InvestorPostFilterOptions investorPostFO) {
        Query query = new Query();

        if (investorPostFO.getuId() != null) {
            query.addCriteria(Criteria.where("uId").is(investorPostFO.getuId()));
        }

        if (investorPostFO.getCategoriesOfIndustry() != null) {
            query.addCriteria(Criteria.where("categoriesOfIndustry").in(investorPostFO.getCategoriesOfIndustry()));
        }

        query.skip(investorPostFO.getSkip());
        query.limit(investorPostFO.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, InvestorPost.class),
                                mongoTemplate.find(query, InvestorPost.class));
    }

    @Override
    public void addApplication(String investorPostId, ApplicationForInvestment applicationForInvestment) {
        Query query = new Query(Criteria.where("id").is(investorPostId));

        Update update = new Update()
                .push("thoseInNeed", applicationForInvestment)
                .inc("totalThoseInNeed", 1);

        mongoTemplate.updateFirst(query, update, InvestorPost.class);
    }
}
