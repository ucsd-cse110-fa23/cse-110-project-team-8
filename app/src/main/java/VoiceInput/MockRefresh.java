package VoiceInput;

import PantryPal.*;

public class MockRefresh {

    static public String mockRefreshRecipe(Recipe recipetest, String imagePNG) {
        recipetest.setIngredients(recipetest.getIngredients());
        recipetest.setInstructions("refreshInstructions");
        recipetest.setTitle("refreshTitle");

        return "refreshedImagePNG";
    }
}
