package persistence;

import model.UserProfileAndWallet;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonReadingTest {
    @Test
    public void testParseUserProfileAndWallet() throws IOException {
        JsonReading jsonReading = new JsonReading("./data/testingWriteMethod.json");
        UserProfileAndWallet userProfileAndWallet = jsonReading.read();

    }
}
