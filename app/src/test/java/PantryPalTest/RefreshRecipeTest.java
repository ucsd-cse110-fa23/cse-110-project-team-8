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
        recipe0 = new Recipe("testTitle0", "testIngredients0","testTitle0");
    }

    @Test
    void RefreshRecipeTitle() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getTitle().equals("refreshTitle"),true);
    }

    @Test
    void RefreshRecipeIngredients() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getIngredients().equals("testIngredients0"),true);
    }

    @Test
    void RefreshRecipeInstructions() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getIngredients().equals("testIngredients0"),true);
    }

    @Test
    void RefreshRecipeImage() {
        assertEquals(MockRefresh.mockRefreshRecipe(recipe0, "imagePNG").equals("refreshedImagePNG"),true);
    }
    
}
