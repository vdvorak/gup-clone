package ua.com.itproekt.gup.dao.tender;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.model.tender.TenderType;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;


@Repository
public class TenderRepositoryImpl implements TenderRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    TenderMongoRepository tenderMongoRepository;

    //            query.fields().exclude("fieldName");

    @Override
    public void createTender(Tender tender) {
        mongoTemplate.save(tender);
    }

    @Override
    public Tender findById(String id) {
//        return tenderMongoRepository.findById(id);
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Tender.class);
    }

    @Override
    public Tender findTenderAndUpdate(Tender tender) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(tender);
    }

    @Override
    public void update(Tender tender) {
        mongoTemplate.save(tender);
    }

    @Override
    public int deleteTenderById(String id) {
//        return tenderMongoRepository.deleteById(id);
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Tender.class);
        return result.getN();
    }

    @Override
    public boolean tenderExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, Tender.class);
    }

    @Override
    public EntityPage<Tender> findWihOptions(TenderFilterOptions tenderFilterOptions, Profile currUser) {

//        Фильтр тендеров
//        По КВЭД !  совпадение со списком (хотябы один елемент совпадает) и по ИД елем матч
//        По автору !
//        По участнику !
//        По адресу !
//        По заголовку !
//        По дате начала и конца !
//        По типу (при выборе закрытых, показывает пользователю все закрытые тендеры в которых он участвует)

        Query query = new Query();
        if (tenderFilterOptions.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").all(tenderFilterOptions.getAuthorId()));
        }

        if (tenderFilterOptions.getMembers() != null) {
            query.addCriteria(Criteria.where("members").elemMatch(Criteria.where("id").is(tenderFilterOptions.getMemberId())));
        }

        if (tenderFilterOptions.getAddress() != null) {
            if (tenderFilterOptions.getAddress().getArea() != null) {
                query.addCriteria(Criteria.where("address.area").is(tenderFilterOptions.getAddress().getArea()));
            }
            if (tenderFilterOptions.getAddress().getCity() != null) {
                query.addCriteria(Criteria.where("address.city").is(tenderFilterOptions.getAddress().getCity()));
            }
            if (tenderFilterOptions.getAddress().getGoogleMapKey() != null) {
                query.addCriteria(Criteria.where("address.googleMapKey").is(tenderFilterOptions.getAddress().getGoogleMapKey()));
            }
        }


        if (tenderFilterOptions.getSearchField() != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("title").regex("(?i:.*" + tenderFilterOptions.getSearchField() + ".*)"),
                    Criteria.where("body").regex("(?i:.*" + tenderFilterOptions.getSearchField() + ".*)")));
        }


        if (tenderFilterOptions.getNaceId() != null) {
            query.addCriteria(Criteria.where("naceId").elemMatch(Criteria.where("id").is(tenderFilterOptions.getNaceId())));
        }

        if (tenderFilterOptions.getBegin() != -1) {
            query.addCriteria(Criteria.where("begin").gte(tenderFilterOptions.getBegin()));
        }

        if (tenderFilterOptions.getEnd() != -1) {
            query.addCriteria(Criteria.where("end").lte(tenderFilterOptions.getEnd()));
        }

        if (tenderFilterOptions.getSortDirection() != null && tenderFilterOptions.getSortField() != null) {
            query.with(new Sort(Sort.Direction.fromString(tenderFilterOptions.getSortDirection()), tenderFilterOptions.getSortField()));
        }

        if (tenderFilterOptions.getType() == TenderType.CLOSE) {
            query.addCriteria(Criteria.where("type").is("CLOSE"));
            query.addCriteria(Criteria.where("members").elemMatch(Criteria.where("id").is(currUser.getId())));
        } else {
            Criteria opened = Criteria.where("type").is("OPEN");

            query.addCriteria(opened);

            if(currUser.getContact() != null && currUser.getContact().getNaceId() != null) {
                query.addCriteria(Criteria.where("naceId").in(currUser.getContact().getNaceId()));
            }
        }
        query.skip(tenderFilterOptions.getSkip());
        query.limit(tenderFilterOptions.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, Tender.class),
                mongoTemplate.find(query, Tender.class));
    }
}