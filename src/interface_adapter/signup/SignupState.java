package interface_adapter.signup;

public class SignupState {
    private String username = "";

    private String password = "";
    private String repeatPassword = "";

    public SignupState(SignupState copy) {
        username = copy.username;
        password = copy.password;
        repeatPassword = copy.repeatPassword;
    }

    public SignupState() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    @Override
    public String toString(){
        return "SignupState{" + "username='" + username + '\'' +
                ", password='" + password + '\'' + ", repeatPassword='" + repeatPassword + '\'' + '}';
    }
}
