package ui;

import model.UserProfileAndWallet;
import persistence.JsonReading;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginPanel extends JPanel {
    JTextField userText;
    JPasswordField passwordText;
    StockSuggestionPanel investmentSuggestion;
    private static final String JSON_STORAGE = "./data/";
    private boolean couldLogIn = false;
    private JsonReading jsonReading;

    public LoginPanel() {
        initializeUserName();
        initializePassword();
    }


    private void initializeUserName() {
        // Creating JLabel
        JLabel userLabel = new JLabel("User");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(10,20,80,25);
        this.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        this.add(userText);
    }

    private void initializePassword() {
        // Same process for password label and text field.
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        this.add(passwordLabel);

        /*This is similar to text field but it hides the user
         * entered data and displays dots instead to protect
         * the password like we normally see on login screens.
         */
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        this.add(passwordText);
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

    // REQUIRES: jsonReading not to be null
    public UserProfileAndWallet loadData() {
        UserProfileAndWallet userProfileAndWallet = null;
        try {
            userProfileAndWallet = jsonReading.read();
        } catch (IOException e) {
            // todo: throw an error
            System.out.println("Sorry we cannot find you in the system");
        }
        return userProfileAndWallet;
    }

    // EFFECTS: checks if the userName with the given password exists by checking file path
    public Boolean checkFileExists(String path) {
        return Files.exists(Paths.get(path));
    }
}
