package ua.com.gup.mongo.model.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Form login user")
public class FormLoggedUser {

    @NotNull(message="Имэйл должен быть задан")
    @ApiModelProperty(position = 0)
    private String email;

    @NotNull(message="Описание должно быть задано")
    @ApiModelProperty(position = 10)
    private String password;

    private FormLoggedUser() {
    }

    public FormLoggedUser(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return String.format("FormLoggedUser{email='%s', password='%s'}",
                email, password);
    }
}
