package ua.com.itproekt.gup.dao.notice;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.notice.Notice;
import ua.com.itproekt.gup.model.notice.NoticeFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;


@Repository
public class NoticeRepositoryImpl implements NoticeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createNotice(Notice notice) {
        mongoTemplate.save(notice);
    }

    @Override
    public Notice findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Notice.class);
    }

    @Override
    public Notice findNoticeAndUpdate(Notice notice) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(notice);
    }

    @Override
    public void update(Notice notice) {
        mongoTemplate.save(notice);
    }

    @Override
    public int deleteNoticeById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        WriteResult result = mongoTemplate.remove(query, Notice.class);
        return result.getN();
    }

    @Override
    public boolean noticeExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, Notice.class);
    }

    @Override
    public EntityPage<Notice> findWihOptions(NoticeFilterOptions noticeFilterOptions) {

        Query query = new Query();
        if (noticeFilterOptions.getUserId() != null) {
                query.addCriteria(Criteria.where("userId").all(noticeFilterOptions.getUserId()));
        }

        if (noticeFilterOptions.getInitiatorId() != null) {
            query.addCriteria(Criteria.where("initiatorId").all(noticeFilterOptions.getInitiatorId()));
        }

        if (noticeFilterOptions.getServiceName() != null) {
            query.addCriteria(Criteria.where("serviceName").all(noticeFilterOptions.getServiceName()));
        }

        if (noticeFilterOptions.getNoticePoint() != null) {
            query.addCriteria(Criteria.where("noticePoint").all(noticeFilterOptions.getNoticePoint()));
        }

        query.skip(noticeFilterOptions.getSkip());
        query.limit(noticeFilterOptions.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, Notice.class),
                mongoTemplate.find(query, Notice.class));
    }
}
