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
    }
}
