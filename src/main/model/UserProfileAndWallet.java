package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

// This class is for holding wallet of each profile and linking the two
public class UserProfileAndWallet {
    private UserProfile profile;
    private StocksInWallet wallet;
    private HashMap<UserProfile, StocksInWallet> associatedWallet = new HashMap<>();

    // EFFECT: constructing a UserProfileAndWallet by putting users profile and stocks in a list
    public UserProfileAndWallet(UserProfile profile, StocksInWallet stocks) {
        this.profile = profile;
        this.wallet = stocks;
        associatedWallet.put(profile,stocks);
    }

    // MODIFY: this
    // EFFECT: adding the given user profile and stocks to the list of associated wallets
    public void addAssociatedWallets(UserProfile profile, StocksInWallet stocks) {
        this.profile = profile;
        this.wallet = stocks;
        associatedWallet.put(profile,stocks);
    }

    public HashMap<UserProfile, StocksInWallet> getAssociatedWallet() {
        return associatedWallet;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("profile", profile.toJson());
        json.put("wallet", wallet.toJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    public JSONArray toArrayOfJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(toJson());
        return jsonArray;
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("userProfileAndWallets", toArrayOfJson());
        return json;
    }
}
