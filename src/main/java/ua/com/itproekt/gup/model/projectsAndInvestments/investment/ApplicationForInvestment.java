package ua.com.itproekt.gup.model.projectsAndInvestments.investment;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ApplicationForInvestment {
    private String uId;
    private String projectId;
    private Long createdDate;

    public String getuId() {
        return uId;
    }

    public ApplicationForInvestment setuId(String uId) {
        this.uId = uId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public ApplicationForInvestment setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public ApplicationForInvestment setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ApplicationForInvestment setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }
}
