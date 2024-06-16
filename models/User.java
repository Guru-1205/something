package models;

import enums.UserType;
import java.io.Serializable;

// Class to represent a user
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private UserType type;

    public User(String username, UserType type) {
        this.username = username;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Type: " + type;
    }
}
