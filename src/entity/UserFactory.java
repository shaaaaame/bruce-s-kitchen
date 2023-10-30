package entity;

public class UserFactory {
    /***Requires password to be valid***/
    public User create(String name, String password) {
        return new User(name, password);
    }
}
