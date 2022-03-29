package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

// This class is for holding wallet of each profile and linking the two
public class UserProfileAndWallet {
    private UserProfile profile;
    private StocksInWallet wallet;
    private double profit;

    // EFFECT: constructing a UserProfileAndWallet by putting users profile and stocks in a list
    public UserProfileAndWallet(UserProfile profile, StocksInWallet stocks) {
        this.profile = profile;
        this.wallet = stocks;
    }

    // MODIFY: this
    // EFFECT: adding the given user profile and stocks to the list of associated wallets
    public void setAssociatedWallets(UserProfile profile, StocksInWallet stocks) {
        this.profile = profile;
        this.wallet = stocks;
    }

    public StocksInWallet getWallet() {
        return wallet;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }

    // EFFECTS: making a JSONObject of UserProfileAndWallets
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("profile", profile.toJson());
        json.put("profit", profit);
        json.put("wallet", wallet.toJson());
        return json;
    }

    // EFFECTS: adding the result of toJson file with the userProfileAndWallets key
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("userProfileAndWallets", toJson());
        return json;
    }
}
