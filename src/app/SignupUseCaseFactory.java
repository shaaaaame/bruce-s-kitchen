package app;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.*;
import entity.UserFactory;
import interface_adapter.*;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {
    }

    public static SignupView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot open user data file.");
        }
        return null;

        }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {
    SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel);
        UserFactory userFactory = new UserFactory();
        SignupInputBoundary userSignupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}