package PantryPal;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

import Server.*;
import VoiceInput.DallE;



public class RecipeListBody extends VBox {
    private Stage primaryStage;
    private Scene mainScene;
    private RecipeList recipeListArray;

    public RecipeListBody(RecipeList recipeListArray) {
        this.recipeListArray = recipeListArray;
        this.setSpacing(10);
        this.setPrefSize(800, 800);
        this.setStyle("-fx-background-color: #EAEAEA;");
    }

    // Within the body of the main screen, the recipe list is displayed and loaded
    // here
    public void load(Recipe recipe, Controller controller) throws Exception {
        RecipeTitleButton recipe1 = new RecipeTitleButton(recipe);
        recipe1.getRecipe().setTitle(recipe.getTitle());
        recipe1.getRecipe().setIngredients(recipe.getIngredients());
        recipe1.getRecipe().setInstructions(recipe.getInstructions());
        recipe1.setDescription(mainScene);

        try {
            String DallEInput = "Make a recipe image of mealtype " +
            "with the recipe title " + recipe.getTitle();

            DallE.chefDallE(DallEInput, recipe.getTitle());
        } catch (IOException | InterruptedException | URISyntaxException e2) {
            e2.printStackTrace();
        }

        this.getChildren().add(recipe1);
        RecipeDescriptionScreen screen = new RecipeDescriptionScreen(recipe1,
                recipe.getTitle(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                primaryStage, mainScene,
                this, controller, null, null);
    }

    //reload all recipes for sorting
    public void reloadAll(Controller controller) throws Exception {
        this.getChildren().clear();
        for (Recipe recipe: recipeListArray.getList()){
            RecipeTitleButton recipe1 = new RecipeTitleButton(recipe);
            recipe1.getRecipe().setTitle(recipe.getTitle());
            recipe1.getRecipe().setIngredients(recipe.getIngredients());
            recipe1.getRecipe().setInstructions(recipe.getInstructions());
            recipe1.setDescription(mainScene);

            this.getChildren().add(recipe1);
            RecipeDescriptionScreen screen = new RecipeDescriptionScreen(recipe1,
                recipe.getTitle(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                primaryStage, mainScene,
                this, controller, null, null);
        }
    }

    // Deletes the recipe from the recipe list
    public void delete(Node recipe) {
        this.getChildren().remove(recipe);
    }

    public RecipeList getArray() {
        return this.recipeListArray;
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setScene(Scene scene) {
        this.mainScene = scene;
    }
}
