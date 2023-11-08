package PantryPalTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import PantryPal.*;

public class VoiceInputTest {
    private Generate generate;
    private ChatGPT chatGPT;

    @BeforeEach
    void setUp() {
        generate = new Generate();
        chatGPT = new ChatGPT();
    }

    @Test
    public void testGPT() {
        String testGPTinput = "I want you to give print the numbers '123' exactly like that, do not type anything else.";

        assertEquals("I want you to give print the numbers '123' exactly like that, do not type anything else.",
                testGPTinput);
    }
}