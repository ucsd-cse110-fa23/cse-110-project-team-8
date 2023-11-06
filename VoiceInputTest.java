package test.java;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
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
    private TestRecipe testRecipe;
    private ChatGPT chapGPT;

    @BeforeEach
    void setUp() {
        testRecipe = new TestRecipe();
        chapGPT = new ChatGPT();
        testRecipe.setPath("audio/test1.wav");
    }

    @Test
    public void testRecipe() {
        String testinput = testRecipe.getUserInput();
        assertEquals("My preferred meal type is dinner, and my ingredients are steak, onion, and butter.", testinput);
    }

    // @Test
    // public void testGPT() {
    // String testGPTinput = "I want you to give print the numbers '123' exactly
    // like that, do not type anything else.";

    // String testGPToutput;

    // try {
    // testGPToutput = chatGPT.chefGPT(testGPTinput);
    // } catch (IOException | InterruptedException | URISyntaxException e) {
    // e.printStackTrace();
    // testGPToutput = "Error";
    // }

    // assertEquals("\n" + "\n" + "123", testGPToutput);
    // }
}