package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import view.ViewManager;

public class SignupInputBoundaryTest {


    public SignupInputData createInputData(){
        SignupInputData inputData = new SignupInputData("Mickle", "12345", "12345");
        return inputData;
    }
    public void createUser(){
        UserFactory userFactory = new UserFactory();
        userFactory.create("Mickle", "12345");
    }

    /**
     * public void createStarter(){
     *         UserFactory userFactory = new UserFactory();
     *         userFactory.create("Han", "12345");
     *         SignupInputData input = createInputData();
     *         SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, new SignupViewModel());
     *         SignupUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
     *
     *         SignupInputBoundary userSignupInteractor = new SignupInteractor(
     *                 userDataAccessObject, signupOutputBoundary, userFactory);
     *         userSignupInteractor.execute(input);
     *     }
     */



    /**
     *
     *Test something idk man im just doing my best
    */


    @org.junit.Test
    public void testSomething(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();


        SignupInputData input1 = createInputData();

        UserFactory userFactory = new UserFactory();
        userFactory.create("Mickle", "12345");
        SignupInputData input = createInputData();
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel);
        SignupUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);
        userSignupInteractor.execute(input);

        assert(input.equals(input1));
    }


}
