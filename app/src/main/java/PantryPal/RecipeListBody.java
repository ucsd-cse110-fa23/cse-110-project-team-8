package PantryPal;

import java.io.*;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

    public void load(Recipe recipe){
        RecipeTitleButton recipe1 = new RecipeTitleButton(recipe);
        recipe1.getRecipe().setTitle(recipe.getTitle());
        recipe1.getRecipe().setIngredients(recipe.getIngredients());
        recipe1.getRecipe().setInstructions(recipe.getInstructions());
        recipe1.setDescription(mainScene);
        // Button titleButton = recipe1.getRecipeTitleButton();
        // titleButton.setOnAction(e1 -> {
        //     primaryStage.setScene(recipe1.getDescription());
        // });
        this.getChildren().add(recipe1);
        RecipeDescriptionScreen screen = new RecipeDescriptionScreen(recipe1, recipe.getTitle(), recipe.getIngredients(), recipe.getInstructions(),primaryStage, mainScene, this);
    }

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
