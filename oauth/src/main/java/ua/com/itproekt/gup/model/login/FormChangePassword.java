package ua.com.itproekt.gup.model.login;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FormChangePassword {
    private String password;
    @Pattern.List({
            @Pattern(regexp = "((?=.*\\d)(?=.*[A-Z]).{2,})", message = "Пароль должен содержать хотя бы одну цифру и хотя бы одну заглавную букву")
            , @Pattern(regexp = "([^\\s]+$)", message = "Пароль не должен иметь пробелов")
            , @Pattern(regexp = "(^[^а-яА-ЯёЁ]+$)", message = "Пароль не должен использовать кириллицу")
    })
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
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
