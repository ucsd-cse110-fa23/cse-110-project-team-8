package test;

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
        generate.FILE_PATH = "test1.wav";
    }

    @Test
    public void testRecipe() {

    }

    @Test
    public void testRecipe2() {
    }
}