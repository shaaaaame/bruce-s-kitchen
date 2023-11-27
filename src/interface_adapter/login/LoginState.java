package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String password = "";
    private String loginError = null;

    public LoginState(LoginState login) {
        username = login.username;
        password = login.password;
        loginError = login.loginError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginError() {
        return this.loginError = loginError;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

}
