package entity;

public class User {

    private final String username;

    private final String password;

    /**
     * Requires: password is valid.
     * @param username
     * @param password
     */

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

