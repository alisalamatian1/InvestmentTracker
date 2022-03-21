package ui;

import model.UserProfileAndWallet;
import persistence.JsonReading;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginPanel extends EnterPanel {

    public LoginPanel() {
        super();
    }

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
