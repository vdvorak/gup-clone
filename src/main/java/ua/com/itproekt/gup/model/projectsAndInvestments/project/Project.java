package ua.com.itproekt.gup.model.projectsAndInvestments.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Document
public class Project {
    @Id
    private String id;
    private String authorId;
    private Integer views;
    private ProjectStatus status;
    private Long totalScore;
    private Integer totalVoters;
    private Set<ProjectVote> votes;
    private Integer totalComments;
    private Set<Comment> comments;
    private Long createdDate;
    private Long expirationDate;
    private Long lastInvestmentDate;
    @Min(1)
    private Integer amountRequested;
    @Min(0)
    private Integer investedAmount;
    @Size(min = 4, max = 140)
    private String projectName;
    @Size(min = 50, max = 5000)
    private String projectDescription;
    private TypeOfProject typeOfProject;
    private Set<String> categoriesOfIndustry;
    @Size(max = 15)
    private Map<String, String> imagesIds;

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", views=" + views +
                ", totalScore=" + totalScore +
                ", totalVoters=" + totalVoters +
                ", votes=" + votes +
                ", totalComments=" + totalComments +
                ", comments=" + comments +
                ", createdDate=" + createdDate +
                ", expirationDate=" + expirationDate +
                ", lastInvestmentDate=" + lastInvestmentDate +
                ", amountRequested=" + amountRequested +
                ", investedAmount=" + investedAmount +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", typeOfProject=" + typeOfProject +
                ", categoriesOfIndustry=" + categoriesOfIndustry +
                ", imagesIds=" + imagesIds +
                '}';
    }

    public Project setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Project setLastInvestmentDateEqualsToCurrentDate() {
        this.lastInvestmentDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Project updateExpirationDateAt20Days() {
        this.expirationDate = this.lastInvestmentDate + TimeUnit.DAYS.toMillis(1) * 20;
        return this;
    }

    //--------------------------------------------------------------------------------------------


    public ProjectStatus getStatus() {
        return status;
    }

    public Project setStatus(ProjectStatus status) {
        this.status = status;
        return this;
    }

    public Integer getTotalVoters() {
        return totalVoters;
    }

    public Project setTotalVoters(Integer totalVoters) {
        this.totalVoters = totalVoters;
        return this;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public Project setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public Set<ProjectVote> getVotes() {
        return votes;
    }

    public Project setVotes(Set<ProjectVote> votes) {
        this.votes = votes;
        return this;
    }

    public String getId() {
        return id;
    }

    public Project setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Project setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public Project setViews(Integer views) {
        this.views = views;
        return this;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public Project setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Project setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public Project setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Long getLastInvestmentDate() {
        return lastInvestmentDate;
    }

    public Project setLastInvestmentDate(Long lastInvestmentDate) {
        this.lastInvestmentDate = lastInvestmentDate;
        return this;
    }

    public Integer getAmountRequested() {
        return amountRequested;
    }

    public Project setAmountRequested(Integer amountRequested) {
        this.amountRequested = amountRequested;
        return this;
    }

    public Integer getInvestedAmount() {
        return investedAmount;
    }

    public Project setInvestedAmount(Integer investedAmount) {
        this.investedAmount = investedAmount;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Project setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    public TypeOfProject getTypeOfProject() {
        return typeOfProject;
    }

    public Project setTypeOfProject(TypeOfProject typeOfProject) {
        this.typeOfProject = typeOfProject;
        return this;
    }

    public Set<String> getCategoriesOfIndustry() {
        return categoriesOfIndustry;
    }

    public Project setCategoriesOfIndustry(Set<String> categoriesOfIndustry) {
        this.categoriesOfIndustry = categoriesOfIndustry;
        return this;
    }

    public Map<String, String> getImagesIds() {
        return imagesIds;
    }

    public Project setImagesIds(Map<String, String> imagesIds) {
        this.imagesIds = imagesIds;
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Project setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
