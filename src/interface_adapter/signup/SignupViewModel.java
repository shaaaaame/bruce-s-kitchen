package interface_adapter.signup;

import interface_adapter.ViewModel;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up Page";
    public static final String USERNAME_LABEL = "Choose your username";
    public static final String PASSWORD_LABEL = "Choose your password";
    public static final String REPEAT_PASSWORD_LABEL = "Please re-enter your password";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private SignupState state = new SignupState();

    public void setState(SignupState state) { this.state = state; }
    public SignupState getState() { return state; }
    public SignupViewModel() {
        super("Sign Up");
    }

}
