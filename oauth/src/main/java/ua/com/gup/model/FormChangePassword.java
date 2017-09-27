package ua.com.gup.model;

import ua.com.gup.annotation.Password;

public class FormChangePassword {
    private String password;
    @Password
    private String newPassword;

    public FormChangePassword() {
    }

    public FormChangePassword(String password, String newPassword) {
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

    @Override
    public String toString() {
        return "FormLostPassword{" +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}