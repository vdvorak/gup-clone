package ua.com.gup.server.dto;

public class AuthenticatedDTO {
    private Boolean isAuthenticated;

    public AuthenticatedDTO(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
