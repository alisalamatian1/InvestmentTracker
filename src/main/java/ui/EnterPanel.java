package ui;

import dao.DbConnector;
import model.UserProfileAndWallet;
import persistence.JsonReading;

import javax.swing.*;
import java.io.IOException;

// abstract class representing common methods of starting panels
public abstract class EnterPanel extends JPanel {
    protected JTextField userText;
    protected JPasswordField passwordText;
    protected JsonReading jsonReading;
    protected static final String JSON_STORAGE = "./data/";

    // EFFECTS: constructing a starting page with username and password
    public EnterPanel() {
        initializeUserName();
        initializePassword();
    }

    // MODIFIES: this
    // EFFECTS: making a userName text
    private void initializeUserName() {
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        this.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        this.add(userText);
    }

    // MODIFIES: this
    // EFFECTS: making password text
    private void initializePassword() {
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        this.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        this.add(passwordText);
    }

    // REQUIRES: jsonReading not to be null
    // MODIFIES: this
    // EFFECTS: loading data from database
    public UserProfileAndWallet loadData() {
        String userName = userText.getText();
        String password = passwordText.getText();
        jsonReading = new JsonReading(JSON_STORAGE + "/" + userName
               + password + ".json");
        UserProfileAndWallet userProfileAndWallet = null;
//        try {
//            userProfileAndWallet = jsonReading.read();
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this,
//                    "Sorry we cannot find you in the system",
//                    "Not found",
//                    JOptionPane.ERROR_MESSAGE);
//        }
        String userString = DbConnector.select(userName, password);
        try {
            userProfileAndWallet = JsonReading.read(userString);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Sorry we cannot find you in the system",
                    "Not found",
                    JOptionPane.ERROR_MESSAGE);
        }
        return userProfileAndWallet;
    }
}
