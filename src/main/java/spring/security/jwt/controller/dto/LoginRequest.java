package spring.security.jwt.controller.dto;

public class LoginRequest {
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
