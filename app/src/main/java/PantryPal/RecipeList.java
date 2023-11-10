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

    public RecipeList() {
        this.setSpacing(5); // sets spacing between tasks
        this.setPrefSize(800, 800);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    // TODO: Iteration 2
    public void delete(String RecipeName) {

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
                recipe.setTime(row[3]);

                recipe.getRecipeTitle().setText(row[0]);
                recipe.getRecipeBody().setText(row[1] + "\n" + row[2] + "\n" + row[3]);
                recipe.getRecipeTitleButton().setText(row[0]);
                this.getChildren().add(recipe);
                GeneratedRecipeScreen screen = new GeneratedRecipeScreen(recipe, row[0], row[1], row[2],
                        row[3], primaryStage, mainScene);
            }

            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save to a CSV file whenever "close" is clicked
    public void toCSV() {
        try {
            // Create a CSV writer and specify the file to write to
            CSVWriter writer = new CSVWriter(new FileWriter("RecipeList.csv"));

            // Write data to the CSV file
            String[] header = { "Title", "Ingredients", "Instructions", "Preparation Time" };
            writer.writeNext(header);
            for (int i = 0; i < this.getChildren().size(); i++) {
                Recipe recipe = (Recipe) this.getChildren().get(i);
                String[] data = { recipe.getTitle(), recipe.getIngredients(), recipe.getInstructions(),
                        recipe.getTime() };
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
}
