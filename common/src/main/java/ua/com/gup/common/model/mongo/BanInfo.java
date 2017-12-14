package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class BanInfo {

    @JsonProperty("userId")
    @NotNull
    private String userId;
    @JsonProperty("date")
    @NotNull
    private Date date;    
    @JsonProperty("public")
    @NotNull
    private String publicExplanation;
    @JsonProperty("private")
    @NotNull
    private String privateExplanation;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPublicExplanation() {
        return publicExplanation;
    }

    public void setPublicExplanation(String publicExplanation) {
        this.publicExplanation = publicExplanation;
    }

    public String getPrivateExplanation() {
        return privateExplanation;
    }

    public void setPrivateExplanation(String privateExplanation) {
        this.privateExplanation = privateExplanation;
    }
}
