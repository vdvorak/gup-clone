package ua.com.gup.mongo.model.login;

import javax.validation.constraints.NotNull;
import ua.com.gup.config.annotation.Password;

public class FormChangePassword {

    @NotNull
    private String token;
    @Password
    private String password;
    @Password
    private String newPassword;

    public FormChangePassword() {
    }

    public FormChangePassword(String token, String password, String newPassword) {
        this.token = token;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FormLostPassword{"
                + ", password='" + password + '\''
                + ", newPassword='" + newPassword + '\''
                + '}';
    }
}
