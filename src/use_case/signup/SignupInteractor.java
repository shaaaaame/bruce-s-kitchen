package use_case.signup;

import entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary{

    final SignupUserDataAccessInterface userDataAccessInterface;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;


    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }
    public void execute(SignupInputData signupInputData){
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username is taken");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())){
            userPresenter.prepareFailView("Passwords are not identical");
        } else {
            User user = UserFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString, false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
