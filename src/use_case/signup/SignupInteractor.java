package use_case.signup;

import entity.PasswordValidator;
import entity.UserFactory;
import entity.User;

public class SignupInteractor implements SignupInputBoundary{

    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username is taken");
        } else if (!(signupInputData.getPassword().equals(signupInputData.getRepeatPassword()))) {
            userPresenter.prepareFailView("Passwords are not identical");
        } else if (!PasswordValidator.passwordIsValid(signupInputData.getPassword())) {
            userPresenter.prepareFailView("Password must be at least 8 characters long, and include 1 of each:" +
                    "upper case, lower case, number, and special character.");
        } else {
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(),false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
