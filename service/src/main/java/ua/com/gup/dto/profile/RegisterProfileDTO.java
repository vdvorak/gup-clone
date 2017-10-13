package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.config.annotation.Email;
import ua.com.gup.config.annotation.Password;

public class RegisterProfileDTO {

    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("password")
    @Password
    private String password;

    public RegisterProfileDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
