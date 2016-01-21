package ua.com.itproekt.gup.service.projectsAndInvestments.project;

import ua.com.itproekt.gup.model.projectsAndInvestments.project.Comment;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ProjectFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;


public interface ProjectService {
    //Projects
    void create(Project project);

    Project findById(String id);

    int delete(String id);

    boolean projectExists(String id);

    Project edit(Project project);

    Project findProjectAndIncViews(String projectId);

    EntityPage<Project> findProjectsWihOptions (ProjectFilterOptions projectFilterOptions);

    // Comments
    void addComment(String projectId, Comment comment);

    int deleteComment(String projectId, String commentId);

    Project findComment(String projectId, String commentId);

    boolean commentExists(String projectId, String commentId);

    void vote(String projectId, String userId, int score);

    boolean userHasVoted(String projectId, String userId);

    void bringBackMoneyToInvestors();

    List<String> getMatchedNames(String name);
}
