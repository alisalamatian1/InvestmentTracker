package persistence;

import model.UserProfileAndWallet;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonReadingTest {
    @Test
    public void testParseUserProfileAndWallet() {
        JsonReading jsonReading = new JsonReading("./data/testingWriteMethod.json");
        try {
            UserProfileAndWallet userProfileAndWallet = jsonReading.read();
        } catch (IOException e) {
            System.out.println("sorry we cannot find you in the system.");
        }

        JsonReading jsonReading2 = new JsonReading("./data/testingWriteMethod2.json");
        try {
            UserProfileAndWallet userProfileAndWallet2 = jsonReading2.read();
        } catch (IOException e) {
            System.out.println("sorry we cannot find you in the system.");
        }
    }
}
