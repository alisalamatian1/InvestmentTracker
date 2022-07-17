package ui;

import persistence.JsonReading;
import java.nio.file.Files;
import java.nio.file.Paths;

// class representing the login panel
public class LoginPanel extends EnterPanel {

    // EFFECTS: constructing loginPanel
    public LoginPanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: Checks if the profile exists or not
    public boolean checkForProfile() {
        String userName = userText.getText();
        String password = passwordText.getText();
        if (checkFileExists(JSON_STORAGE + "/" + userName + password + ".json")) {
            jsonReading = new JsonReading(JSON_STORAGE + "/" + userName + password + ".json");
            return true;
        }
        return false;
    }

    // EFFECTS: checks if the userName with the given password exists by checking file path
    public Boolean checkFileExists(String path) {
        return Files.exists(Paths.get(path));
    }
}
