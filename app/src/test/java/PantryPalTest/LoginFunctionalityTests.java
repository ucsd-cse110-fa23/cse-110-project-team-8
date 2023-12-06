package PantryPalTest;

import org.junit.jupiter.api.Test;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import PantryPal.*;

public class LoginFunctionalityTests {
    File Testfile;

    @BeforeEach
    void setUp() throws IOException {
        Testfile = new File("TestFile.txt");
    }

    @AfterEach
    void closeDown() throws IOException {
        Testfile.delete();
    }

    // US2 BDD1
    // Test login with autologin disabled
    @Test
    void loginWithoutAutoLogin() {
        assertEquals(AutoLogin.autoLoginEnabled("TestFile.txt"), false);
    }

    // US2 BDD2
    // Test login with autologin enabled
    @Test
    void loginWithAutoLogin() {
        AutoLogin.createFileTest("test1", "1234");
        assertEquals(AutoLogin.autoLoginEnabled("TestFile.txt"), true);
    }

    // US2 BDD3
    // Test logout
    @Test
    void logout() {
        AutoLogin.createFileTest("test1", "1234");
        AutoLogin.clearFileTest();
        assertEquals(AutoLogin.autoLoginEnabled("TestFile.txt"), false);
    }

    // US2 BDD4
    // Test create account with autologin
    @Test
    void createAccountWithAutoLogin() {
        AutoLogin.createFileTest("test1", "1234");
        assertEquals(AutoLogin.autoLoginEnabled("TestFile.txt"), true);
    }
}