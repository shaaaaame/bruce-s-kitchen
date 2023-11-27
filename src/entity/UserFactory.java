package entity;

import java.util.UUID;

public class UserFactory {
    /***Requires password to be valid***/
    public static User create(String name, String password) {
        String user_id = UUID.randomUUID().toString();
        return new User(name, password, user_id);
    }
}
