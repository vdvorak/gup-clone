package ua.com.itproekt.gup.service.projectsAndInvestments.project;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.bank_api.services.Pair;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.dao.projectsAndInvestments.project.ProjectRepository;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.*;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.ServiceNames;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ActivityFeedService activityFeedService;

    BankSession bankSession = new BankSession();

    @Override
    public void create(Project project) {
        Project newProject = new Project()
                .setAuthorId(project.getAuthorId())
                .setViews(0)
                .setTotalScore(0L)
                .setTotalVoters(0)
                .setTotalComments(0)
                .setCreatedDateEqualsToCurrentDate()
                .setModerationStatus(ModerationStatus.NO)
                .setStatus(ProjectStatus.ACTIVE)
                .setLastInvestmentDateEqualsToCurrentDate()
                .updateExpirationDateAt20Days()
                .setAmountRequested(project.getAmountRequested())
                .setProjectName(project.getProjectName())
                .setProjectDescription(project.getProjectDescription())
                .setTypeOfProject(project.getTypeOfProject())
                .setCategoriesOfIndustry(project.getCategoriesOfIndustry())
                .setImagesIds(project.getImagesIds())
                .setComments(new HashSet<>())
                .setVotes(new HashSet<>());

        projectRepository.create(newProject);

        project.setId(newProject.getId()); // ***
    }

    @Override
    public Project findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        Map<String, String> imagesIds = findById(id).getImagesIds();
        if (imagesIds != null) {
            storageRepository.delete(ServiceNames.PROJECTS_AND_INVESTMENTS.toString(), (Set<String>)imagesIds.values());
        }
    }

    @Override
    public boolean projectExists(String id) {
        return projectRepository.projectExists(id);
    }

    @Override
    public EntityPage<Project> findProjectsWihOptions(ProjectFilterOptions projectFilterOptions) {
        return projectRepository.findProjectsWihOptions(projectFilterOptions);
    }

    @Override
    public void addComment(String projectId, Comment comment) {
        Comment newComment = new Comment()
                .setComment(comment.getComment())
                .setFromId(comment.getFromId())
                .setToId(comment.getToId())
                .setCreatedDateEqualsToCurrentDate();

        projectRepository.createComment(projectId, newComment);

        comment.setId(newComment.getcId());
    }

    @Override
    public void deleteComment(String projectId, String commentId) {
        projectRepository.deleteComment(projectId, commentId);
    }

    @Override
    public Project findComment(String projectId, String commentId) {
        return projectRepository.findComment(projectId, commentId);
    }

    @Override
    public Project findProjectAndIncViews(String projectId) {
        projectRepository.incViewsAtOne(projectId);
        return projectRepository.findById(projectId);
    }

    @Override
    public boolean commentExists(String projectId, String commentId) {
        return projectRepository.commentExists(projectId, commentId);
    }

    @Override
    public Project edit(Project project) {
        Project newProject = new Project()
                .setId(project.getId())
                .setProjectName(project.getProjectName())
                .setProjectDescription(project.getProjectDescription())
                .setTypeOfProject(project.getTypeOfProject())
                .setCategoriesOfIndustry(project.getCategoriesOfIndustry())
                .setImagesIds(project.getImagesIds());
        return projectRepository.findProjectAndUpdate(newProject);
    }

    @Override
    public void vote(String projectId, String userId, int score) {
        ProjectVote projectVote = new ProjectVote(userId, score);
        if (projectRepository.userHasVoted(projectId, userId)) {
            projectRepository.updateVote(projectId, projectVote);
        } else {
            projectRepository.vote(projectId, projectVote);
        }
    }

    @Override
    public boolean userHasVoted(String projectId, String userId) {
        return projectRepository.userHasVoted(projectId, userId);
    }

    @Override
    public void bringBackMoneyToInvestors() {
        Set<String> expiredProjectsIds = projectRepository.getExpiredProjectsIds();
        expiredProjectsIds.parallelStream().unordered().forEach(projectId -> {
            List<Pair<String, Long>> projectInvestments = null;
            try {
                projectInvestments = bankSession.projectPayback(projectId);
            } catch (ParseException e) {
                throw new RuntimeException(Arrays.toString(e.getStackTrace()));
            }

            sendNotificationsToInvestors(projectInvestments, projectId);
            projectRepository.updateProjectStatus(projectId, ProjectStatus.EXPIRED_AND_RETURNED_MONEY);
        });
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return projectRepository.getMatchedNames(name);
    }

    public void sendNotificationsToInvestors(List<Pair<String, Long>> projectInvestments, String projectId) {
        projectInvestments.parallelStream().unordered().forEach(pair -> {
            String uId = pair.getKey();
            Long moneyAmount = pair.getValue();
            activityFeedService.createEvent(new Event(uId, EventType.PROJECT_BRING_BACK_MONEY,
                    moneyAmount.toString(), projectId));
        });

    }

}
