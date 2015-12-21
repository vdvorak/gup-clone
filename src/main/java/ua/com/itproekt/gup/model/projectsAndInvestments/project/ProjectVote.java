package ua.com.itproekt.gup.model.projectsAndInvestments.project;


public class ProjectVote {
    private String uId;
    private Integer score;

    public ProjectVote() {
    }

    public ProjectVote(String uId, Integer score) {
        this.uId = uId;
        this.score = score;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
