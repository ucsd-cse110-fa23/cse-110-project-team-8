package PantryPalTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

import PantryPal.*;
import VoiceInput.*;
import java.util.*;

public class MileStoneTest {

    MockChatGPT mockGPT;
    MockWhisper mockWhisper;
    MockVoiceRecorder mockVoiceRecorder;
    RecipeList lst;
    MockDallE mockDallE;
    Recipe recipe0;
    RecipeList recipeList;

    @BeforeEach
    void setUp() throws Exception {
        mockGPT = new MockChatGPT();
        mockWhisper = new MockWhisper();
        mockVoiceRecorder = new MockVoiceRecorder();
        mockDallE = new MockDallE();

        this.lst = new RecipeList();
        Recipe r1 = new Recipe("a", "instruction", "ingredients", "dinner");
        r1.setCreationTime();
        Recipe r2 = new Recipe("b", "instruction", "ingredients", "dinner");
        r2.setCreationTime();
        Recipe r3 = new Recipe("c", "instruction", "ingredients", "dinner");
        r3.setCreationTime();
        lst.add(r1);
        lst.add(r2);
        lst.add(r3);

        recipe0 = new Recipe("testTitle0", "testIngredients0", "testTitle0", "testMealType0");

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

    //
    @Test
    void createdMaintainsMealtype() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("dinner"), true);
    }

    @Test
    void createdMaintainsIngredients() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("salt, pepper, eggs"), true);
    }

    @Test
    void createdTitle() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("testtitle"), true);
    }

    @Test
    void createdInstructions() {
        String userMealType = "Dinner";
        String userIngredients = "salt, pepper, eggs";

        String newRecipe = mockGPT.chefGPT(userMealType, userIngredients);

        assertEquals(newRecipe.toLowerCase().contains("testinstrutions"), true);
    }

    @Test
    void createRecipeImage() {
        String recipeTitle = "Eggs and Bacon";
        String userInstructions = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle;
        File TestFile = mockDallE.chefDallE(userInstructions, recipeTitle);

        assertEquals(TestFile.exists(), true);
        TestFile.delete();
    }

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

        File TestFile1 = mockDallE.chefDallE(userInstructions1, recipeTitle1);
        File TestFile2 = mockDallE.chefDallE(userInstructions2, recipeTitle2);
        File TestFile3 = mockDallE.chefDallE(userInstructions3, recipeTitle3);

        assertEquals(TestFile1.exists() && TestFile2.exists() && TestFile3.exists(), true);
        TestFile1.delete();
        TestFile2.delete();
        TestFile3.delete();
    }

    @Test
    void checkRecipeName() {
        String recipeTitle = "Eggs and Bacon";
        String userInstructions = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle;
        File TestFile = mockDallE.chefDallE(userInstructions, recipeTitle);

        assertEquals(TestFile.getName().equals("Eggs and Bacon.txt"), true);
        TestFile.delete();

    }

    // US3 BDD2
    // Caitlin access an already saved recipe
    @Test
    void checkSavedRecipeImage() {
        String recipeTitle = "Eggs and Bacon";
        String userInstructions = "Make a recipe image of mealtype " + "mealType" + "with the recipe title "
                + recipeTitle;
        File TestFile = mockDallE.chefDallE(userInstructions, recipeTitle);

        Boolean hasImage;

        if (TestFile.exists()) {
            hasImage = true;
        } else {
            hasImage = false;
        }

        assertEquals(hasImage, true);
        TestFile.delete();
    }

    @Test
    void testBreakfastFilter() {
        ArrayList<Recipe> testList = new ArrayList<>();
        testList.add(new Recipe("testTitle1", "testIngredients1",
                "testInstructions1", "breakfast"));
        assertEquals(Filter.filterRecipes(recipeList.getList(),
                "breakfast").get(0).getTitle(),
                testList.get(0).getTitle());

    }

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

    @Test
    void testDinnerFilter() {
        ArrayList<Recipe> testList3 = new ArrayList<>();
        testList3.add(new Recipe("testTitle4", "testIngredients4",
                "testInstructions4", "dinner"));
        assertEquals(Filter.filterRecipes(recipeList.getList(),
                "dinner").get(0).getTitle(),
                testList3.get(0).getTitle());
    }

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

    @Test
    void RefreshRecipeTitle() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getTitle().equals("refreshTitle"), true);
    }

    @Test
    void RefreshRecipeIngredients() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getIngredients().equals("testIngredients0"), true);
    }

    @Test
    void RefreshRecipeInstructions() {
        MockRefresh.mockRefreshRecipe(recipe0, "imagePNG");

        assertEquals(recipe0.getIngredients().equals("testIngredients0"), true);
    }

    @Test
    void RefreshRecipeImage() {
        assertEquals(MockRefresh.mockRefreshRecipe(recipe0, "imagePNG").equals("refreshedImagePNG"), true);
    }

    @Test
    void testsortAtoZ() {
        lst.sortAtoZ();
        int lessthan = lst.get(0).getTitle().compareTo(lst.get(1).getTitle());
        assertEquals(lessthan < 0, true);
    }

    @Test
    void testsortZtoA() {
        lst.sortZtoA();
        int greaterthan = lst.get(0).getTitle().compareTo(lst.get(1).getTitle());
        assertEquals(greaterthan > 0, true);
    }

    @Test
    void testsortOldtoNew() {
        lst.sortOldtoNew();
        int greaterthan = lst.get(0).getCreationTime().compareTo(lst.get(1).getCreationTime());
        assertEquals(greaterthan > 0, false);
    }

    @Test
    void testsortNewtoOld() {
        lst.sortNewtoOld();
        int lessthan = lst.get(0).getCreationTime().compareTo(lst.get(1).getCreationTime());
        assertEquals(lessthan < 0, false);
    }

    @Test
    void record() {
        String input = "record";
        String result = MockVoiceRecorder.record(input);
        assertEquals(result.equals("listening"), true);
    }

    @Test
    void stopRecord() {
        String input = "stop";
        String result = MockVoiceRecorder.stopRecord(input);
        assertEquals(result.equals("stop listening"), true);
    }

    @Test
    void understands() {
        String input = "Hello, World!";
        String result = MockWhisper.processIngredients(input, true);
        assertEquals(result.equals(input + "understand"), true);
    }

    @Test
    void notUnderstands() {
        String input = "sdgfadfasdfasdff!";
        String result = MockWhisper.processIngredients(input, false);
        assertEquals(result.equals(input + "misunderstand"), true);
    }

    @Test
    void breakfastMealTypeTest() {
        String mealType = "breakfast";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, true);
    }

    @Test
    void lunchMealTypeTest() {
        String mealType = "lunch";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, true);
    }

    @Test
    void dinnerMealTypeTest() {
        String mealType = "dinner";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, true);
    }

    @Test
    void dessertMealTypeTest() {
        String mealType = "dessert";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, false);
    }
}