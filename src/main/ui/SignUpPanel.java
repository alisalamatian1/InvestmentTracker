package ui;

import model.StocksInWallet;
import model.UserProfile;
import model.UserProfileAndWallet;
import persistence.JsonWriting;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SignUpPanel extends EnterPanel {
    private JsonWriting jsonWriting;

    public SignUpPanel() {
        super();
    }

    public void saveUserInfo() throws FileNotFoundException {
        String userName = userText.getText();
        String password = passwordText.getText();
        UserProfile userProfile = new UserProfile(userName, password);
        UserProfileAndWallet userProfileAndWallet =
                new UserProfileAndWallet(userProfile, new StocksInWallet());
        jsonWriting = new JsonWriting(JSON_STORAGE + "/" + userName
                + password + ".json");
        jsonWriting.open();
        jsonWriting.write(userProfileAndWallet);
        jsonWriting.close();
    }
}
