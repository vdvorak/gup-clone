package ua.com.itproekt.gup.model.login;

/**
 * ua.com.itproekt.gup.api.rest.loginAndSignUp >> LoginRestController
 * >> login(<FormLoggedUser>, <HttpServletResponse>)
 */
public class FormLoggedUser {

    private String email;
    private String password;

    public FormLoggedUser(){
    }

    public FormLoggedUser(String email, String password){
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
}
