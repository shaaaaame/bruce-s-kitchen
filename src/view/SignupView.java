package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Sign Up";
    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);
    private final JTextField repeatPasswordInputField = new JTextField(15);
    private final SignupController signupController;
    private final JButton signUp;
    private final JButton cancel;

    public SignupView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameInfo = new JTextField(SignupViewModel.USERNAME_LABEL);
        JTextField passwordInfo = new JTextField(SignupViewModel.PASSWORD_LABEL);
        JTextField repeatPasswordInfo = new JTextField(SignupViewModel.REPEAT_PASSWORD_LABEL);


        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
