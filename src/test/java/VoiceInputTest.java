package test.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import VoiceInput.*;
import PantryPal.*;

public class VoiceInputTest {
    private Generate generate;

    @BeforeEach
    void setUp() {
        generate = new Generate();
    }

    @Test
    void testRecipe() {
        generate.FILE_PATH = "./audio/test1.wav";
        String output = generate.getUserInput();
        assertEquals("My preferred meal type is dinner, and my ingredients are steak, onion, and butter.", output);
    }

    @Test
    void testRecipe2() {
    }
}