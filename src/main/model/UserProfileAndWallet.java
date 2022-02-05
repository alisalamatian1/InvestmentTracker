package model;

import java.util.HashMap;

public class UserProfileAndWallet {
    private HashMap<UserProfile, StocksInWallet> associatedWallet = new HashMap<>();

    // EFFECT: constructing a UserProfileAndWallet by putting users profile and stocks in a list
    public UserProfileAndWallet(UserProfile profile, StocksInWallet stocks) {
        associatedWallet.put(profile,stocks);
    }

    public HashMap<UserProfile, StocksInWallet> getAssociatedWallet() {
        return associatedWallet;
    }

    public void addAssociatedWallets(UserProfile profile, StocksInWallet stocks) {
        associatedWallet.put(profile,stocks);
    }
}
