package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class BanInfoDto { 
    @JsonProperty("public")
    @NotNull
    private String publicExplanation;
    @JsonProperty("private")
    @NotNull
    private String privateExplanation;
  
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
