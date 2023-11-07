package test.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import main.java.VoiceInput.*;
import main.java.PantryPal.*;

public class VoiceInputTest {
    private Generate generate;
    private ChatGPT chatGPT;

    @BeforeEach
    void setUp() {
        generate = new Generate();
        chatGPT = new ChatGPT();
    }

    @Test
    void testRecipe() {
        generate.FILE_PATH = "./audio/test1.wav";
        String output = generate.getUserInput();
        assertEquals("My preferred meal type is dinner, and my ingredients are steak, onion, and butter.", output);
    }

    @Test
    public void testGPT() {
        String testGPTinput = "I want you to give print the numbers '123' exactly like that, do not type anything else.";

        String testGPToutput;

        try {
            testGPToutput = chatGPT.chefGPT(testGPTinput);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            testGPToutput = "Error";
        }

        assertEquals("\n" + "\n" + "123", testGPToutput);
    }
}