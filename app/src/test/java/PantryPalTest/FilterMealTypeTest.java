package PantryPalTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import PantryPal.Recipe;
import PantryPal.RecipeList;
import VoiceInput.Filter;

public class FilterMealTypeTest {
    private RecipeList recipeList;

    @BeforeEach
    void setUp() throws Exception {
        recipeList = new RecipeList();
        recipeList.add(new Recipe("testTitle1", "testIngredients1",
                "testInstructions1", "breakfast"));
        recipeList.add(new Recipe("testTitle2", "testIngredients2",
                "testInstructions2", "lunch"));
        recipeList.add(new Recipe("testTitle3", "testIngredients3",
                "testInstructions3", "lunch"));
        recipeList.add(new Recipe("testTitle4", "testIngredients4",
                "testInstructions4", "dinner"));

    }

    // US9 BDD1
    @Test
    void testBreakfastFilter() {
        ArrayList<Recipe> testList = new ArrayList<>();
        testList.add(new Recipe("testTitle1", "testIngredients1",
                "testInstructions1", "breakfast"));
        assertEquals(Filter.filterRecipes(recipeList.getList(),
                "breakfast").get(0).getTitle(),
                testList.get(0).getTitle());

    }

    // US9 BDD2
    @Test
    void testLunchFilter() {
        ArrayList<Recipe> testList2 = new ArrayList<>();

        testList2.add(new Recipe("testTitle3", "testIngredients3",
                "testInstructions3", "lunch"));
        testList2.add(new Recipe("testTitle2", "testIngredients2",
                "testInstructions2", "lunch"));
        assertEquals(Filter.filterRecipes(recipeList.getList(),
                "lunch").get(0).getTitle(),
                testList2.get(0).getTitle());
    }

    // US9 BDD3
    @Test
    void testDinnerFilter() {
        ArrayList<Recipe> testList3 = new ArrayList<>();
        testList3.add(new Recipe("testTitle4", "testIngredients4",
                "testInstructions4", "dinner"));
        assertEquals(Filter.filterRecipes(recipeList.getList(),
                "dinner").get(0).getTitle(),
                testList3.get(0).getTitle());
    }

    // US9 BDD4
    @Test
    void testFilterAll() {
        ArrayList<Recipe> testList4 = new ArrayList<>();

        testList4.add(new Recipe("testTitle4", "testIngredients4",
                "testInstructions4", "dinner"));
        testList4.add(new Recipe("testTitle3", "testIngredients3",
                "testInstructions3", "lunch"));
        testList4.add(new Recipe("testTitle2", "testIngredients2",
                "testInstructions2", "lunch"));
        testList4.add(new Recipe("testTitle1", "testIngredients1",
                "testInstructions1", "breakfast"));
        assertEquals(Filter.filterRecipes(recipeList.getList(),
                "all").get(0).getTitle(), testList4.get(0).getTitle());
    }

}
