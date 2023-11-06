package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import VoiceInput.*;
import PantryPal.*;

public class VoiceInputTest {
    //private Recipe recipe;
    private ChatGPT chatGPT;

    @BeforeEach
    void setUp() {
        //recipe = new Recipe();
        chatGPT = new ChatGPT();
        //recipe.FILE_PATH = "test1.wav";

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