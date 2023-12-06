package PantryPalTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import VoiceInput.*;

public class DishImageTests {
    MockDallE mock;

    @BeforeEach
    void setUp() throws Exception {
        mock = new MockDallE();
    }

    // US3 BDD1
    // Caitlin creates a Recipe and is given a generated image
    @Test
    void createRecipeImage() {
        String recipeTitle = "Eggs and Bacon";
        String userInstructions = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle;
        File TestFile = mock.chefDallE(userInstructions, recipeTitle);

        assertEquals(TestFile.exists(), true);
        TestFile.delete();
    }

    // US3 BDD2
    @Test
    void createMultipleRecipeImage() {
        String recipeTitle1 = "Eggs and Bacon";
        String userInstructions1 = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle1;

        String recipeTitle2 = "Eggs and Bacon";
        String userInstructions2 = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle2;

        String recipeTitle3 = "Eggs and Bacon";
        String userInstructions3 = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle3;

        File TestFile1 = mock.chefDallE(userInstructions1, recipeTitle1);
        File TestFile2 = mock.chefDallE(userInstructions2, recipeTitle2);
        File TestFile3 = mock.chefDallE(userInstructions3, recipeTitle3);

        assertEquals(TestFile1.exists() && TestFile2.exists() && TestFile3.exists(), true);
        TestFile1.delete();
        TestFile2.delete();
        TestFile3.delete();
    }

    // US3 BDD3
    @Test
    void checkRecipeName() {
        String recipeTitle = "Eggs and Bacon";
        String userInstructions = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle;
        File TestFile = mock.chefDallE(userInstructions, recipeTitle);

        assertEquals(TestFile.getName().equals("Eggs and Bacon.txt"), true);
        TestFile.delete();

    }

    // US3 BDD4
    // Caitlin access an already saved recipe
    @Test
    void checkSavedRecipeImage() {
        String recipeTitle = "Eggs and Bacon";
        String userInstructions = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle;
        File TestFile = mock.chefDallE(userInstructions, recipeTitle);

        Boolean hasImage;

        if (TestFile.exists()) {
            hasImage = true;
        } else {
            hasImage = false;
        }

        assertEquals(hasImage, true);
        TestFile.delete();
    }

}