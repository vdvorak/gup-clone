package ua.com.itproekt.gup.service.projectsAndInvestments.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.bank_api.entity.InternalTransaction;
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
import java.util.stream.Collectors;

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
                .setModerationStatus(ModerationStatus.COMPLETE)
                .setStatus(ProjectStatus.ACTIVE)
                .setLastInvestmentDateEqualsToCurrentDate()
                .updateExpirationDateAt20Days()
                .setInvestedAmount(0)
                .setAmountRequested(project.getAmountRequested())
                .setTitle(project.getTitle())
                .setDescription(project.getDescription())
                .setType(project.getType())
                .setCategoriesOfIndustry(project.getCategoriesOfIndustry())
                .setImagesIds(project.getImagesIds())
                .setComments(new HashSet<>())
                .setVotes(new HashSet<>());

        projectRepository.create(newProject);

        project.setId(newProject.getId());
    }

    @Override
    public Project findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public void delete(String projectId) {
        Map<String, String> imagesIds = findById(projectId).getImagesIds();
        if (imagesIds != null) {
            storageRepository.delete(ServiceNames.PROJECTS_AND_INVESTMENTS.toString(), imagesIds.keySet());
        }
        projectRepository.delete(projectId);
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
                .setTitle(project.getTitle())
                .setDescription(project.getDescription())
                .setType(project.getType())
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
    public boolean userHasVoted(String projectId, String profileId) {
        return projectRepository.userHasVoted(projectId, profileId);
    }

    @Override
    public boolean userHasCommentedProject(String projectId, String profileId) {
        return projectRepository.userHasCommentedProject(projectId, profileId);
    }

    @Override
    public Set<String> getMatchedNames(String name) {
        return projectRepository.getMatchedNames(name);
    }

    @Override
    public void bringBackMoneyToInvestors() {
        List<Project> activeAndExpiredProjects = projectRepository.getActiveAndExpiredProjects();
        Set<String> notCollectedAmountRequestedProjectIds = getNotCollectedRequestedAmountProjectIds(activeAndExpiredProjects);
        notCollectedAmountRequestedProjectIds.parallelStream().unordered()
                .forEach(projectId-> {
                    List<Pair<String, Long>> projectInvestments = bankSession.projectPayback(projectId);
                    projectRepository.updateProjectStatus(projectId, ProjectStatus.EXPIRED_AND_RETURNED_MONEY);
                    sendProjectBringBackNotificationsToInvestors(projectInvestments, projectId);
                });
    }

    private Set<String> getCollectedRequestedAmountProjectIds(List<Project> activeAndExpiredProjects) {
        return activeAndExpiredProjects.parallelStream().unordered()
                .filter(project -> project.getAmountRequested() >= bankSession.getUserBalance(project.getId()))
                .map(Project::getId)
                .collect(Collectors.toSet());
    }

    private Set<String> getNotCollectedRequestedAmountProjectIds(List<Project> activeAndExpiredProjects) {
        return activeAndExpiredProjects.parallelStream().unordered()
                .filter(project -> project.getAmountRequested() < bankSession.getUserBalance(project.getId()))
                .map(Project::getId)
                .collect(Collectors.toSet());
    }

    public void sendProjectBringBackNotificationsToInvestors(List<Pair<String, Long>> projectInvestments, String projectId) {
        projectInvestments.parallelStream().unordered()
                .forEach(pair -> {
                    String uId = pair.getKey();
                    Long investedMoneyAmount = pair.getValue();
                    activityFeedService.createEvent(new Event(uId, EventType.PROJECT_BRING_BACK_MONEY,
                            projectId, investedMoneyAmount.toString(), null));
                });
    }

    public void sendProjectCollectedMoneyNotificationsToInvestors(String projectId) {
        List<InternalTransaction> depositors = bankSession.getAllRecipientInternalTransactionsJson(projectId);
        depositors.parallelStream().unordered()
                .forEach(depositor -> {
                    String uId = depositor.getSenderId();
                    activityFeedService.createEvent(new Event(uId, EventType.PROJECT_COLLECTED_REQUESTED_AMOUNT,
                            projectId, null));
                });
    }

    @Override
    public void sendNotificationsToInvestorsOfCompletedProjects() {
        List<Project> activeAndExpiredProjects = projectRepository.getActiveAndExpiredProjects();
        Set<String> collectedAmountRequestedProjectIds = getCollectedRequestedAmountProjectIds(activeAndExpiredProjects);
        collectedAmountRequestedProjectIds.parallelStream().unordered()
                .forEach(projectId-> {
                    projectRepository.updateProjectStatus(projectId, ProjectStatus.COLLECTED_MONEY);
                    sendProjectCollectedMoneyNotificationsToInvestors(projectId);
                });
    }
}
