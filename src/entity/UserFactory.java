package entity;

import java.util.UUID;

public class UserFactory {
    /***Requires password to be valid***/
    public static User create(String name, String password) {
        UUID user_id = UUID.randomUUID();
        return new User(name, password, user_id);
    }
}
