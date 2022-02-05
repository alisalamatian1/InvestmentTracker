package model;

import ui.UserInteraction;


public class UserProfile {
    private String userName;
    private String password;

    // EFFECT: constructing a UserProfile with the given username and password
    public UserProfile(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
