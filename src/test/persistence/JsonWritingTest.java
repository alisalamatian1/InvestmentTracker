package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tests for the JsonWriting class
// citation: the invalid test was similar to the code found on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWritingTest {
    UserProfile userProfile;
    StocksInWallet stocksInWallet;
    UserProfileAndWallet userProfileAndWallet;
    @BeforeEach
    public void setUp() {
        userProfile = new UserProfile("ali", "123");
        stocksInWallet = new StocksInWallet();
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("vfv"), 1, 100));
        userProfileAndWallet = new UserProfileAndWallet(userProfile, stocksInWallet);
        userProfileAndWallet.setProfit(100);
    }

    @Test
    public void writeTest() {
        String validAddress = "./data/testingWriteMethod.json";
        JsonWriting json = new JsonWriting(validAddress);
        JsonReading reading = new JsonReading(validAddress);
        try {
            json.open();
            json.write(userProfileAndWallet);
            json.close();
            UserProfileAndWallet userProfileAndWalletTest = reading.read();
            assertEquals(stocksInWallet.getStocks().get(0).getNumber(),
                    userProfileAndWalletTest.getWallet().getStocks().get(0).getNumber());
            assertEquals(stocksInWallet.getStocks().size(), userProfileAndWalletTest.getWallet().getStocks().size());
            assertEquals(userProfile.getUserName(), userProfileAndWalletTest.getProfile().getUserName());
            assertEquals(userProfile.getPassword(), userProfileAndWalletTest.getProfile().getPassword());
            assertEquals(100, userProfileAndWalletTest.getProfit());
        } catch (IOException e) {
            fail("did not expect an exception");
        }
    }

    @Test
    void writerInvalidFileTest() {
        JsonWriting writer = new JsonWriting("./data/my\0illegal:fileName.json");
        try {
            writer.open();
            fail("FileNotFoundException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }
}
