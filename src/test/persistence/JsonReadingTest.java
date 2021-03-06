package persistence;

import model.UserProfileAndWallet;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tests for the JsonReading class
// citation: the invalid test was similar to the code found on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReadingTest {
    @Test
    public void testParseUserProfileAndWallet() {
        JsonReading jsonReading = new JsonReading("./data/testingWriteMethod.json");
        try {
            UserProfileAndWallet userProfileAndWallet = jsonReading.read();
            assertEquals(1, userProfileAndWallet.getWallet().getStocks().get(0).getNumber());
            assertEquals(1, userProfileAndWallet.getWallet().getStocks().size());
            assertEquals("123", userProfileAndWallet.getProfile().getPassword());
            assertEquals(100, userProfileAndWallet.getProfit());
        } catch (IOException e) {
            fail("did not expect an error");
        }
    }

    @Test
    public void readerInvalidTest() {
        JsonReading jsonReading = new JsonReading("./data/noSuchFile.json");
        try {
            UserProfileAndWallet userProfileAndWallet = jsonReading.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testMatchFinder() throws IOException {
        JsonReading jsonReading = new JsonReading("./data/testingWriteMethod.json");
        jsonReading.read();
        assertEquals("", jsonReading.matchFinder("invalidRegexForChecking"));
    }
}
