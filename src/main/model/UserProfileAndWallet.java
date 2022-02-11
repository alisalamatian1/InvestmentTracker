package model;

import java.util.HashMap;

// This class is for holding wallet of each profile and linking the two
public class UserProfileAndWallet {
    private HashMap<UserProfile, StocksInWallet> associatedWallet = new HashMap<>();

    // EFFECT: constructing a UserProfileAndWallet by putting users profile and stocks in a list
    public UserProfileAndWallet(UserProfile profile, StocksInWallet stocks) {
        associatedWallet.put(profile,stocks);
    }

    // MODIFY: this
    // EFFECT: adding the given user profile and stocks to the list of associated wallets
    public void addAssociatedWallets(UserProfile profile, StocksInWallet stocks) {
        associatedWallet.put(profile,stocks);
    }

    public HashMap<UserProfile, StocksInWallet> getAssociatedWallet() {
        return associatedWallet;
    }
}
