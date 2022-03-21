package ui;

import model.UserProfileAndWallet;
import persistence.JsonReading;

import javax.swing.*;
import java.io.IOException;

public abstract class EnterPanel extends JPanel {
    protected JTextField userText;
    protected JPasswordField passwordText;
    protected JsonReading jsonReading;
    protected static final String JSON_STORAGE = "./data/";

    public EnterPanel() {
        initializeUserName();
        initializePassword();
    }


    private void initializeUserName() {
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        this.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        this.add(userText);
    }

    private void initializePassword() {
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        this.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        this.add(passwordText);
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
}
