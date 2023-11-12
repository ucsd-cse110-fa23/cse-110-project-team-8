package PantryPal;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RecipeList extends VBox {
    private Stage primaryStage;
    private Scene mainScene;
    private int numRecipe;

    public RecipeList() {
        // this.setSpacing(5); // sets spacing between tasks
        // this.setPrefSize(800, 800);
        // this.setStyle("-fx-background-color: #F0F8FF;");

        this.setSpacing(10); // Increased spacing for a cleaner look
        this.setPrefSize(800, 800);
        this.setStyle("-fx-background-color: #EAEAEA;"); // Light blue background color
        this.numRecipe = 0;
    }

    // TODO: Iteration 2
    public void delete(Recipe recipe) {
        this.getChildren().remove(recipe);
    }
    

    // reload the CSV and regenerate the recipe to recipelist
    public void loadCSV() throws CsvValidationException {
        try {
            // Create a CSV reader and specify the file to read
            CSVReader reader = new CSVReader(new FileReader("RecipeList.csv"));
            reader.readNext();
            // Read the CSV file and process each row
            while (reader.peek() != null) {
                String[] row = reader.readNext();
                Recipe recipe = new Recipe();
                recipe.setTitle(row[0]);
                recipe.setIngredients(row[1]);
                recipe.setInstructions(row[2]);

                recipe.getRecipeTitle().setText(row[0]);
                recipe.getRecipeBody().setText(row[1] + "\n" + row[2]);
                recipe.getRecipeTitleButton().setText(row[0]);
                this.getChildren().add(recipe);
                RecipeDescriptionScreen screen = new RecipeDescriptionScreen(recipe, row[0], row[1], row[2],primaryStage, mainScene, this);
            }

            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save to a CSV file whenever "close" is clicked
    public void toCSV(Recipe toDelete) {
        try {
            // Create a CSV writer and specify the file to write to
            CSVWriter writer = new CSVWriter(new FileWriter("RecipeList.csv"));

            // Write data to the CSV file
            String[] header = { "Title", "Ingredients", "Instructions"};
            writer.writeNext(header);
            for (int i = 0; i < this.getChildren().size(); i++) {
                Recipe recipe = (Recipe) this.getChildren().get(i);
                if (recipe == toDelete){
                    continue;
                }
                String[] data = {recipe.getTitle(), recipe.getIngredients(), recipe.getInstructions()};
                writer.writeNext(data);
            }
            // Close the CSV writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setScene(Scene scene) {
        this.mainScene = scene;
    }


    public void incNum() {
        this.numRecipe++;
    }

    public void decNum() {
        if (this.getNum() == 0) {
            throw new IllegalArgumentException("No Recipe in the list.");
        }
        this.numRecipe--;
    }

    public int getNum() {
        return this.numRecipe;
    }
}
