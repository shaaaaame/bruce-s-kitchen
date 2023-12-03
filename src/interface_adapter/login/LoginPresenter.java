package interface_adapter.login;

import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import javax.swing.*;


public class LoginPresenter implements LoginOutputBoundary {

        private final LoginViewModel loginViewModel;
        private final LoggedInViewModel loggedInViewModel;
        private final HomePageViewModel homePageViewModel;
        private ViewManagerModel viewManagerModel;

        public LoginPresenter(ViewManagerModel viewManagerModel,
                              LoggedInViewModel loggedInViewModel,
                              HomePageViewModel homePageViewModel,
                              LoginViewModel loginViewModel) {
            this.viewManagerModel = viewManagerModel;
            this.loggedInViewModel = loggedInViewModel;
            this.homePageViewModel = homePageViewModel;
            this.loginViewModel = loginViewModel;
        }

        @Override
        public void prepareSuccessView(LoginOutputData response) {
//            LoggedInState loggedInState = loggedInViewModel.getState();
//            loggedInState.setUsername(response.getUsername());
//            this.loggedInViewModel.setState(loggedInState);
//            this.loggedInViewModel.firePropertyChanged();


            this.viewManagerModel.setActiveView("Home");
            this.viewManagerModel.setLoggedIn(true);
            this.viewManagerModel.firePropertyChanged();

            System.out.println("Success! :D");
        }

        @Override
        public void prepareFailView(String error) {
            LoginState loginState = loginViewModel.getState();
            loginState.setLoginError(error);
            loginViewModel.firePropertyChanged();
            System.out.println("Login Failed. " + error);
        }
}