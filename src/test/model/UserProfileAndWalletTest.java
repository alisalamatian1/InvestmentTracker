package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test for the UserProfileAndWallet class
public class UserProfileAndWalletTest {
    UserProfileAndWallet userProfileAndWallet;
    UserProfile userProfile;
    StocksInWallet stocksInWallet;

    @BeforeEach
    public void setUp() {
        userProfile = new UserProfile("Ali", "12345678");
        stocksInWallet = new StocksInWallet();
        userProfileAndWallet = new UserProfileAndWallet(userProfile, stocksInWallet);
    }

    @Test
    public void constructorTest() {
        assertEquals(stocksInWallet, userProfileAndWallet.getWallet());
        assertEquals(userProfile, userProfileAndWallet.getProfile());
    }

    @Test
    public void addAssociatedUserWalletTest() {
        userProfile = new UserProfile("Mo", "87654321");
        stocksInWallet = new StocksInWallet();
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock ("VFV"), 2, 100));
        userProfileAndWallet.setWallet(stocksInWallet);
        assertEquals(stocksInWallet, userProfileAndWallet.getWallet());
        assertEquals(2, userProfileAndWallet.getWallet().getStocks().get(0).getNumber());

        userProfile = new UserProfile("Kousha", "87654311");
        stocksInWallet = new StocksInWallet();
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock ("TSLA"), 3, 100));
        userProfileAndWallet.setWallet(stocksInWallet);
        assertEquals(stocksInWallet, userProfileAndWallet.getWallet());
        assertEquals(3, userProfileAndWallet.getWallet().getStocks().get(0).getNumber());

    }
}
