package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWritingTest {

    @Test
    public void writeTest() {
        JsonWriting json = new JsonWriting("./data/testingWriteMethod.json");
        UserProfile userProfile = new UserProfile("ali", "123");
        StocksInWallet stocksInWallet = new StocksInWallet();
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("vfv"), 1, 100));
        UserProfileAndWallet userProfileAndWallet = new UserProfileAndWallet(userProfile, stocksInWallet);

        try {
            json.open();
            json.write(userProfileAndWallet);
            json.close();
        } catch (FileNotFoundException e) {
            fail("Something is totally off");
        }

        JsonWriting json2 = new JsonWriting("./data/testingWriteMethod.json");
        UserProfile userProfile2 = new UserProfile("a", "123");
        StocksInWallet stocksInWallet2 = new StocksInWallet();
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("tesla"), 1, 100));
        UserProfileAndWallet userProfileAndWallet2 = new UserProfileAndWallet(userProfile, stocksInWallet);

        try {
            json2.open();
            json2.write(userProfileAndWallet2);
            json2.close();
        } catch (FileNotFoundException e) {
            fail("Something is totally off");
        }
    }
}
