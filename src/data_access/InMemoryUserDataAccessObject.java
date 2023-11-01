package data_access;
import entity.User;
import use_case.signup.SignupUserDataAccessInterface;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    public boolean existsByName(String identifier){
        return users.containsKey(identifier);
    }
    public void save(User user){
        users.put(user.getUsername(), user);
    }
}
