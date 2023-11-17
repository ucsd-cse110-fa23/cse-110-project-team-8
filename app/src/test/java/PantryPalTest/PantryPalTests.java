package PantryPalTest;

import org.junit.jupiter.api.Test;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import PantryPal.*;
import java.io.FileReader;
import com.opencsv.exceptions.CsvValidationException;

public class PantryPalTests {
    private RecipeList recipeList;
    

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
    }

    // Test incrementing number of recipes
    @Test
    void testIncrementNumRecipe() {
        RecipeList recipeList = new RecipeList();
        recipeList.incNum();
        assertEquals(1, recipeList.getNum());
    }

    // Test deleting number of recipes
    @Test
    void testDeleteNumRecipe() {
        RecipeList recipeList = new RecipeList();
        recipeList.incNum();
        recipeList.decNum();
        assertEquals(0, recipeList.getNum());
    }

    // Test when the number of recipe in the list is 0
    @Test
    void testZeroNumRecipe() {
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

    //Test adding recipe to the list
    @Test
    void testAddtoRecipeList() {
        Recipe  testRecipe = new Recipe("testTitle", "testIngredients","testInstructions");
        recipeList.add(testRecipe);
        Recipe output = recipeList.get(0);
        assertEquals(testRecipe, output);
    }
    
    //Test deleting recipe from the list
    @Test
    void testDeletefromRecipeList() {
        recipeList.add(new Recipe("testTitle0", "testIngredients0","testTitle0"));
        recipeList.add(new Recipe("testTitle1", "testIngredients1","testTitle1"));
        recipeList.delete(recipeList.get(1));
        
        assertEquals(1, recipeList.size());    
    }
    
    //Test getting size for a non empty recipe list
    @Test
    void testGetRecipeListSize() {
        recipeList.add(new Recipe("testTitle0", "testIngredients0","testTitle0"));
        recipeList.add(new Recipe("testTitle1", "testIngredients1","testTitle1"));
        assertEquals(2, recipeList.size()); 
        
    }

    //Test getting size for an empty recipe list
    @Test
    void testGetEmptyRecipeListSize() {
        assertEquals(0, recipeList.size());
    }

    // Test getting the index of a recipe from the recipe list
    @Test
    void testGetIndexFromRecipeList() {
        Recipe recipe0 = new Recipe("testTitle0", "testIngredients0","testTitle0");
        Recipe recipe1 = new Recipe("testTitle1", "testIngredients1","testTitle1");
        Recipe recipe2 = new Recipe("testTitle2", "testIngredients1","testTitle1");
        
        recipeList.add(recipe0);
        recipeList.add(recipe1);
        recipeList.add(recipe2);

        assertEquals(recipe0, recipeList.get(0));  
        assertEquals(recipe1, recipeList.get(1));   
        assertEquals(recipe2, recipeList.get(2));        
    }

    //Test deleting all recipes from the recipe list
    @Test
    void testDeleteAllRecipeList() {
        recipeList.add(new Recipe("testTitle0", "testIngredient0","testTitle0"));
        recipeList.delete(recipeList.get(0));
        
        assertEquals(0, recipeList.size()); 
    }

    //Test csv creation from recipeList
    @Test
    void testSavingToCSV() throws IOException, CsvValidationException {
        recipeList.add(new Recipe("testTitle0", "testIngredient0","testTitle0"));
        recipeList.add(new Recipe("testTitle1", "testIngredient1","testTitle1"));
        recipeList.add(new Recipe("testTitle2", "testIngredient2","testTitle2"));
        recipeList.add(new Recipe("testTitle3", "testIngredient3","testTitle3"));
        recipeList.toCSV("Test.csv");
            recipeList.toCSV("OutputTest.csv");
        CSVReader testReader = new CSVReader(new FileReader("Test.csv"));
        CSVReader OutputReader = new CSVReader(new FileReader("OutputTest.csv"));

        while (testReader.peek() != null && OutputReader.peek() != null) {
            for (int i = 0; i < testReader.readNext().length; i++){
                if (testReader.peek() != null || OutputReader.peek() != null) {
                    break;
                }
                assertEquals(true ,testReader.readNext()[i].equals(OutputReader.readNext()[i]));
            }       
        }
    }

    //Test loading from csv and checking if the title is correct retrived by getTitle()
    @Test
    void testTitleLoadingCSV() throws IOException, CsvValidationException {
        recipeList.loadCSV("Test.csv");
        
        assertEquals(true,recipeList.get(0).getTitle().equals("testTitle0"));
        assertEquals(true,recipeList.get(1).getTitle().equals("testTitle1"));
        assertEquals(true,recipeList.get(2).getTitle().equals("testTitle2"));
        assertEquals(true,recipeList.get(3).getTitle().equals("testTitle3"));
    }

    //Test loading from csv and checking if the Ingredients is correct retrived by getIngredients()
    @Test
    void testIngredientsLoadingCSV() throws IOException, CsvValidationException {
        recipeList.loadCSV("Test.csv");
        
        assertEquals(true,recipeList.get(0).getIngredients().equals("testIngredient0"));
        assertEquals(true,recipeList.get(1).getIngredients().equals("testIngredient1"));
        assertEquals(true,recipeList.get(2).getIngredients().equals("testIngredient2"));
        assertEquals(true,recipeList.get(3).getIngredients().equals("testIngredient3"));
    }

    //Test loading from csv and checking if the Instructions are correct retrived by getInstructions()
    @Test
    void testInstructionsLoadingCSV() throws IOException, CsvValidationException {
        recipeList.loadCSV("Test.csv");
        
        assertEquals(true,recipeList.get(0).getInstructions().equals("testTitle0"));
        assertEquals(true,recipeList.get(1).getInstructions().equals("testTitle1"));
        assertEquals(true,recipeList.get(2).getInstructions().equals("testTitle2"));
        assertEquals(true,recipeList.get(3).getInstructions().equals("testTitle3"));
    }


    //------------------------------------------------- BDD TESTING --------------------------------------------------------------//

    // USER STORY 1
    //Test bdd1 accessing first recipe and bdd2 going back to recipe list
    // Caitlin wants to see the first recipe description.
    @Test
    void S1_BDD1and2() throws IOException, CsvValidationException {
        //Caitlin sees 2 recipes on her main recipe list titled "Pizza" and "Burger"
        Recipe pizza = new Recipe("Pizza", "cheese,flour","put pizza in oven");
        Recipe burger = new Recipe("Burger", "Meat, Cheese","put burger in the oven");

        recipeList.add(pizza);
        recipeList.add(burger);
        /* Caitlin wants to see the first recipe description, if she clicks on Pizza, she should see
           the title of the "Pizza" recipe, ingredients, and instructions */
        assertEquals(true,recipeList.get(0).getTitle().equals("Pizza"));
        assertEquals(true,recipeList.get(0).getIngredients().equals("cheese,flour"));
        assertEquals(true,recipeList.get(0).getInstructions().equals("put pizza in oven"));

        /* Caitlin returns to the recipeList screen and sees her 2 new recipes */
        assertEquals(2, recipeList.size());
    }


    // method to create recipe for BDD testing
    Recipe createRecipe(String mockTitle, String mealType, String ingredients, Boolean recognized){
        if (recognized == false){
            return null;
        }
        String instructions = "cook until ready";
        return new Recipe((mealType + mockTitle), ingredients , instructions);
    }


    // USER STORY 2

    // Caitlin wants to create a new recipe 
    // she cancels the recipe she created
    @Test
    void S2_BDD1andBDD2() throws IOException, CsvValidationException {
        String CaitlinMealType = "dinner ";
        String CaitlinTitle = "hot dog";
        String CaitlinIngredients = "sausage, ketchup, buns";

        Recipe recipe = createRecipe(CaitlinTitle, CaitlinMealType, CaitlinIngredients, true);
        
        assertEquals(true,recipe.getTitle().equals("dinner hot dog"));
        assertEquals(true,recipe.getIngredients().equals("sausage, ketchup, buns"));
        assertEquals(true,recipe.getInstructions().equals("cook until ready"));

        // Caitlin cancels the recipe thus it is not saved to the recipe list.
        assertEquals(0, recipeList.size());
    }

    // ChatGPT does not recognize Caitlins mealtype
    // so Caitlin tries to make a recipe again
    @Test
    void S2_BDD3andBDD4() throws IOException, CsvValidationException {
        String CaitlinMealType = "dinner ";
        String CaitlinTitle = "hot dog";
        String CaitlinIngredients = "sausage, ketchup, buns";

        Recipe badRecipe = createRecipe(CaitlinTitle, CaitlinMealType, CaitlinIngredients, false);

        // Caitlin cancels 
        assertEquals(null, badRecipe);
        
        Recipe goodRecipe = createRecipe(CaitlinTitle, CaitlinMealType, CaitlinIngredients, true);

        assertEquals(true,goodRecipe.getTitle().equals("dinner hot dog"));
        assertEquals(true,goodRecipe.getIngredients().equals("sausage, ketchup, buns"));
        assertEquals(true,goodRecipe.getInstructions().equals("cook until ready"));
    }

    // USER STORY 3

    // Caitlin wants to save a recently created recipe
    @Test
    void S3_BDD1() throws IOException, CsvValidationException {
        String CaitlinMealType = "dinner ";
        String CaitlinTitle = "hot dog";
        String CaitlinIngredients = "sausage, ketchup, buns";

        Recipe badRecipe = createRecipe(CaitlinTitle, CaitlinMealType, CaitlinIngredients, false);

        // Caitlin cancels 
        assertEquals(null, badRecipe);
        
        Recipe goodRecipe = createRecipe(CaitlinTitle, CaitlinMealType, CaitlinIngredients, true);

        assertEquals(true,goodRecipe.getTitle().equals("dinner hot dog"));
        assertEquals(true,goodRecipe.getIngredients().equals("sausage, ketchup, buns"));
        assertEquals(true,goodRecipe.getInstructions().equals("cook until ready"));
    }

    // Caitlin closes the app and relaunches it
    @Test
    void S3_BDD2() throws IOException, CsvValidationException {
        recipeList.add(new Recipe("testTitle0", "testIngredient0","testTitle0"));
        recipeList.add(new Recipe("testTitle1", "testIngredient1","testTitle1"));
        recipeList.add(new Recipe("testTitle2", "testIngredient2","testTitle2"));
        recipeList.add(new Recipe("testTitle3", "testIngredient3","testTitle3"));
        recipeList.toCSV("S3_BDD2_TEST.csv");
        
        // Caitlin relaunches the app and sees her 4 recipes
        RecipeList recipeListOutput = new RecipeList();
        recipeListOutput.loadCSV("S3_BDD2_TEST.csv");
        
        assertEquals(true,recipeListOutput.get(0).getTitle().equals("testTitle0"));
        assertEquals(true,recipeListOutput.get(1).getTitle().equals("testTitle1"));
        assertEquals(true,recipeListOutput.get(2).getTitle().equals("testTitle2"));
        assertEquals(true,recipeListOutput.get(3).getTitle().equals("testTitle3"));
        
    }



    Recipe editRecipe (Recipe recipe, String newingredients, String newinstructions, Boolean confirm) {
        if (confirm == true) {
            recipe.setIngredients(newingredients);
            recipe.setInstructions(newinstructions);
            return recipe;
        } else {
            return recipe;
        }
    }
    
    // USER STORY 4

    // Caitlin wants to edit her recipe and saves
    @Test
    void S4_BDD1() throws IOException, CsvValidationException {
        Recipe pizza = new Recipe("Pizza", "cheese, pepperoni, flour","add pepperoni put pizza in oven");
        recipeList.add(pizza);
        Recipe newPizza = editRecipe(recipeList.get(0), "cheese, flour", "put pizza in oven", true);

        assertEquals(true, newPizza.getIngredients().equals("cheese, flour"));
        assertEquals(true, newPizza.getInstructions().equals("put pizza in oven"));    
    }

    
    // Caitlin wants to edit her recipe but cancels
    @Test
    void S4_BDD2() throws IOException, CsvValidationException {
        Recipe pizza = new Recipe("Pizza", "cheese, pepperoni, flour","add pepperoni put pizza in oven");
        recipeList.add(pizza);
        Recipe newPizza = editRecipe(recipeList.get(0), "cheese, flour", "put pizza in oven", false);

        assertEquals(true, newPizza.getIngredients().equals("cheese, pepperoni, flour"));
        assertEquals(true, newPizza.getInstructions().equals("add pepperoni put pizza in oven"));    
    }
    
    // Caitlin wants to edit a new recipe and Saves
    @Test
    void S4_BDD3() throws IOException, CsvValidationException {
        Recipe pizza = new Recipe("Pizza", "cheese, pepperoni, flour","add pepperoni put pizza in oven");

        Recipe newPizza = editRecipe(pizza, "cheese, flour", "put pizza in oven", true);

        assertEquals(true, newPizza.getIngredients().equals("cheese, flour"));
        assertEquals(true, newPizza.getInstructions().equals("put pizza in oven"));    
    }

     // Caitlin wants to edit a new recipe but cancels
     @Test
     void S4_BDD4() throws IOException, CsvValidationException {
         Recipe pizza = new Recipe("Pizza", "cheese, pepperoni, flour","add pepperoni put pizza in oven");
 
         Recipe newPizza = editRecipe(pizza, "cheese, flour", "put pizza in oven", false);
 
         assertEquals(true, newPizza.getIngredients().equals("cheese, pepperoni, flour"));
         assertEquals(true, newPizza.getInstructions().equals("add pepperoni put pizza in oven"));    
     }
    
    
    

    void confirmDelete(Boolean confirm, RecipeList recipeList, Recipe recipe){
        if (confirm == true){
            recipeList.delete(recipe);
        }
    }
    
    // USER STORY 5
    
    //caitlin wants to delete a recipe and confirms
    @Test
    void S5_BDD1() {
        Recipe  testRecipe = new Recipe("testTitle", "testIngredients","testInstructions");
        recipeList.add(testRecipe);
        confirmDelete(true, recipeList, recipeList.get(0));

        assertEquals(0, recipeList.getNum());
    }

    //caitlin wanted to delete a recipe but cancels
    @Test
    void S5_BDD2() {
        Recipe  testRecipe = new Recipe("testTitle", "testIngredients","testInstructions");
        recipeList.add(testRecipe);
        confirmDelete(false, recipeList, recipeList.get(0));

        assertEquals(1, recipeList.getNum());
    }
}