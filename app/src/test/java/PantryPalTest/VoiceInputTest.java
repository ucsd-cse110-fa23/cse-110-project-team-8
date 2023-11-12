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
import VoiceInput.ChatGPT;

public class VoiceInputTest {
    private Generate generate;
    private ChatGPT chatGPT;

    @BeforeEach
    void setUp() {
        generate = new Generate();
        chatGPT = new ChatGPT();
    }

    // @Test
    // void testRecipe() {
    // generate.FILE_PATH = "test1.wav";
    // String output = generate.getUserInput();
    // assertEquals("My preferred meal type is dinner, and my ingredients are
    // steak,onion, and butter.", output);
    // }

    @Test
    void testGPT() {
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


    // Test incrementing number of recipes
    @Test
    void testNumRecipe1() {
        RecipeList recipeList = new RecipeList();
        recipeList.incNum();
        assertEquals(1, recipeList.getNum());
    }

    // Test deleting number of recipes
    @Test
    void testNumRecipe2() {
        RecipeList recipeList = new RecipeList();
        recipeList.incNum();
        recipeList.decNum();
        assertEquals(0, recipeList.getNum());
    }

    // Test when the number of recipe in the list is 0
    @Test
    void testNumRecipe3() {
        RecipeList recipeList = new RecipeList();
        String output = "";
        try {
            recipeList.decNum();
        } catch (Exception e) {
            e.printStackTrace();
            output = "No Recipe in the list.";
        }
        assertEquals("No Recipe in the list.", output);
    }
}