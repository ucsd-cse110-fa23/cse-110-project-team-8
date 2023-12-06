package PantryPalTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PantryPal.*;
import VoiceInput.*;

public class RefreshRecipeTest {
    Recipe recipe0;

    @BeforeEach
    void setUp() throws Exception {
        recipe0 = new Recipe("testTitle0", "testIngredients0", "testTitle0", "testMealType0");
    }

    // BDD1
    @Test
    void RefreshRecipeTitle() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getTitle().equals("refreshTitle"), true);
    }

    // BDD2
    @Test
    void RefreshRecipeIngredients() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getIngredients().equals("testIngredients0"), true);
    }

    // BDD3
    @Test
    void RefreshRecipeInstructions() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getIngredients().equals("testIngredients0"), true);
    }

    // BDD4
    @Test
    void RefreshRecipeImage() {
        assertEquals(MockRefresh.mockRefreshRecipe(recipe0, "imagePNG").equals("refreshedImagePNG"), true);
    }

}