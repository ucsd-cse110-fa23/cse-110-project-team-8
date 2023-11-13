package PantryPal;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javafx.scene.control.Button;

public class RecipeList {
    // private int numRecipe;
    private ArrayList<Recipe> list;

    public RecipeList(){
        // this.numRecipe = 0;
        list = new ArrayList<Recipe>();
    }

    public void delete(Recipe recipe) {
        list.remove(recipe);
    }

    public int size() {
        return this.list.size();
    }

    public void add(Recipe recipe) {
        list.add(recipe);
    }

    public Recipe get(int index) {
        return list.get(index);
    }

        // reload the CSV and regenerate the recipe to recipelist
    public void loadCSV(RecipeListBody recipeList) throws CsvValidationException {
        try {
            // Create a CSV reader and specify the file to read
            CSVReader reader = new CSVReader(new FileReader("RecipeList.csv"));
            reader.readNext();
            // Read the CSV file and process each row
            while (reader.peek() != null) {
                String[] row = reader.readNext();
                Recipe recipe = new Recipe(row[0], row[1], row[2]);

                // recipe.getRecipeTitle().setText(row[0]);
                // recipe.getRecipeBody().setText(row[1] + "\n" + row[2]);
                // recipe.getRecipeTitleButton().setText(row[0]);
                list.add(recipe);

                recipeList.load(recipe);
                // recipeList.incNum();
                // RecipeDescriptionScreen screen = new RecipeDescriptionScreen(recipe, row[0], row[1], row[2],primaryStage, mainScene, this);
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
            String[] header = { "Title", "Ingredients", "Instructions"};
            writer.writeNext(header);
            for (int i = 0; i < this.list.size(); i++) {
                Recipe recipe = (Recipe) this.list.get(i);
                String[] data = {recipe.getTitle(), recipe.getIngredients(), recipe.getInstructions()};
                writer.writeNext(data);
            }
            // Close the CSV writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public void incNum() {
    //     this.numRecipe++;
    // }

    // public void decNum() {
    //     if (this.getNum() == 0) {
    //         throw new IllegalArgumentException("No Recipe in the list.");
    //     }
    //     this.numRecipe--;
    // }

    // public int getNum() {
    //     return this.numRecipe;
    // }
}
