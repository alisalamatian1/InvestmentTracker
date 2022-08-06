package ui;

import dao.DbConnector;
import model.StocksInWallet;
import model.UserProfile;
import model.UserProfileAndWallet;
import persistence.JsonWriting;
import java.io.FileNotFoundException;

// class representing a signUp page
public class SignUpPanel extends EnterPanel {

    // EFFECTS: constructing a signUp panel
    public SignUpPanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: save user info into the database
    public void saveUserInfo() throws FileNotFoundException {
        String userName = userText.getText();
        String password = passwordText.getText();
        String id= userName + password;
        UserProfile userProfile = new UserProfile(userName, password);
        UserProfileAndWallet userProfileAndWallet =
                new UserProfileAndWallet(userProfile, new StocksInWallet());
        DbConnector.write(userProfileAndWallet, id);
    }
}
