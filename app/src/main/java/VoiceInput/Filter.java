package VoiceInput;

import java.util.ArrayList;

import PantryPal.Recipe;

public class Filter {

    static public ArrayList<Recipe> filterRecipes(ArrayList<Recipe> recipes, String mealtype) {
        ArrayList<Recipe> returnList = new ArrayList<>();
        if (mealtype.equals("all")) {
            return recipes;
        }
        for (Recipe recipe : recipes) {
            if (recipe.getMealType().toLowerCase().contains(mealtype)) {
                returnList.add(recipe);
            }
        }
        return returnList;

    }
}
