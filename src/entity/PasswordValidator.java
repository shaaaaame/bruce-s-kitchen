package entity;

public class PasswordValidator {
    public boolean passwordIsValid(String password){
        return password != null &&
                password.length() > 8 &&
                password.matches(".*[~!@#$%^&*,.+=?:;].*") &&
                password.matches(".*[1234567890].*") &&
                password.matches(".*[abcdefghijklmnopqrstuvwxyz].*") &&
                password.matches(".*[ABCDEFGHIJKLMNOPQRSTUVWXYZ].*");

    }
}
