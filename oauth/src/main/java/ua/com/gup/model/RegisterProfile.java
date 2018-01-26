package ua.com.gup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.annotation.Email;
import ua.com.gup.common.annotation.Password;

import javax.validation.constraints.NotNull;

public class RegisterProfile {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("password")
    @Password
    private String password;

    public RegisterProfile() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
