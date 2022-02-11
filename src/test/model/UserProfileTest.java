package model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Test for the UserProfile class
public class UserProfileTest {
    @Test
    public void userProfileConstructorTest() {
        UserProfile userProfile = new UserProfile( "Ali", "12345678");
        assertEquals("Ali", userProfile.getUserName());
        assertEquals("12345678", userProfile.getPassword());

        userProfile = new UserProfile( "Mo", "87654321");
        assertEquals("Mo", userProfile.getUserName());
        assertEquals("87654321", userProfile.getPassword());
    }
}
