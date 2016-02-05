package ua.com.itproekt.gup.dao.projectsAndInvestments.project;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.*;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Project.class)) {
            mongoTemplate.createCollection(Project.class);
        }
    }

    @Override
    public void create(Project project) {
        mongoTemplate.insert(project);
    }

    @Override
    public Project findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Project.class);
    }

    @Override
    public Project findProjectAndUpdate(Project project) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(project);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Project.class);
    }

    @Override
    public boolean projectExists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Project.class);
    }

    @Override
    public EntityPage<Project> findProjectsWihOptions(ProjectFilterOptions projectFO) {
        Query query = new Query();

        if (projectFO.getSearchField() != null) {
            String searchFieldRegex = "(?i:.*" + projectFO.getSearchField() + ".*)";
            query.addCriteria(Criteria.where("projectName").regex(searchFieldRegex));
        }

        if (projectFO.getStatus() != null) {
            query.addCriteria(Criteria.where("status").is(projectFO.getStatus()));
        }

        if (projectFO.getModerationStatus() != null) {
            query.addCriteria(Criteria.where("moderationStatus").is(projectFO.getModerationStatus()));
        }

        if (projectFO.getAuthorId() != null) {
            query.addCriteria(Criteria.where("authorId").is(projectFO.getAuthorId()));
        }

        if (projectFO.getTypeOfProject() != null) {
            query.addCriteria(Criteria.where("typeOfProject").is(projectFO.getTypeOfProject()));
        }

        if (projectFO.getCategoriesOfIndustry() != null) {
            query.addCriteria(Criteria.where("categoriesOfIndustry").all(projectFO.getCategoriesOfIndustry()));
        }

        if (!projectFO.isIncludeComments()) {
            query.fields().exclude("comments");
        }

        if (projectFO.getCreatedDateSortDirection() != null) {
            query.with(new Sort(projectFO.getCreatedDateSortDirection(), "createdDate"));
        }

        if (projectFO.getViewsSortDirection() != null) {
            query.with(new Sort(projectFO.getViewsSortDirection(), "views"));
        }

        query.skip(projectFO.getSkip());
        query.limit(projectFO.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, Project.class),
                                mongoTemplate.find(query, Project.class));
    }

    @Override
    public void createComment(String projectId, Comment comment) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(projectId)),
                new Update().push("comments", comment).inc("totalComments", 1),
                Project.class);
    }

    @Override
    public void deleteComment(String projectId, String commentId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(projectId)),
                new Update().pull("comments", Query.query(Criteria.where("cId").is(commentId))).inc("totalComments", -1),
                Project.class);
    }

    @Override
    public Project findComment(String projectId, String commentId) {
        Query query = new Query()
            .addCriteria(Criteria.where("id").is(projectId))
            .addCriteria(Criteria.where("comments.cId").is(commentId));
        query.fields().slice("comments", 1);

        return mongoTemplate.findOne(query, Project.class);
    }

    @Override
    public boolean commentExists(String projectId, String commentId) {
        Query query = new Query()
            .addCriteria(Criteria.where("id").is(projectId))
            .addCriteria(Criteria.where("comments.cId").is(commentId));
        return mongoTemplate.exists(query, Project.class);
    }

    @Override
    public void incViewsAtOne(String projectId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(projectId)),
                new Update().inc("views", 1),
                Project.class);
    }

    @Override
    public boolean userHasVoted(String projectId, String userId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(projectId))
                .addCriteria(Criteria.where("votes.uId").is(userId));
        return mongoTemplate.exists(query, Project.class);
    }

    @Override
    public void vote(String projectId, ProjectVote projectVote) {
        Update update = new Update()
                .push("votes", projectVote)
                .inc("totalVoters", 1)
                .inc("totalScore", projectVote.getScore());

        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(projectId)),
                update,
                Project.class);
    }

    @Override
    public void updateVote(String projectId, ProjectVote projectVote) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(projectId))
                .addCriteria(Criteria.where("votes.uId").is(projectVote.getuId()));
        query.fields().slice("votes", 1);

        Project projectWithOldVote = mongoTemplate.findOne(query, Project.class);
        int oldUserScore = projectWithOldVote.getVotes().iterator().next().getScore();

        Update update = new Update()
                .set("votes.$.score", projectVote.getScore())
                .inc("totalScore", projectVote.getScore() - oldUserScore);

        mongoTemplate.updateFirst(
                query,
                update,
                Project.class);
    }

    @Override
    public Set<String> getExpiredProjectsIds() {
        Long currentDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        DBObject queryObj = new BasicDBObject();
        queryObj.put("expirationDate", new BasicDBObject("$lte", currentDate));
        queryObj.put( "$where", "this.investedAmount < this.amountRequested");
        List<Project> expiredProjects = mongoTemplate.find(new BasicQuery(queryObj), Project.class);
        return expiredProjects.stream().map(Project::getId).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        String searchFieldRegex = "(?i:.*" + name + ".*)";

        Query query = new Query()
                .addCriteria(Criteria.where("projectName").regex(searchFieldRegex))
                .addCriteria(Criteria.where("moderationStatus").is(ModerationStatus.COMPLETE));

        query.fields().include("projectName");
        query.skip(0);
        query.limit(10);
        return mongoTemplate.find(query, Project.class).stream().map(Project::getProjectName).collect(Collectors.toSet());
    }

    @Override
    public void updateProjectStatus(String projectId, ProjectStatus status) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(projectId)),
                new Update().set("status", status),
                Project.class);
    }
}
