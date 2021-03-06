package ui;

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
        UserProfile userProfile = new UserProfile(userName, password);
        UserProfileAndWallet userProfileAndWallet =
                new UserProfileAndWallet(userProfile, new StocksInWallet());
        JsonWriting jsonWriting = new JsonWriting(JSON_STORAGE + "/" + userName
                + password + ".json");
        jsonWriting.open();
        jsonWriting.write(userProfileAndWallet);
        jsonWriting.close();
    }
}
