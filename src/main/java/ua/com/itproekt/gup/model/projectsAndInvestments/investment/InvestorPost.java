package ua.com.itproekt.gup.model.projectsAndInvestments.investment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Document(collection = "investorPosts")
public class InvestorPost {
    @Id
    private String id;
    private String uId;
    @Size(min = 50, max = 5000)
    private String description;
    @Size(min = 1)
    private Set<String> categoriesOfIndustry;
    private Integer totalThoseInNeed;
    private Set<ApplicationForInvestment> thoseInNeed;
    private Long createdDate;

    public Integer getTotalThoseInNeed() {
        return totalThoseInNeed;
    }

    public InvestorPost setTotalThoseInNeed(Integer totalThoseInNeed) {
        this.totalThoseInNeed = totalThoseInNeed;
        return this;
    }

    public Set<ApplicationForInvestment> getThoseInNeed() {
        return thoseInNeed;
    }

    public InvestorPost setThoseInNeed(Set<ApplicationForInvestment> thoseInNeed) {
        this.thoseInNeed = thoseInNeed;
        return this;
    }

    public String getId() {
        return id;
    }

    public InvestorPost setId(String id) {
        this.id = id;
        return this;
    }

    public String getuId() {
        return uId;
    }

    public InvestorPost setuId(String uId) {
        this.uId = uId;
        return this;
    }

    public InvestorPost setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public InvestorPost setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Set<String> getCategoriesOfIndustry() {
        return categoriesOfIndustry;
    }

    public InvestorPost setCategoriesOfIndustry(Set<String> categoriesOfIndustry) {
        this.categoriesOfIndustry = categoriesOfIndustry;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InvestorPost setDescription(String description) {
        this.description = description;
        return this;
    }
}
