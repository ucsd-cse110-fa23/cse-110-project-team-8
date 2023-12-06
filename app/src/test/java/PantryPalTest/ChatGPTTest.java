package PantryPalTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import PantryPal.*;
import VoiceInput.*;

public class ChatGPTTest {
    MockChatGPT mockGPT;

    @BeforeEach
    void setUp() throws Exception {
        mockGPT = new MockChatGPT();
    }

    // bdd 1
    @Test
    void createdMaintainsMealtype() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("dinner"), true);
    }

    // bdd 2
    @Test
    void createdMaintainsIngredients() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("salt, pepper, eggs"), true);
    }

    // bdd 3
    @Test
    void createdTitle() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("testtitle"), true);
    }

    // bdd 4
    @Test
    void createdInstructions() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("testinstrutions"), true);
    }
}