package ua.com.itproekt.gup.dao.projectsAndInvestments.project;

import ua.com.itproekt.gup.model.projectsAndInvestments.project.*;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.Set;

public interface ProjectRepository {
    void create(Project project);

    Project findById(String projectId);

    Project findProjectAndUpdate (Project project);

    void delete(String projectId);

    boolean projectExists(String projectId);

    EntityPage<Project> findProjectsWihOptions (ProjectFilterOptions projectFilterOptions);

    void createComment(String projectId, Comment comment);

    void deleteComment(String projectId, String commentId);

    Project findComment(String projectId, String commentId);

    boolean commentExists(String projectId, String commentId);

    void incViewsAtOne(String projectId);

    boolean userHasVoted(String projectId, String userId);

    void vote(String projectId, ProjectVote projectVote);

    void updateVote(String projectId, ProjectVote projectVote);

    void updateProjectStatus(String projectId, ProjectStatus status);

    Set<String> getExpiredProjectsIds();

    Set<String> getCompletedAmountRequestedProjectsIds();

    Set<String> getMatchedNames(String name);

    boolean userHasCommentedProject(String projectId, String profileId);
}
