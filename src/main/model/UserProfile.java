package model;

import org.json.JSONObject;
import ui.UserInteraction;

// This class represents user's profile; their userName and password
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

    // EFFECTS: making a JSONObject of UserProfile
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", userName);
        json.put("password", password);
        return json;
    }
}
