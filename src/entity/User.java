package entity;

public class User {

    private final String username;

    private final String password;
    private final String user_id;

    /**
     * Requires: password is valid.
     * @param username
     * @param password
     */

    User(String username, String password, String user_id) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getID() { return user_id; }

}

